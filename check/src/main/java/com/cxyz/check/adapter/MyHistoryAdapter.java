package com.cxyz.check.adapter;

import android.content.Context;
import android.view.View;

import com.cxyz.check.R;
import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.date.DateTime;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */

public class MyHistoryAdapter extends AdapterBase<MyHistoryDto> {

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public MyHistoryAdapter(Context mContext, List<MyHistoryDto> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, MyHistoryDto item,int position) {
        holder.getConvertView().setOnClickListener(view -> {
            if(listener != null)
                listener.onClick(view,position);
        });
        DateTime dateTime = DateTime.fromTS(item.getTime());
        holder.setText(R.id.tv_task_name,item.getSubject());
        holder.setText(R.id.tv_date,dateTime.getDate()+"  "+dateTime.getTime());
        holder.setText(R.id.tv_des,item.getDes());
        String result = null;
        switch (item.getResult())
        {
            case CheckRecordResult.VACATE:{
                result = "请假";
            }break;
            case CheckRecordResult.ABSENTEEISM:{
                result = "缺勤";
            }break;
            case CheckRecordResult.EARLYLEAVE:{
                result = "早退";
            }break;
            case CheckRecordResult.LATE:{
                result = "迟到";
            }break;
        }
        holder.setText(R.id.tv_result,result);
        super.convertView(holder,item);
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
}
