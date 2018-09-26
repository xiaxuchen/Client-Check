package com.cxyz.check;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;

/**
 * Created by 28058 on 2018/9/25.
 */

public class CheckActivity extends BaseActivity<IBasePresenter> implements IBaseView {

    IBaseView mView;
    IBasePresenter mPresenter;


    @Override
    public int getContentViewId() {
        return R.layout.activity_check_layout;
    }

    @Override
    public void initView() {
        ToastUtil.init(getApplicationContext());
        new IcheckFunctionImple().initGridView(getActivity());
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
