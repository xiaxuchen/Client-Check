package com.cxyz.untilchecked.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.untilchecked.R;
import com.cxyz.untilchecked.ipresenter.ILoginPresenter;
import com.cxyz.untilchecked.ipresenter.ipresenterimpl.ILoginPresenterImpl;
import com.cxyz.untilchecked.iview.ILoginView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class LoginActivity extends BaseActivity<ILoginPresenter> implements ILoginView{

    private EditText et_id;
    private EditText et_pwd;
    private TextView tv_forgetpwd;
    private Button btn_login;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login_layout;
    }

    @Override
    public void initView() {
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.login(et_id.getText().toString(),et_pwd.getText().toString(),0);
            }
        });
    }

    @Override
    protected ILoginPresenter createIPresenter() {
        return new ILoginPresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFail(String message) {

    }
}
