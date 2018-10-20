package com.cxyz.homepage.myAdapter.base;

import android.view.ViewGroup;

/**
 * Created by 鱼塘主 on 2018/10/16.
 */

public interface Cell {
    /**
     * 回收资源
     */
    public void releaseResource();

    /**
     * 获取viewType
     * @return
     */
    public int getItemType();

    /**
     * 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    public void onBindViewHolder(CardBaseViewHolder holder, int position);
}
