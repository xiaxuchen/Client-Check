package com.cxyz.check.adapter;

import android.content.Context;

import com.cxyz.check.R;
import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class FunctionAdapter extends AdapterBase<Map<String,Object>>{


    public FunctionAdapter(Context mContext, List<Map<String, Object>> list, int mItemLayoutId) {
        super(mContext, list, mItemLayoutId);
    }

    @Override
    public void convertView(ViewHolder holder, Map<String, Object> item) {
        holder.setText(R.id.tv_txt,(String)item.get("txt"));
        holder.setImageResource(R.id.iv_img, (Integer) item.get("img"));
    }

    @Override
    public long getItemId(int position) {
        return (long)getItem(position).get("id");
    }
}
