package com.cxyz.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.mine.IPresenter.presenter.IMinePresenter;
import com.cxyz.mine.R;
import com.cxyz.mine.activity.AppointmentActivity;
import com.cxyz.mine.activity.MyinfoActivity;
import com.cxyz.mine.activity.SettingActivity;

/**
 * Created by Administrator on 2018/9/25.
 */

public class MineFragment extends BaseFragment<IMinePresenter> implements View.OnClickListener {
    private TextView tvmyinfo, tvappointment, tvsetting;
    private Button btexitlogin;


    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_layout;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tvmyinfo = findViewById(R.id.tvmyinfo);
        tvappointment = findViewById(R.id.tvappointment);
        tvsetting = findViewById(R.id.tvsetting);
        btexitlogin = findViewById(R.id.btexitlogin);
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
        tvmyinfo.setOnClickListener(this);
        tvappointment.setOnClickListener(this);
        tvsetting.setOnClickListener(this);
        btexitlogin.setOnClickListener(this);
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
    public void tvsetting() {
        Intent intent = new Intent(getActivity().getApplicationContext(), SettingActivity.class);

        startActivity(intent);
    }

    //从我的界面跳转到预约请假界面
    public void tvappointment() {
        Intent intent = new Intent(getActivity().getApplicationContext(), AppointmentActivity.class);
        startActivity(intent);
    }

    //从我的界面跳转到个人信息界面
    public void tvmyinfo() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MyinfoActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.tvmyinfo)
            tvmyinfo();
        else if(viewId == R.id.tvappointment)
            tvappointment();
        else if(viewId == R.id.tvsetting)
            tvsetting();
    }
}