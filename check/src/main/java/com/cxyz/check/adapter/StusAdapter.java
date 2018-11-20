package com.cxyz.check.adapter;

import android.content.Context;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.cxyz.check.R;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.other.Comparator;
import com.cxyz.check.other.MineMap;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.ColorsUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.widget.sideview.CharacterParser;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class StusAdapter extends AdapterBase<GradeStusDto> implements SectionIndexer{

    private CharacterParser parser = CharacterParser.getInstance();

    //控制是否显示已到达的状态变量
    private boolean hideNormal = false;

    private Context context;

    private Comparator comparator = new Comparator();

    public String[] items = new String[]{"迟到","请假","已到达","缺勤","早退"};

    public int [] values = new int[]{CheckRecordResult.
            LATE,CheckRecordResult.VACATE,CheckRecordResult.NORMAL
            ,CheckRecordResult.ABSENTEEISM,CheckRecordResult.EARLYLEAVE};
    //装载违规学生的信息
    private MineMap<String,CommitCheckDto.StuInfo> dtoMap;

    //违规学生
    private List<GradeStusDto> bad;

    //全部的记录
    private List<GradeStusDto> all;


    public StusAdapter(Context mContext, List<GradeStusDto> list, MineMap<String,CommitCheckDto.StuInfo> dtoMap, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
        this.all =  list;
        this.context = mContext;
        this.dtoMap = dtoMap;
        initBad();
    }


    //将不良记录装载进list
    private List<GradeStusDto> initBad()
    {
        if(bad == null)
        {
            bad = new ArrayList<>();
            for (GradeStusDto item : all) {
                if (dtoMap.containsKey(item.getId())) {
                    bad.add(item);
                }
            }
            //为dtomap设置观察者
            dtoMap.setObserver(new MineMap.MineObserver<String, CommitCheckDto.StuInfo>() {
                @Override
                public void onRemove(String key, CommitCheckDto.StuInfo value) {
                    for(GradeStusDto dto:all)
                    {
                        if(dto.getId().equals(key))
                        {
                            bad.remove(dto);
                            break;
                        }
                    }
                    if(hideNormal)
                        notifyDataSetChanged();
                }

                @Override
                public void onPut(final String key, CommitCheckDto.StuInfo value) {
                    for(GradeStusDto dto:all)
                    {
                        if(dto.getId().equals(key)&&!bad.contains(dto))
                        {
                            bad.add(dto);
                            Collections.sort(bad,comparator);
                            break;
                        }
                    }
                    if(hideNormal)
                        notifyDataSetChanged();
                }
            });
        }
        return bad;
    }

    @Override
    public void convertView(ViewHolder holder, GradeStusDto item,int position) {
        char letter = parser.getSelling(item.getName()).toUpperCase().charAt(0);
        int selection = getPositionForSection(letter);
        LogUtil.e(position+"");
        LogUtil.e(selection+"");
        View view = holder.getConvertView().findViewById(R.id.ll_letter);
        if(selection == position)
        {
            if(!(view.getVisibility() == View.VISIBLE)){
                    view.setVisibility(View.VISIBLE);
                    view.setOnClickListener((View v) -> {});
            }
            TextView tv_letter = holder.getView(R.id.tv_letter);
            tv_letter.setText(String.valueOf(letter));

        }else {
            if(view.getVisibility() == View.VISIBLE)
            {
                view.setVisibility(View.GONE);
            }
        }
        QMUIRadiusImageView image=holder.getView(R.id.iv_photo);
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(1);//设置边缘宽度
        image.setBorderColor(ColorsUtil.TRANSPARENT);
        CommitCheckDto.StuInfo stuInfo = dtoMap.get(item.getId());
        holder.setImageUrl(R.id.iv_photo,item.getPhoto(),R.drawable.beauty);
        holder.setText(R.id.tv_name,item.getName());
        holder.setText(R.id.tv_id,item.getId());
        //如果违规学生map中没有该学生信息则不是违规学生，设置状态为已到达
        LogUtil.d(String.valueOf(stuInfo==null));
        if(stuInfo == null)
        {
            holder.setText(R.id.tv_state,items[2]);
            holder.setTextColor(R.id.tv_state,context.getResources().getColor(R.color.small_gray));
            holder.setTextColor(R.id.tv_states,context.getResources().getColor(R.color.small_gray));
        }else
        {
            //根据当前学生的状态值设置颜色以及状态
            holder.setText(R.id.tv_state,getResultString(stuInfo.getResult()));
            holder.setTextColor(R.id.tv_state,getStateColor(stuInfo.getResult()));
            holder.setTextColor(R.id.tv_states,getStateColor(stuInfo.getResult()));
        }
        super.convertView(holder,item);
    }

    /**
     * 设置是否显示正常学生信息
     * @param hideNormal
     */
    public void isShowNormal(boolean hideNormal)
    {
        this.hideNormal = hideNormal;
        if(!hideNormal)
        {
           setList(all);
        }else
        {
            setList(bad);
        }
        notifyDataSetInvalidated();
    }

    /**
     * 获取相应的状态字符串用以显示
     * @param result 状态值
     * @return
     */
    public String getResultString(Integer result)
    {
        int i = 0;
        for(int value:values)
        {
            if(value == result)
            {
                return items[i];
            }
            i++;
        }
        return "已到达";
    }


    /**
     * 获取显示状态的textview的颜色
     * @param result 考勤结果
     */
    public int getStateColor(int result)
    {
        return context.getResources().getColor(getColorId(result));
    }


    private int getColorId(int result)
    {
        LogUtil.d(result+"");
        //缺勤显示为红色
        if(result == CheckRecordResult.ABSENTEEISM)
            return R.color.red;
        //请假则显示为橙色
        else if(result == CheckRecordResult.VACATE)
            return R.color.orange;
        //已到达则显示灰色
        else if(result == CheckRecordResult.NORMAL)
            return R.color.small_gray;
        //迟到早退显示为蓝色
        else
            return R.color.blue;

    }


    /**
     * 筛选学生信息
     */
     public void filter(String info)
     {
         //如果输入框中为空则通过isNormal判断将数据设置为不良记录或全部记录
         if(info==null||info.isEmpty())
         {
             if(hideNormal)
                setList(bad);
             else
                 setList(all);
             notifyDataSetInvalidated();
             return;
         }
         List<GradeStusDto> list;
         if(hideNormal)
         {
             list = bad;
         }else
         {
             list = all;
         }
         LogUtil.e(list==null?"null":list.toString());
         List<GradeStusDto> newValue = new ArrayList<>();

         Pattern pattern = Pattern.compile(getRegex(info));
         try {
             Integer.parseInt(info);
             for(GradeStusDto dto:list)
             {
                 //如果学号匹配则显示
                if(pattern.matcher(dto.getId()).matches())
                {
                    newValue.add(dto);
                }
             }
         }catch (NumberFormatException e)
         {
             for(GradeStusDto dto:list)
             {
                 //如果名字的首拼匹配或者名字匹配则显示
                 if(pattern.matcher(dto.getName()).matches()||
                         pattern.matcher(getPrimarySpell(dto.getName())).matches())
                 {
                     newValue.add(dto);
                 }
             }
         }
         //获取到数据后进行设置
         setList(newValue);
         notifyDataSetInvalidated();
     }

    /**
     * 获取名字的首拼
     * @param name
     * @return
     */
     private String getPrimarySpell(String name)
     {

         StringBuilder builder = new StringBuilder();
         for(char c:name.toCharArray())
         {
             builder.append(parser.getSelling(String.valueOf(c)).charAt(0));
         }
         return builder.toString();
     }

    /**
     * 拼接正则表达式
     * @param info 需要匹配的字符串
     * @return 正则表达式
     */
    private String getRegex(String info)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("\\w*");
        for(char c:info.toCharArray())
        {
            builder.append(String.valueOf(c).toLowerCase());
            builder.append("\\w*");
        }
        return builder.toString();
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return parser.getSelling(getList().get(position).getName()).charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        LogUtil.e(String.valueOf((char)section));
        for (int i = 0; i < getCount(); i++) {
            String sortStr = parser.getSelling(getList().get(i).getName());;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

}
