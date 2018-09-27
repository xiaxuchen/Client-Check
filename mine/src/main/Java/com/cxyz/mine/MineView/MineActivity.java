package com.cxyz.mine.MineView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePresenter.presenter.IMinePresenter;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class MineActivity extends BaseActivity implements View.OnClickListener {
    private IMinePresenter iMinePresenter;
    private TitleView tvtitle;
    private  TextView tvmyinfo,tvappointment, tvsetting;
    private   Button btexitlogin;
    public int getContentViewId() {
        return R.layout.activity_mine_layout;
    }

    @Override
    public void initView() {
        iMinePresenter=new IMinePresenter();
        tvmyinfo= (TextView) findViewById(R.id.tvmyinfo);
        tvappointment =(TextView) findViewById(R.id.tvappointment);
        tvsetting=(TextView)findViewById(R.id.tvsetting);
        btexitlogin=(Button)findViewById(R.id.btexitlogin);
        tvtitle= (TitleView) findViewById(R.id.tvtitle);
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
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
    //监听事件








    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.tvmyinfo: iMinePresenter.tvmyinfo(getApplicationContext());break;
            case R.id.tvappointment:iMinePresenter.tvappointment(getApplicationContext());break;
            case R.id.tvsetting:iMinePresenter.tvsetting(getApplicationContext());break;
        }


    }
}