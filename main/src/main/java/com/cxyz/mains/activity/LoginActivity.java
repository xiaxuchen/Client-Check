package com.cxyz.mains.activity;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.BitmapUtil;
import com.cxyz.commons.utils.ScreenUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mains.R;
import com.cxyz.mains.ipresenter.ILoginPresenter;
import com.cxyz.mains.ipresenter.ipresenterimpl.ILoginPresenterImpl;
import com.cxyz.mains.iview.ILoginView;

import java.io.IOException;

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

    /**
     * 背景
     */
    private LinearLayout ll_back;

    @Override
    public int getContentViewId() {
        return R.layout.activity_logins_layout;
    }

    @Override
    public void initView() {
        AssetManager assetManager = getAssets();
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        rg_type = (RadioGroup) findViewById(R.id.rg_type);
        tv_forget_pwd = findViewById(R.id.tv_forget_pwd);
        ll_back = findViewById(R.id.ll_back);

        et_username.setText(getSpUtil().getString("username",""));

        //加载背景图片
        try {
            ll_back.setBackgroundDrawable(BitmapUtil.bitmapToDrawable(getActivity(),BitmapUtil.
                    getBitmapFromStream(getActivity(), getAssets().open("logo.jpg"),
                            ScreenUtil.getScreenHeight(getActivity()),ScreenUtil.
                                    getScreenWidth(getActivity()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 从输入框和单选框中获取数据后登录
                 */
                iPresenter.login(et_username.getText().toString(),et_password.getText().toString(),rg_type
                        .getCheckedRadioButtonId()==R.id.rb_student?0:1);
            }
        });
    }

    @Override
    protected ILoginPresenter createIPresenter() {
        return new ILoginPresenterImpl();
    }

    @Override
    protected boolean isFullScreen() {
        return true;
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
