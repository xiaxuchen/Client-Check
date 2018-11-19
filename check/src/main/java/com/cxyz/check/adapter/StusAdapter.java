package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.other.MineMap;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class StusAdapter extends AdapterBase<GradeStusDto>{

    //控制是否显示已到达的状态变量
    private boolean hideNormal = false;

    private Context context;

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

    /**
     * 获取相应item的position
     * @param info 学号或姓名
     * @return position，如果没有则返回-1
     */
     public int getItemPostion(String info)
     {
         List<GradeStusDto> list = getList();
         int position = -1;
         int i = 0;

             try {
                 //如果可以转成int类型则为学号
                 Integer.parseInt(info);
                 //遍历学号获取position
                 for(GradeStusDto dto:list)
                 {
                     if(info.equals(dto.getId()))
                     {
                         position = i;
                         break;
                     }
                     i++;
                 }
             }catch (NumberFormatException e)
             {
                 //如果不能转成数字则为姓名
                 for(GradeStusDto dto:list)
                 {
                     //遍历姓名获取position
                     if(info.equals(dto.getName()))
                     {
                         position = i;
                         break;
                     }
                     i++;
                 }
             }

         return position;
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
                        if(dto.getId().equals(key))
                        {
                            if(!bad.contains(dto))
                                bad.add(dto);
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
    public void convertView(ViewHolder holder, GradeStusDto item) {
        QMUIRadiusImageView image=holder.getView(R.id.iv_photo);
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(5);//设置边缘宽度
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
     * 获取自动完成的列表信息
     * @return
     */
    public String[] getCompletion() {
        List<String> obj = new ArrayList();
        List<GradeStusDto> data = getList();
        for(GradeStusDto dto: data)
        {
                obj.add(dto.getName());
                obj.add(dto.getId());
        }
        String strData[] = new String[obj.size()];
        for(int i = 0;i<obj.size();i++)
        {
            strData[i] = obj.get(i);
        }
        return strData;

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
         Pattern pattern = Pattern.compile("\\w*"+info+"\\w*");
         LogUtil.e(String.valueOf(pattern.matcher("daskdlas"+info+"asdas").matches()));
         try {
             Integer.parseInt(info);
             for(GradeStusDto dto:list)
             {
                if(pattern.matcher(dto.getId()).matches())
                {
                    newValue.add(dto);
                }
             }
         }catch (NumberFormatException e)
         {
             for(GradeStusDto dto:list)
             {
                 if(pattern.matcher(dto.getName()).matches())
                 {
                     newValue.add(dto);
                 }
             }
         }
         //获取到数据后进行设置
         setList(newValue);
         notifyDataSetInvalidated();
     }
}
