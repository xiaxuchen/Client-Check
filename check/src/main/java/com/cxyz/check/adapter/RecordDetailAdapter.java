package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.logiccommons.domain.RecordDetail;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public class RecordDetailAdapter extends AdapterBase<RecordDetail> {

    public RecordDetailAdapter(Context mContext, List<RecordDetail> list, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, RecordDetail item) {
        //填充数据
        holder.setText(R.id.tv_lesson_name,item.get_name());
        holder.setText(R.id.tv_date,item.getCheckTime().getDate());
        holder.setText(R.id.tv_time,item.getCheckTime().getTime());
        holder.setText(R.id.tv_des,item.getDes()==null?"":item.getDes());
    }
}
