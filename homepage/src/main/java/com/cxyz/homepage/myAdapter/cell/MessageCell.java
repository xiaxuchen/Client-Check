package com.cxyz.homepage.myAdapter.cell;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cxyz.homepage.R;
import com.cxyz.homepage.myAdapter.base.CardBaseCell;
import com.cxyz.homepage.myAdapter.base.CardBaseViewHolder;
import com.cxyz.logiccommons.domain.RecordDetail;


/**
 * Created by 鱼塘主 on 2018/10/16.
 */

public class MessageCell extends CardBaseCell<RecordDetail>{
    public static final int CLAZZ_TYPE = 1;

    public MessageCell(RecordDetail o) {
        super(o);
    }

    @Override
    public int getItemType() {
        return CLAZZ_TYPE;
    }

    @Override
    public CardBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardBaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_item,null));
    }

    @Override
    public void onBindViewHolder(CardBaseViewHolder holder, int position) {

            switch (mData.getResult()){
                case -3:
                    holder.getImageView(R.id.img_item_check).setImageResource(R.mipmap.chi);
                    holder.setText(R.id.tv_item_checkstation,"迟到");
                break;
                case -1:
                    holder.getImageView(R.id.img_item_check).setImageResource(R.mipmap.jia);
                    holder.setText(R.id.tv_item_checkstation,"请假");
                break;
                case -4:
                    holder.getImageView(R.id.img_item_check).setImageResource(R.mipmap.que);
                    holder.setText(R.id.tv_item_checkstation,"缺勤");
                break;
                case -2:
                    holder.getImageView(R.id.img_item_check).setImageResource(R.mipmap.tui);
                    holder.setText(R.id.tv_item_checkstation,"早退");
                break;
            }
            holder.setText(R.id.tv_checkname,mData.getChecker().get_name());
            holder.setText(R.id.tv_checkreson,mData.getDes());
            holder.setText(R.id.tv_checktime,mData.getCheckTime().toString());


    }
}
