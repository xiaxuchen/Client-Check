package com.cxyz.mains.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
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
    private TitleView tv_title;

    @Override
    public int getContentViewId() {
        return R.layout.activity_logins_layout;
    }

    @Override
    public void initView() {
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        rg_type = (RadioGroup) findViewById(R.id.rg_type);
        tv_title = (TitleView) findViewById(R.id.tv_title);
        tv_title.setTitle("登录");
        tv_title.setBack(0,"");
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
                        .getCheckedRadioButtonId()==R.id.rb_stu?0:1);
            }
        });
        tv_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),FogetpwdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected ILoginPresenter createIPresenter() {
        return new ILoginPresenterImpl();
    }

    @Override
    public void showLoadingView() {
        dialog = new ProgressDialog(getActivity());
        dialog.setIcon(R.mipmap.common_logo);
        dialog.setTitle("正在登录...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void hideLoadingView() {
        if(dialog != null)
            dialog.dismiss();
    }


    @Override
    public void loginSuccess() {
        ToastUtil.showShort("登录成功");
        startActivity(HomeActivity.class);
    }

    @Override
    public void loginFail(String message) {
        ToastUtil.showShort(message);
    }
}
