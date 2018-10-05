package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.domain.Student;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class StusAdapter extends AdapterBase<Student> {

    public StusAdapter(Context mContext, List<Student> list, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, Student item) {
        holder.setImageUrl(R.id.iv_photo,item.getPhoto(),R.mipmap.ic_launcher);
        holder.setText(R.id.tv_name,item.get_name());
        holder.setText(R.id.tv_id,item.get_id());
    }
}
