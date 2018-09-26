package com.cxyz.untilchecked;


import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class HomeActivity extends BaseActivity<IExamplePresenter> implements IExampleView{

    @Override
    public int getContentViewId() {
        return R.layout.activity_home_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        iPresenter.login("", "");
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IExamplePresenter createIPresenter() {
        return new IExamplePresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showLogin() {
        ToastUtil.showShort("登录成功");
    }

    @Override
    public void showError() {
        ToastUtil.showShort("登录失败");
    }
}

