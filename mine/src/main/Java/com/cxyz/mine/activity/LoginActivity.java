package com.cxyz.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/28.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private Button btlogin;
    EditText etlogin_username;
    EditText etlogin_password;
    @Override
    public int getContentViewId() {
        return R.layout.activity_login_layout;
    }

    @Override
    public void initView() {
        btlogin= (Button) findViewById(R.id.btlogin);
        etlogin_username= (EditText) findViewById(R.id.etlogin_username);
        etlogin_password= (EditText) findViewById(R.id.etlogin_password);

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btlogin.setOnClickListener(this);

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
        switch(v.getId()){
            case R.id.btlogin:btlogin();
        }

    }
    public void btlogin(){
        Intent intent=new Intent(this,MineActivity.class);
        startActivity(intent);
    }
}
