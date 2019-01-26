package com.cxyz.mains.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.typevalue.UserType;
import com.cxyz.mains.R;
import com.cxyz.mains.ipresenter.ILoginPresenter;
import com.cxyz.mains.ipresenter.ipresenterimpl.ILoginPresenterImpl;
import com.cxyz.mains.iview.ILoginView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

@Route(path = "/main/LoginActivity")
public class LoginActivity extends BaseActivity<ILoginPresenter> implements ILoginView{

    /**
     * 用户id
     */
    private EditText et_username;
    /**
     * 密码
     */
    private EditText et_password;
    /**
     * 忘记密码
     */
    private TextView tv_forget_pwd;
    /**
     * 登录
     */
    private Button bt_login;
    /**
     * 用户类型
     */
    private RadioGroup rg_type;

    @Override
    public int getContentViewId() {
        return R.layout.activity_logins_layout;
    }

    @Override
    public void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);
        rg_type = findViewById(R.id.rg_type);
        tv_forget_pwd = findViewById(R.id.tv_forget_pwd);

        et_username.setText(getSpUtil().getString("username",""));
        if(SpUtil.getInstance().getInt("type",UserType.STUDENT)== UserType.TEACHER)
        {
            RadioButton rb_tea = findViewById(R.id.rb_teacher);
            rb_tea.setChecked(true);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        bt_login.setOnClickListener(v -> {
            /**
             * 从输入框和单选框中获取数据后登录
             */
            iPresenter.login(et_username.getText().toString(),et_password.getText().toString(),rg_type
                    .getCheckedRadioButtonId()==R.id.rb_student?0:1);
        });
    }

    @Override
    protected ILoginPresenter createIPresenter() {
        return new ILoginPresenterImpl();
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
