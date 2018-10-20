package com.cxyz.homepage.myAdapter.base;

/**
 * Created by 鱼塘主 on 2018/10/16.
 */

public abstract class CardBaseCell<T> implements Cell {
    public CardBaseCell(T t) {
        mData = t;
    }

    public T mData;

    @Override
    public void releaseResource() {
        // do nothing
        // 如果有需要回收的资源，子类自己实现

    }
}