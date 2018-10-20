package com.cxyz.homepage.myAdapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cxyz.homepage.R;
import com.cxyz.homepage.feature_z_domain.TestTask;
import com.cxyz.homepage.myAdapter.base.CardBaseCell;
import com.cxyz.homepage.myAdapter.base.CardBaseViewHolder;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/16.
 * 加载数据中
 */

public class LoadingCell extends CardBaseCell<List<TestTask>>{
    public static final int LOADING = 500;
    public LoadingCell(List<TestTask> o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return LOADING;
    }
    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error,null));
    }

    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {
        holder.getImageView(R.id.img_error).setImageResource(R.mipmap.common_logo);
    }
}
