package com.cxyz.mine.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
        QMUIRadiusImageView image = (QMUIRadiusImageView) viewStudent.findViewById(R.id.iv_appoint_image);
        final LinearLayout ll_appointmentvisible_info=viewStudent.findViewById(R.id.ll_appoint_info);
        final Button  bt_appoint_check=viewStudent.findViewById(R.id.bt_appoint_check);
        TextView name = (TextView) viewStudent.findViewById(R.id.tv_appoint_name);
        TextView code = (TextView) viewStudent.findViewById(R.id.tv_appoint_code);
        TextView info = (TextView) viewStudent.findViewById(R.id.tv_appoint_info);
        TextView time = (TextView) viewStudent.findViewById(R.id.tv_appoint_time);
        TextView moreinfo = (TextView) viewStudent.findViewById(R.id.tv_appoint_moreinfo);
        final LinearLayout ll_appoint_message=viewStudent.findViewById(R.id.ll_appoint_message);
        final Button bt_appoint_reject=viewStudent.findViewById(R.id.bt_appoint_reject);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(click==true){
                    click=false;
                    ll_appoint_message.setVisibility(View.GONE);
                ll_appointmentvisible_info.setVisibility(View.VISIBLE);


            }else if(click==false){
                    click=true;
                    ll_appointmentvisible_info.setVisibility(View.GONE);
                    ll_appoint_message.setVisibility(View.VISIBLE);
            }
            }
        });
        //老师审核
        bt_appoint_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_appoint_check.setClickable(false);
                bt_appoint_reject.setVisibility(View.GONE);
                bt_appoint_check.setBackgroundResource(R.drawable.main_shapegray);
                bt_appoint_check.setText("已审核");
            }
        });
        bt_appoint_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_appoint_reject.setClickable(false);
                bt_appoint_check.setVisibility(View.GONE);
                bt_appoint_reject.setBackgroundResource(R.drawable.main_shapegray);
                bt_appoint_reject.setText("已拒绝");
            }
        });
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(5);//设置边缘宽度
        image.setBorderColor(Color.CYAN);

        //将数据一一添加到自定义的布局中。
        image.setImageResource(studentinfo.getImage());
        name.setText(studentinfo.getName());
        code.setText(studentinfo.getCode());
        time.setText(studentinfo.getTime());
        moreinfo.setText(studentinfo.getMoreinfo());
        return viewStudent;
    }
}
