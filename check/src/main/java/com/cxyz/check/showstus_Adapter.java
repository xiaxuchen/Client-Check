package com.cxyz.check;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 28058 on 2018/9/26.
 */

public class showstus_Adapter extends BaseAdapter {
    private ArrayList mData;
    private Context mContext;

    public showstus_Adapter(ArrayList mData, Context mContext) {

        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {

        return mData.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }
//初始化item的各个组件
        final ImageView stu_image = (ImageView) convertView.findViewById(R.id.stu_image);
        final TextView stu_name = (TextView) convertView.findViewById(R.id.stu_name);

        //获取学生姓名

        stu_name.setText(
                "安卓机器人"
                //mData.get(position).getName()
        );


        final TextView hotelLocation = (TextView) convertView.findViewById(R.id.stu_info);

        //获取学生信息
        hotelLocation.setText(
                "安卓机器人没有学号(String)"
                //mData.get(position).getLocation()
        );


        //获取学生头像
        stu_image.setBackgroundResource(R.mipmap.ic_launcher);


//设置按钮的监听，实现点击后跳转到酒店详细内容的界面，并且传递数据
        return convertView;
    }
}
