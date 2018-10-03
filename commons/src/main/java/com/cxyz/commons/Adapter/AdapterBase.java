package com.cxyz.commons.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * listView适配器的基类，泛型T为传递给Adapter的数据类型
 */
public abstract class AdapterBase<T> extends BaseAdapter {
    protected List<T> mList = new LinkedList<T>(); //数据吧
    protected Context mContext; //上下文
    protected int mItemLayoutId; //item布局id

    //无参的构造方法
    public AdapterBase() {

    }

    /**
     *  带三个参数的构造方法初始化对象
     * @param mContext
     * @param list
     * @param mItemLayoutId
     */
    public AdapterBase(Context mContext, List<T> list, int mItemLayoutId) {
        initAdp(mContext, list, mItemLayoutId);
    }


    private void initAdp(Context mContext, List<T> list, int mItemLayoutId) {
        if (list == null) {
            return;
        }
        this.mList = list;
        this.mContext = mContext;
        this.mItemLayoutId = mItemLayoutId;
        notifyDataSetChanged();
    }

    /**
     * 获取所有数据
     * @return <list></>数据
     */
    public List<T> getList() {
        return mList;
    }

    /**
     * 动态添加Item
     * @param item
     */
    public void appendItem(T item) {
        if (item == null) {
            return;
        }
        mList.add(item);
        afterAddLists(mList);
        notifyDataSetChanged();//刷新的方法
    }

    /**
     * 设置数据list
     * @param list 装载数据的list
     */
    public void setList(List<T> list) {
        if (list == null) {
            return;
        }
        mList = list;
        afterAddLists(mList);
        notifyDataSetChanged();
    }

    /**
     * 添加item到存放数据的list中
     * 会刷新的
     * @param list
     */
    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
        afterAddLists(mList);
        notifyDataSetChanged();
    }

    /**
     * 添加item到存放数据的list中，且放在第一个
     * 也会刷新的
     * @param list
     */
    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(0, list);
        afterAddLists(mList);
        notifyDataSetChanged();
    }

    /**
     * 删除指定数据
     * @param t
     */
    public void deleteItem(T t) {
        if (t == null) {
            return;
        }
        mList.remove(t);
        afterRemove(mList);
        notifyDataSetChanged();
    }

    /**
     * 移除指定item后执行
     */
    protected void afterRemove(List<T> list) {
    }


    /**
     * 清空所有条目
     */
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     *
     * @return 数据的长度
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     *
     * @param position
     * @return 返回该位置的数据对象
     */
    @Override
    public T getItem(int position) {
        if (position > mList.size() - 1) {
            return null;
        }
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convertView(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }

    /**
     * @param holder
     * @param item
     */
    public abstract void convertView(ViewHolder holder, T item);

    /**
     * 在添加item到list之后、更新listview之前执行
     * @param lists
     */
    protected void afterAddLists(List<T> lists) {
    }

    /**
     * 跳转到指定activity
     * @param bundle 数据
     * @param acti activity的class
     */
    protected void startActi(Bundle bundle, Class<?> acti) {
        Intent intent = new Intent(mContext, acti);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mContext.startActivity(intent);
    }
}

