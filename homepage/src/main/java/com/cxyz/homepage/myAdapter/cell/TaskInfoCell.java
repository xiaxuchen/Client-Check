package com.cxyz.homepage.myAdapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cxyz.homepage.R;
import com.cxyz.homepage.myAdapter.base.CardBaseCell;
import com.cxyz.homepage.myAdapter.base.CardBaseViewHolder;
import com.cxyz.logiccommons.domain.TaskInfo;

/**
 * Created by 鱼塘主 on 2018/10/18.
 */

public class TaskInfoCell extends CardBaseCell<TaskInfo>{
    public static final int TASKINFO_TYPE = 2;
    public TaskInfoCell(TaskInfo o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item_taskinfo,null));
    }

    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {
        holder.setText(R.id.tv_taskinfo_time,mData.getStart().getHour()+":"+mData.getStart().getMinute()+" - "+mData.getEnd().getHour()+":"+mData.getEnd().getMinute());
        holder.setText(R.id.tv_taskinfo_clazzname,mData.get_name());
        holder.setText(R.id.tv_taskinfo_clazzteacher,mData.getSponser().get_name());
        holder.setText(R.id.tv_taskinfo_clazzname,mData.getClassRoom().get_name());
    }
}
