package com.cxyz.homepage.myAdapter.cell;

import android.view.ViewGroup;

import com.cxyz.homepage.myAdapter.base.CardBaseCell;
import com.cxyz.homepage.myAdapter.base.CardBaseViewHolder;

/**
 * Created by 鱼塘主 on 2018/10/18.
 */

public class TaskInfoCell extends CardBaseCell{
    public static final int TASKINFO_TYPE = 2;
    public TaskInfoCell(Object o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {

    }
}
