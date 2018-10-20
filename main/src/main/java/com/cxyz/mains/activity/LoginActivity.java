package com.cxyz.mains.activity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mains.R;
import com.cxyz.mains.ipresenter.ILoginPresenter;
import com.cxyz.mains.ipresenter.ipresenterimpl.ILoginPresenterImpl;
import com.cxyz.mains.iview.ILoginView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

@Route(path = "/main/LoginActivity")
public class LoginActivity extends BaseActivity<ILoginPresenter> implements ILoginView{

    private EditText et_id;
    private EditText et_pwd;
    private TextView tv_forgetpwd;
    private Button btn_login;
    private RadioGroup rg_type;
    private ProgressDialog dialog;

    @Override
    public int getContentViewId() {
        return R.layout.activity_logins_layout;
    }

    @Override
    public void initView() {
        et_id = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);
        tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
        btn_login = (Button) findViewById(R.id.bt_login);
        rg_type = (RadioGroup) findViewById(R.id.rg_teacherstudent);

        et_id.setText(getSpUtil().getString("username",""));
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 从输入框和单选框中获取数据后登录
                 */
                iPresenter.login(et_id.getText().toString(),et_pwd.getText().toString(),rg_type
                        .getCheckedRadioButtonId()==R.id.rb_student?0:1);
            }
        });
    }

    @Override
    protected ILoginPresenter createIPresenter() {
        return new ILoginPresenterImpl();
    }

    @Override
    public void showLoadingView() {
        super.showLoadingView();
    }

    @Override
    public void hideLoadingView() {
        super.hideLoadingView();
    }


    @Override
    public void loginSuccess() {
        ToastUtil.showShort("登录成功");
        startActivity(HomeActivity.class);
        this.finish();
    }

    @Override
    public void loginFail(String message) {
        ToastUtil.showShort(message);
    }
}
