package com.cxyz.check.adapter;

import android.content.Context;
import android.view.View;

import com.cxyz.check.R;
import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.date.DateTime;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */

public class HistoryAdapter extends AdapterBase<CheckHistoryDto> {

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public HistoryAdapter(Context mContext, List<CheckHistoryDto> list, int... mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, CheckHistoryDto item,int position) {
        holder.getConvertView().setOnClickListener(view -> {
            listener.onClick(view,position);
        });
        DateTime dateTime = DateTime.fromTS(item.getCommitTime());
        holder.setText(R.id.tv_task_name,item.getTaskName());
        holder.setText(R.id.tv_date,dateTime.getDate()+"\n    "+dateTime.getTime());
        if(item.getResults() == null)
            return;
        int counts[] = new int[4];

        if(item.getResults().get(0).getResultType() == null)
        {
            holder.setText(R.id.tv_des,"迟到:0早退:0缺勤:0请假:0");
        }else {
            for(CheckHistoryDto.RecordResultCustom result:item.getResults())
            {
                switch (result.getResultType())
                {
                    case CheckRecordResult.VACATE:{
                        counts[3] = result.getCount();
                    }break;
                    case CheckRecordResult.ABSENTEEISM:{
                        counts[2] = result.getCount();
                    }break;
                    case CheckRecordResult.EARLYLEAVE:{
                        counts[1] = result.getCount();
                    }break;
                    case CheckRecordResult.LATE:{
                        counts[0] = result.getCount();
                    }break;
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append("迟到:"+counts[0]).append("早退:"+counts[1]+"缺勤:"+counts[2]+"请假:"+counts[3]);
            holder.setText(R.id.tv_des,builder.toString());
        }

        super.convertView(holder,item);
    }

    public interface OnItemClickListener{
        void onClick(View view,int position);
    }
}
