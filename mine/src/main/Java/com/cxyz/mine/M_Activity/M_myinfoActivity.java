package com.cxyz.mine.M_Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class M_myinfoActivity extends BaseActivity implements View.OnClickListener{
    Switch m_myinfo_switch;
    @Override
    public int getContentViewId() {
        return R.layout.m_myinfo_layout;
    }

    @Override
    public void initView() {
        ImageView m_myinfo_headimg;
        TextView m_myinfo_name,m_myinfo_sex,m_myinfo_schoolcode,m_myinfo_class,
                m_myinfo_college,m_myinfo_result,m_myinfo_tel;
        m_myinfo_headimg= (ImageView) findViewById(R.id.m_myinfo_headimg);
        m_myinfo_sex= (TextView) findViewById(R.id.m_myinfo_sex);
        m_myinfo_schoolcode= (TextView) findViewById(R.id.m_myinfo_schoolcode);
        m_myinfo_class= (TextView) findViewById(R.id.m_myinfo_class);
        m_myinfo_college= (TextView) findViewById(R.id.m_myinfo_college);
        m_myinfo_result= (TextView) findViewById(R.id.m_myinfo_result );
        m_myinfo_tel= (TextView) findViewById(R.id.m_myinfo_tel);
        m_myinfo_switch= (Switch) findViewById(R.id.m_myinfo_switch);
        m_myinfo_switch.setOnClickListener(this);
        m_myinfo_headimg.setOnClickListener(this);
        m_myinfo_sex.setOnClickListener(this);
        m_myinfo_schoolcode.setOnClickListener(this);
        m_myinfo_class.setOnClickListener(this);
        m_myinfo_college.setOnClickListener(this);
        m_myinfo_result.setOnClickListener(this);
        m_myinfo_tel.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_myinfo_switch:m_myinfo_switch();
        }

    }
    public void m_myinfo_switch(){

        if (m_myinfo_switch.isChecked()){
            ToastUtil.showShort("已开启");
        }else {
            ToastUtil.showShort("已关闭");
        }
    }
}
