package com.cxyz.homepage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.homepage.R;
import com.cxyz.homepage.ipresenter.TaskInfoPresenter;
import com.cxyz.homepage.ipresenter.mpl.TakInfoPresenterImpl;
import com.cxyz.homepage.iview.TaskInfosPagerView;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;


/**
 * Created by 鱼塘主 on 2018/9/25.
 */
@Route(path="/homepage/HomeFragment")
public class HomeFragment extends BaseFragment<TaskInfoPresenter> implements TaskInfosPagerView{

    //虚拟(假)信息
    private Button btn_goto;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle bundle) {

    }
    /**
     * 初始化view的
     * @param view mRootView
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    }
    @Override
    protected TaskInfoPresenter createIPresenter() {
        return new TakInfoPresenterImpl();
    }

    /**
     * 设置监听的
     */
    @Override
    protected void setListener() {
    }

    /**
     * 显示加载的
     */
    @Override
    public void showLoadingView() {
    }

    /**
     * 隐藏加载的
     */
    @Override
    public void hideLoadingView() {
    }

    /**
     * 接口回调的
     * @param taskInfosData
     */
    @Override
    public void setTaskInfosData(List<TaskInfo> taskInfosData) {
    }

}
