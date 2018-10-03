package com.cxyz.untilchecked;

import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/9/25.
 */

public class Example_FragmentActivity extends FragmentActivity<IExamplePresenter>{
    @Override
    public int getFragmentContentId() {
        return R.id.content;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return Example_Fragmment.newInstance();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_example_layout;
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
    protected IExamplePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
