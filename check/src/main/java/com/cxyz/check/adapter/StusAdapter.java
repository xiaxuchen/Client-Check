package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class StusAdapter extends AdapterBase<GradeStusDto>{

    //控制是否显示已到达的状态变量
    private boolean hideNormal = true;

    private Context context;

    public String[] items = new String[]{"迟到","请假","已到达","缺勤","早退"};

    public int [] values = new int[]{CheckRecordResult.
            LATE,CheckRecordResult.VACATE,CheckRecordResult.NORMAL
            ,CheckRecordResult.ABSENTEEISM,CheckRecordResult.EARLYLEAVE};
    //装载违规学生的信息
    Map<String,CommitCheckDto.StuInfo> dtoMap;

    //全部的记录
    private List<GradeStusDto> all;

    public StusAdapter(Context mContext, List<GradeStusDto> list, Map<String,CommitCheckDto.StuInfo> dtoMap, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
        this.all =  list;
        this.context = mContext;
        this.dtoMap = dtoMap;
    }

    //获取不良记录的list
    private List<GradeStusDto> getBad()
    {
        List<GradeStusDto> bad = new ArrayList<>();
        for(GradeStusDto item:all)
        {
            if(dtoMap.containsKey(item.getId()))
            {
                bad.add(item);
            }
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
            setList(getBad());
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
}
