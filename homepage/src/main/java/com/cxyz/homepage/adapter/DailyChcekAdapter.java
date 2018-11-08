package com.cxyz.homepage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.bean.DailyCheckBean;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/29.
 */

public class DailyChcekAdapter extends BaseAdapter {
    private List<DailyCheckBean> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。
    public DailyChcekAdapter(LayoutInflater inflater,List<DailyCheckBean> data){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View viewdailycheck=mInflater.inflate(R.layout.itemhome,null);
        DailyCheckBean dailyCheckinfo=mData.get(position);
        QMUIRadiusImageView image = (QMUIRadiusImageView)viewdailycheck.findViewById(R.id.iv_homedailycheck_image);
        TextView name = (TextView) viewdailycheck.findViewById(R.id.tv_dailycheck_name);
        TextView code = (TextView) viewdailycheck.findViewById(R.id.tv_dailycheck_code);
        TextView time = (TextView) viewdailycheck.findViewById(R.id.tv_dailycheck_code);
        final CheckBox cbstate =  (CheckBox) viewdailycheck.findViewById(R.id.cb_dailycheck_state);
        image.setCircle(true);
        image.setCornerRadius(100);
        image.setOval(false);//是否椭圆
        image.setBorderWidth(10);//设置边缘宽度
        cbstate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbstate.setText("已到达");
                    ToastUtil.showShort("已选中");
                }else{
                    cbstate.setText("未到达");
                    ToastUtil.showShort("未选中");
                }
            }
        });
        //将数据一一添加到自定义的布局中。
        image.setImageResource(dailyCheckinfo.getImage());
        name.setText(dailyCheckinfo.getName());
        code.setText(dailyCheckinfo.getCode());
        time.setText(dailyCheckinfo.getTime());
        return viewdailycheck;
    }
}
