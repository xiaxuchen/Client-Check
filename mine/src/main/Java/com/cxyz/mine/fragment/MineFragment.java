package com.cxyz.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.mine.IPresenter.presenter.IMinePresenter;
import com.cxyz.mine.R;
import com.cxyz.mine.activity.ApponitmentActivity;
import com.cxyz.mine.activity.ForgetpwdActivity;
import com.cxyz.mine.activity.MoreSettingActivity;
import com.cxyz.mine.activity.MyinfoActivity;
import com.cxyz.mine.activity.UserResponse;

/**
 * Created by Administrator on 2018/9/25.
 */
@Route(path = "/mine/MineFragment")
public class MineFragment extends BaseFragment<IMinePresenter> implements View.OnClickListener {
    private TextView tv_mine_myinfo;
    private TextView tv_mine_update;
    private TextView tv_mine_useradvice;
    private TextView tv_mine_setting;
    private TextView tv_mine_appointment;
    private  TextView   tv_mine_alterpwd;
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_mine_myinfo = findViewById(R.id.tv_mine_myinfo);
        tv_mine_appointment = findViewById(R.id.tv_mine_appointment);
        tv_mine_update = findViewById(R.id.tv_mine_update);
        tv_mine_useradvice = findViewById(R.id.tv_mine_useradvice);
        tv_mine_setting = findViewById(R.id.tv_mine_setting);
        tv_mine_alterpwd = findViewById(R.id.tv_mine_alterpwd);
    }

    @Override
    protected IMinePresenter createIPresenter() {
        return new IMinePresenter() {
            @Override
            public IBaseModel createModel() {
                return super.createModel();
            }
        };
    }

    @Override
    protected void setListener() {
        tv_mine_myinfo.setOnClickListener(this);
        tv_mine_appointment.setOnClickListener(this);
        tv_mine_update.setOnClickListener(this);
        tv_mine_useradvice.setOnClickListener(this);
        tv_mine_setting.setOnClickListener(this);
        tv_mine_alterpwd.setOnClickListener(this);
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    //监听事件

    //界面跳转
    //从我的界面跳转到设置界面
    public void tv_mine_setting() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MoreSettingActivity.class);
        startActivity(intent);
    }

    //从我的界面跳转到预约请假界面
    public void tv_mine_appointment() {
        Intent intent = new Intent(getActivity().getApplicationContext(), ApponitmentActivity.class);
        startActivity(intent);
    }

    //从我的界面跳转到个人信息界面
    public void tv_mine_myinfo() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MyinfoActivity.class);
        startActivity(intent);
    }
    //从我的界面跳转到修改密码界面
    public void tv_mine_alterpwd() {
        Intent intent = new Intent(getActivity().getApplicationContext(), ForgetpwdActivity.class);
        startActivity(intent);
    }
    //从我的界面跳转到用户反馈界面
    public void tv_mine_useradvice() {
        Intent intent = new Intent(getActivity().getApplicationContext(), UserResponse.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.tv_mine_myinfo)
            tv_mine_myinfo();
        else if(viewId == R.id.tv_mine_appointment)
            tv_mine_appointment();
        else if(viewId == R.id.tv_mine_setting)
            tv_mine_setting();
        else if(viewId == R.id.tv_mine_alterpwd)
            tv_mine_alterpwd();
        else if(viewId == R.id.tv_mine_useradvice)
            tv_mine_useradvice();
        else if(viewId == R.id.tv_mine_update)
        {

        }
    }
}