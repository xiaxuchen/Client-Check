package com.cxyz.check.adapter;

import android.content.Context;
import android.view.View;

import com.cxyz.check.R;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class StusAdapter extends AdapterBase<GradeStusDto> {

    //控制是否显示已到达的状态变量
    private boolean showNormal = true;

    String[] items = new String[]{"迟到","请假","已到达","缺勤","早退"};

    int [] values = new int[]{CheckRecordResult.
            LATE,CheckRecordResult.VACATE,CheckRecordResult.NORMAL
            ,CheckRecordResult.ABSENTEEISM,CheckRecordResult.EARLYLEAVE};
    //装载违规学生的信息
    Map<String,CommitCheckDto.StuInfo> dtoMap;

    public StusAdapter(Context mContext, List<GradeStusDto> list, Map<String,CommitCheckDto.StuInfo> dtoMap, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
        this.dtoMap = dtoMap;
    }

    @Override
    public void convertView(ViewHolder holder, GradeStusDto item) {
        CommitCheckDto.StuInfo stuInfo = dtoMap.get(item.getId());
        holder.setImageUrl(R.id.iv_photo,item.getPhoto(),R.mipmap.common_logo);
        holder.setText(R.id.tv_name,item.getName());
        holder.setText(R.id.tv_id,item.getId());
        //如果违规学生map中没有该学生信息则不是违规学生，设置状态为已到达
        holder.setText(R.id.tv_state,stuInfo==null?"已到达":getResultString(stuInfo.getResult()));
        if(!showNormal&&stuInfo==null)
        {
            holder.getConvertView().setVisibility(View.GONE);
        }

    }

    /**
     * 设置是否显示正常学生信息
     * @param showNormal
     */
    public void isShowNormal(boolean showNormal)
    {
        this.showNormal = showNormal;
    }

    /**
     * 获取相应的状态字符串用以显示
     * @param result 状态值
     * @return
     */
    private String getResultString(int result)
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

}
