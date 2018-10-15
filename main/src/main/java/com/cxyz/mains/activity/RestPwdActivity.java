package com.cxyz.mains.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;

/**
 * Created by Administrator on 2018/10/13.
 */

public class RestPwdActivity extends BaseActivity {
    private EditText et_resetpwd_password;
    private EditText et_resetpwd_confirmpwd;
    private TitleView tv_resetpwd_title;
    private Button bt_restpwd_complete;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_resetpwd_layout;
    }

    @Override
    public void initView() {
        et_resetpwd_password=findViewById(R.id.et_resetpwd_password);
        et_resetpwd_confirmpwd=findViewById(R.id.et_resetpwd_confirmpwd);
        bt_restpwd_complete=findViewById(R.id.bt_restpwd_complete);
        tv_resetpwd_title=findViewById(R.id.tv_resetpwd_title);
        tv_resetpwd_title.setTitle("重置密码");

    }

    @Override
    public void initData() {


    }

    @Override
    public void setEvent() {
        bt_restpwd_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_resetpwd_password.setText("");
                et_resetpwd_confirmpwd.setText("");
                ToastUtil.showShort("已完成");
                Intent intent=new Intent(getApplicationContext(),Myinfo.class);
                startActivity(intent);
            }
        });
        tv_resetpwd_title.setOnClickListener(new TitleView.OnClickListener() {
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
}
