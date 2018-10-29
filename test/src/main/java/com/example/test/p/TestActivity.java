package com.example.test.p;

import android.widget.Toast;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.example.test.MainActivity;
import com.example.test.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by xiaxuchen on 18-10-29.
 */

@EActivity(R.layout.activity_main)
public class TestActivity extends BaseActivity{


    @Override
    protected void afterInit() {
        ToastUtil.showLong("caonima");
    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public void initView() {

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
}
