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
 * 请求失败
 */

public class ErrorCell extends CardBaseCell<List<TestTask>>{
    public static final int ERROR_TYPE = 404;
    public ErrorCell(List<TestTask> o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return ERROR_TYPE;
    }

    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error,null));
    }

    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {
        int img = R.mipmap.logo;
        holder.getImageView(R.id.img_error).setImageResource(img);
    }
}
