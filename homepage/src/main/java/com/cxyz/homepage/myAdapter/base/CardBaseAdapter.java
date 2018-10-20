package com.cxyz.homepage.myAdapter.base;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.cxyz.commons.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/16.
 */

public abstract class CardBaseAdapter<C extends CardBaseCell> extends RecyclerView.Adapter<CardBaseViewHolder> {
    public static final String TAG = "CardBaseAdapter";
    protected List<C> mData;

    public CardBaseAdapter() {
        mData = new ArrayList<>();
    }

    public void setData(List<C> data) {
        LogUtil.e(data.size()+"");
        addAll(data);
        LogUtil.e(mData.size()+"");
        notifyDataSetChanged();
    }

    public List<C> getData() {
        return mData;
    }

    /**
     * 创建view助手
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            if (viewType == mData.get(i).getItemType()) {
                return mData.get(i).onCreateViewHolder(parent, viewType);
            }
        }
        throw new RuntimeException("wrong viewType");
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {
        mData.get(position).onBindViewHolder(holder, position);
    }

    /**
     * 释放资源
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(CardBaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.e(TAG, "onViewDetachedFromWindow invoke..."); //释放资源
        int position = holder.getAdapterPosition(); //越界检查
        if (position < 0 || position >= mData.size()) {
            return;
        }
        mData.get(position).releaseResource();
    }

    /**
     * 获取item数量
     * @return
     */
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 获取item的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

    /**
     * 向里面添加一个item
     * @param cell
     */
    public void add(C cell) {
        mData.add(cell);
        int index = mData.indexOf(cell);
        notifyItemChanged(index);
    }

    /**
     *向某个位置添加一个cell
     * @param index
     * @param cell
     */
    public void add(int index, C cell) {
        mData.add(index, cell);
        notifyItemChanged(index);
    }

    /**
     * 移除一个Cell
     * @param cell
     */
    public void remove(C cell) {
        int indexOfCell = mData.indexOf(cell);
        remove(indexOfCell);
    }

    /**
     * 根据Cell的位置：index移除一个Cell
     * @param index
     */
    public void remove(int index) {
        mData.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * 移除从开头数的Cell的个数的个数
     * @param start
     * @param count
     */
    public void remove(int start, int count) {
        if ((start + count) > mData.size()) {
            return;
        }
        int size = getItemCount();
        for (int i = start; i < size; i++) {
            mData.remove(i);
        }
        notifyItemRangeRemoved(start, count);
    }

    /**
     *  添加一个list类型的Cells
     * @param cells
     */
    public void addAll(List<C> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        Log.e("Event", "addAll cell size:" + cells.size());
        mData.addAll(cells);
        notifyItemRangeChanged(mData.size() - cells.size(), mData.size());
    }

    /**
     * 从某一个位置添加一个Cells
     * @param index
     * @param cells
     */
    public void addAll(int index, List<C> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        mData.addAll(index, cells);
        notifyItemRangeChanged(index, index + cells.size());
    }

    /**
     * 清空Cell
     */
    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 如果子类需要在onBindViewHolder 回调的时候做的操作可以在这个方法里做 *
     * @param holder *
     * @param position
     */
    protected abstract void onViewHolderBound(CardBaseViewHolder holder, int position);
}

