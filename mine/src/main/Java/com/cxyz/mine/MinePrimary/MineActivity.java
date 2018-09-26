package com.cxyz.mine.MinePrimary;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.M_Activity.M_settingActivity;
import com.cxyz.mine.R;
import com.cxyz.mine.M_Activity.M_appointmentActivity;
import com.cxyz.mine.M_Activity.M_myinfoActivity;


/**
 * Created by Administrator on 2018/9/25.
 */

public class MineActivity extends BaseActivity implements View.OnClickListener {
    private TitleView m_title;
    private  TextView m_myinfo,m_appointment, m_setting;
    Button exitlogin;
    public int getContentViewId() {
        return R.layout.mine_layout;
    }

    @Override
    public void initView() {
        m_myinfo= (TextView) findViewById(R.id.m_myinfo);
        m_appointment =(TextView) findViewById(R.id.m_appointment);
        m_setting=(TextView)findViewById(R.id.m_setting);
        exitlogin=(Button)findViewById(R.id.exitlogin);
        m_title= (TitleView) findViewById(R.id.m_title);
        m_title.setTitle("我的");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        m_myinfo.setOnClickListener(this);
        m_appointment.setOnClickListener(this);
        m_setting.setOnClickListener(this);
        exitlogin.setOnClickListener(this);
        m_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onNameClick() {

            }

            @Override
            public void onSetClick() {

            }
        });


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
    //监听事件
    public void mine_info_myinfo() {
        Intent intent=new Intent(getApplicationContext(),M_myinfoActivity.class);
        startActivity(intent);
    }


    public void mine_info_appointment() {
        Intent  intent=new Intent(getApplicationContext(),M_appointmentActivity.class);
        startActivity(intent);
    }

    public void mine_info_setting() {
        Intent  intent=new Intent(getApplicationContext(),M_settingActivity.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.m_myinfo: mine_info_myinfo();break;
            case R.id.m_appointment:mine_info_appointment();break;
            case R.id.m_setting:mine_info_setting();break;
        }


    }
}