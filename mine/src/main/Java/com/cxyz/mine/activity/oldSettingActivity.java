package com.cxyz.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;
/**
 * Created by Administrator on 2018/9/25.
 */

public class oldSettingActivity extends BaseActivity <IBasePresenter>implements View.OnClickListener {
    @Override
    public int getContentViewId() {
        return R.layout.activity_setting_layout;
    }

    private Switch stsettingswitch;
    private TitleView tvsetting_title;
    private TextView tvsetting_update, tvsetting_response;
    @Override
    public void initView() {

        tvsetting_update = (TextView) findViewById(R.id.tvsetting_update);
        tvsetting_response= (TextView) findViewById(R.id.tvsetting_response);
        stsettingswitch= (Switch) findViewById(R.id.stsettingswitch);
        tvsetting_title= (TitleView) findViewById(R.id.tvsetting_title);
        tvsetting_title.setTitle("设置");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tvsetting_update.setOnClickListener(this);
        tvsetting_response.setOnClickListener(this);
        stsettingswitch.setOnClickListener(this);
        tvsetting_title.setOnClickListener(new TitleView.OnClickListener() {
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



    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.tvsetting_update)
            tvsetting_update();
        else if(viewId == R.id.tvsetting_response)
            tvsetting_response();
        else if(viewId == R.id.stsettingswitch)
            stsettingswitch(stsettingswitch);

        }

    //用户反馈activity跳转
    public void tvsetting_response(){
        Intent intent=new Intent(getApplicationContext(),ResponseActivity.class);
        startActivity(intent);
    }

    //版本更新监听
    public void   tvsetting_update(){
        ToastUtil.showLong("已是最新版本");
    }

    //更新推送开关switch监听
    public void stsettingswitch(Switch s) {
        if (s.isChecked()) {
            ToastUtil.showShort("已开启");}
        else {
            ToastUtil.showShort("已关闭");
        }
    }

}
