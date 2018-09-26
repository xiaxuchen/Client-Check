package com.cxyz.mine.M_settingActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class Setting_responseActivity extends BaseActivity implements View.OnClickListener{
    @Override
    public int getContentViewId() {
        return R.layout.setting_userresponse_layout;
    }

    @Override
    public void initView() {
        EditText m_setting_eduserresponse;
        Button m_setting_submit;
        m_setting_eduserresponse= (EditText) findViewById(R.id.m_setting_eduserresponse);
        m_setting_submit= (Button) findViewById(R.id.m_setting_submit);
        m_setting_eduserresponse.setOnClickListener(this);
        m_setting_submit.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_setting_eduserresponse:m_setting_eduserresponse();break;
            case R.id.m_setting_submit:m_setting_submit();break;
        }

    }

    public void m_setting_eduserresponse() {
    }
    public void m_setting_submit(){
        ToastUtil.showShort("已提交");
    }
}
