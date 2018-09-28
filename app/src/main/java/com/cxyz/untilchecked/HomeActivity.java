package com.cxyz.untilchecked;


import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;


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

