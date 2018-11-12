package com.cxyz.mine.activity;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.mine.R;
import com.cxyz.mine.fragment.MineFragment;

/**
 * Created by Administrator on 2018/11/6.
 */

public class TestActivity extends FragmentActivity {

    @Override
    public int getContentViewId() {
        return R.layout.testlayout;
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
    public int getFragmentContentId() {
        return R.id.fl_content;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return new MineFragment();
    }
}
