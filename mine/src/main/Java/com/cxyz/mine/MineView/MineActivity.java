package com.cxyz.mine.MineView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePresenter.presenter.IMinePresenter;
import com.cxyz.mine.MineView.mineactivity.AppointmentActivity;
import com.cxyz.mine.MineView.mineactivity.MyinfoActivity;
import com.cxyz.mine.MineView.mineactivity.SettingActivity;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class MineActivity extends BaseActivity<IBasePresenter> implements View.OnClickListener {
    private TitleView tvtitle;
    private TextView tvmyinfo, tvappointment, tvsetting;
    private Button btexitlogin;

    public int getContentViewId() {
        return R.layout.activity_mine_layout;
    }

    @Override
    public void initView() {
        tvmyinfo = (TextView) findViewById(R.id.tvmyinfo);
        tvappointment = (TextView) findViewById(R.id.tvappointment);
        tvsetting = (TextView) findViewById(R.id.tvsetting);
        btexitlogin = (Button) findViewById(R.id.btexitlogin);
        tvtitle = (TitleView) findViewById(R.id.tvtitle);
        tvtitle.setTitle("我的");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tvmyinfo.setOnClickListener(this);
        tvappointment.setOnClickListener(this);
        tvsetting.setOnClickListener(this);
        btexitlogin.setOnClickListener(this);
        tvtitle.setOnClickListener(new TitleView.OnClickListener() {
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
        return new IMinePresenter() {
            @Override
            public IBaseModel createModel() {
                return super.createModel();
            }
        };
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
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);

        startActivity(intent);
    }

    //从我的界面跳转到预约请假界面
    public void tvappointment() {
        Intent intent = new Intent(getApplicationContext(), AppointmentActivity.class);
        startActivity(intent);
    }

    //从我的界面跳转到个人信息界面
    public void tvmyinfo() {
        Intent intent = new Intent(getApplicationContext(), MyinfoActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.tvmyinfo:tvmyinfo();break;
            case R.id.tvappointment:tvappointment();break;
            case R.id.tvsetting:tvsetting();break;
        }
    }
}