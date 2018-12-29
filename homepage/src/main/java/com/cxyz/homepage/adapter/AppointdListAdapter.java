package com.cxyz.homepage.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyz.homepage.R;
import com.cxyz.homepage.bean.AppointListBean;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/28.
 */

public class AppointdListAdapter extends BaseAdapter {
    private Boolean click=true;
    private List<AppointListBean> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    public AppointdListAdapter(LayoutInflater inflater, List<AppointListBean> data) {
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得ListView中的view
        View viewStudent = mInflater.inflate(R.layout.item_appoint_list, null);
        //获得学生对象
        AppointListBean studentinfo = mData.get(position);
        //获得自定义布局中每一个控件的对象。
        TextView name = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_name);
        TextView id = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_id);
        TextView  create = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_create);
        TextView check = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_check);
        TextView manager=viewStudent.findViewById(R.id.tv_itemappoint_manager);
        TextView  rank = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_rank);
        TextView advice = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_advice);
        Button state=viewStudent.findViewById(R.id.bt_itemappoint_state);
        QMUIRadiusImageView image=viewStudent.findViewById(R.id.iv_itemappoint_image);
        LinearLayout linearLayout= viewStudent.findViewById(R.id.ll_itemappoint_info);
        TextView info=viewStudent.findViewById(R.id.tv_itemappoint_info);
        TextView  time = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_time);
        TextView reason = (TextView) viewStudent.findViewById(R.id.tv_itemappoint_reason);
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(5);//设置边缘宽度
        image.setBorderColor(Color.CYAN);
        //将数据一一添加到自定义的布局中。
        name.setText(studentinfo.getName());
        id.setText(studentinfo.getId());
        create.setText(studentinfo.getCreate());
        check.setText(studentinfo.getCheck());
        manager.setText(studentinfo.getManager());
        rank.setText(studentinfo.getRank());
        advice.setText(studentinfo.getAdvice());
        state.setText(studentinfo.getState());
        image.setImageResource(studentinfo.getImage());
        time.setText(studentinfo.getTime());
        reason.setText(studentinfo.getReason());
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(click==true){
                    click=false;

                    linearLayout.setVisibility(View.VISIBLE);


                }else if(click==false){
                    click=true;
                    linearLayout.setVisibility(View.GONE);

                }
            }
        });
        return viewStudent;
    }
}
