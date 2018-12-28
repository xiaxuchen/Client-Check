package com.cxyz.homepage.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxyz.homepage.R;
import com.cxyz.homepage.bean.CheckRecordBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/28.
 */

public class CheckRecordListAdapter extends BaseAdapter {
    private Boolean click = true;
    private List<CheckRecordBean> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    public CheckRecordListAdapter(LayoutInflater inflater, List<CheckRecordBean> data) {
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
        View viewStudent = mInflater.inflate(R.layout.item_checkrecord_layout, null);
        //获得学生对象
        CheckRecordBean studentinfo = mData.get(position);
        //获得自定义布局中每一个控件的对象。
        TextView name = (TextView) viewStudent.findViewById(R.id.tv_checkstu_name);
        TextView id = (TextView) viewStudent.findViewById(R.id.tv_checkstu_id);
        TextView classname = (TextView) viewStudent.findViewById(R.id.tv_checkstu_classname);
        TextView time = (TextView) viewStudent.findViewById(R.id.tv_checkstu_time);
        //将数据一一添加到自定义的布局中。
        name.setText(studentinfo.getStuname());
        id.setText(studentinfo.getStuid());
        classname.setText(studentinfo.getStuclassname());
        time.setText(studentinfo.getStutime());

        return viewStudent;
    }
}
