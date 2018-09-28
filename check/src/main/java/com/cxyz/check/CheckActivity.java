package com.cxyz.check;

import android.widget.Adapter;
import android.widget.GridView;

import com.cxyz.check.CheckPresenter.ICheckPresenter;
import com.cxyz.check.CheckView.ICheckView_Imp;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.activity.BaseActivity;

/**
 * Created by 28058 on 2018/9/25.
 */

public class CheckActivity extends BaseActivity<ICheckPresenter> implements IBaseView {

    ICheckView_Imp mView;
    Adapter gv_Adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_check_layout;
    }

    @Override
    public void initView() {
        mView=new ICheckView_Imp();
        mView.initGridView(gv_Adapter,(GridView) findViewById(R.id.check_gv));
    }

    @Override
    public void initData() {
        mView=new ICheckView_Imp();
        mView.initActivity(getActivity());
        gv_Adapter=new ICheckPresenter().getGridView(getActivity());

    }

    @Override
    public void setEvent() {

        iPresenter.setGridViewListener((GridView)findViewById(R.id.check_gv),getActivity());
    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenter();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
