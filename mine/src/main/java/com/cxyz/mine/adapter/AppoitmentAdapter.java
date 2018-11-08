package com.cxyz.mine.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyz.mine.R;
import com.cxyz.mine.bean.AppointmentInfoBean;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/28.
 */

public class AppoitmentAdapter extends BaseAdapter {
    private Boolean click=true;
    private List<AppointmentInfoBean> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。
    public AppoitmentAdapter(LayoutInflater inflater,List<AppointmentInfoBean> data){
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
        View viewStudent = mInflater.inflate(R.layout.item_appoint_layout,null);
        //获得学生对象
        AppointmentInfoBean studentinfo = mData.get(position);
        //获得自定义布局中每一个控件的对象。
        QMUIRadiusImageView image = (QMUIRadiusImageView) viewStudent.findViewById(R.id.iv_appointmentstu_image);
        final LinearLayout ll_appointmentvisible_info=viewStudent.findViewById(R.id.ll_appointmentvisible_info);
        final Button  button=viewStudent.findViewById(R.id.bt_appointmentstu_check);
        TextView name = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_name);
        TextView code = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_code);
        TextView info = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_info);
        TextView time = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_time);
        TextView reason = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_reason);
        TextView moreinfo = (TextView) viewStudent.findViewById(R.id.tv_appointmentstu_moreinfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(click==true){
                    click=false;
                ll_appointmentvisible_info.setVisibility(View.VISIBLE);

            }else if(click==false){
                    click=true;
                    ll_appointmentvisible_info.setVisibility(View.GONE);

            }
            }
        });
        //老师审核
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setClickable(false);
                button.setText("已审核");
                button.setBackgroundColor(R.color.qmui_config_color_blue);
            }
        });
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(10);//设置边缘宽度

        //将数据一一添加到自定义的布局中。
        image.setImageResource(studentinfo.getImage());
        name.setText(studentinfo.getName());
        code.setText(studentinfo.getCode());
        time.setText(studentinfo.getTime());
        reason.setText(studentinfo.getReason());
        moreinfo.setText(studentinfo.getMoreinfo());
        return viewStudent;
    }
}
