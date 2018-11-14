package com.cxyz.homepage.adapter;

import android.content.Context;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.homepage.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/21.
 */

public class FunctionAdapter extends AdapterBase<Map<String,Object>> {

    public FunctionAdapter(Context mContext, List<Map<String,Object>> data,int mItemLayoutId) {
       super(mContext,data,mItemLayoutId);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void convertView(ViewHolder holder, Map<String, Object> item) {
        holder.setText(R.id.tv_text,item.get("text").toString());
        holder.setImageResource(R.id.iv_img,(Integer) item.get("img"));
    }
}
