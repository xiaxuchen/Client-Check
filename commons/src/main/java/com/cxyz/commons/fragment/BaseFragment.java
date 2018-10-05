package com.cxyz.commons.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxyz.commons.activity.FragmentActivity;

/**
 * Created by 夏旭晨 on 2018/9/21.
 * <h2>注意事项<h2/>
 * <h1>BaseFragment的子类只能通过newInstance()方法来获取</h1>
 * <ul>
 * <li>如果不需要传递参数直接在newInstance方法中return子类的对象</li>
 * <li>如果需要传递参数，则如下：</li>
 * </ul><br></br>
 * public static FirstFragment newInstance(Bundle bundle){<br></br>
 *      FirstFragment fragment = new FirstFragment(); <br></br>
 *      Bundle bundle = new Bundle();<br></br>
 *      bundle.putSerializable(FIRST_FRAGMENT, msg); <br></br>
 *      fragment.setArguments(bundle); <br></br>
 *      return fragment;<br></br>
 * }<br></br>
 *注：其中FIRST_FRAGMENT是你自定义的标记，BaseFragment搭配FragmentActivity使用
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 持有的所在Activity的引用
     */
    protected FragmentActivity mActivity;

    /**
     * Fragment的布局转换的view
     */
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;

    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 获取宿主Activity
     */
    protected FragmentActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (FragmentActivity) activity;
    }

    /**
     * 显示fragment，并将Fragment放入Fragment栈中，类似于startActivity
     * @param fragment 需要显示的Fragment
     */
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    /**
     * 将位于栈首的Fragment退栈，类似与Activity的Finish
     */
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initData(getArguments());
        initView(mRootView,savedInstanceState);
        mIsPrepare = true;
        onLazyLoad();
        setListener();
        return mRootView;
    }

    /**
     * 获取fragment布局文件ID
     * @return fragment的布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 进行数据初始化
     */
    protected abstract void initData(Bundle bundle);

    /**
     * 初始化view
     * @param view mRootView
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 设置监听事件
     */
    protected abstract void setListener();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.mIsVisible = isVisibleToUser;
        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行
     */
    protected void onLazyLoad() {
    }

    /**
     * 不需要强转的findViewByid方法
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return (T) mRootView.findViewById(id);
    }
}
