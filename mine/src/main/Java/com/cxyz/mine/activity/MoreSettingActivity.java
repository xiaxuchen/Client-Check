package com.cxyz.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class MoreSettingActivity extends BaseActivity {
    private LinearLayout ll_exit;
    private LinearLayout ll_expand;
    private Switch sw__udpate;
    private TitleView tv_title;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_moresetting_layout;
    }

    @Override
    public void initView() {
        ll_exit=findViewById(R.id.ll_exit);
        ll_expand=findViewById(R.id.ll_expand);
        sw__udpate = findViewById(R.id.sw_update);
        tv_title=findViewById(R.id.tv_title);
        tv_title.setTitle("更多设置");

        sw__udpate.setChecked(getSpUtil().getBoolean("update",true));
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        ll_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ExpandFunctionActivity.class);
                startActivity(intent);
            }
        });
        ll_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到考勤界面
                ARouter.getInstance().build("/main/LoginActivity").navigation();
                UserManager.getInstance().setUser(null);
                getSpUtil().remove("pwd");
                finish();
            }
        });
        tv_title.setOnClickListener(new TitleView.OnClickListenerWrapper(){
            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        sw__udpate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getSpUtil().putBoolean("update",b);
            }
        });

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
}