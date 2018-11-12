package com.cxyz.homepage.myAdapter;

import android.support.annotation.NonNull;

import com.cxyz.homepage.myAdapter.base.CardBaseAdapter;
import com.cxyz.homepage.myAdapter.base.CardBaseViewHolder;
import com.cxyz.homepage.myAdapter.cell.MessageCell;

/**
 * Created by 鱼塘主 on 2018/10/20.
 */

public class MessageRVAdapter extends CardBaseAdapter<MessageCell>{
    @Override
    protected void onViewHolderBound(CardBaseViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull CardBaseViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder, position);
    }
}
