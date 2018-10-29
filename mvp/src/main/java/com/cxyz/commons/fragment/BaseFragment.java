package com.cxyz.commons.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.R;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.log.core.LogManager;

import org.androidannotations.annotations.EFragment;

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
public abstract class BaseFragment<p extends IBasePresenter> extends Fragment implements IBaseView{

    /**
     * 持有的所在Activity的引用
     */
    protected FragmentActivity mActivity;

    /**
     * Fragment的布局转换的view
     */
    protected View mRootView;

    /**
     * 持有的IPresenter的引用
     */
    protected p iPresenter;

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
    public void onCreate(Bundle savedInstanceState) {
        iPresenter = createIPresenter();
        super.onCreate(savedInstanceState);
        if(iPresenter!=null)
            iPresenter.attachV(this);
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
     * 获取IPresenter
     * @return
     */
    protected abstract p createIPresenter();

    /**
     * 设置监听事件
     */
    protected abstract void setListener();

    /**
     * 在初始化之后调用
     */
    protected void afterInit(){}

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

    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在fragment的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    public void onStart() {
        super.onStart();
        if(iPresenter!=null)
            iPresenter.onStart();
        LogManager.noPrefix(getActivity().getClass().getSimpleName()+"--onStart");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在fragment的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    public void onStop() {
        super.onStop();
        if(iPresenter!=null)
            iPresenter.onStop();
        LogManager.noPrefix(getActivity().getClass().getSimpleName()+"--onStop");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在fragment的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    public void onResume() {
        super.onResume();
        if(iPresenter!=null)
            iPresenter.onResume();
        LogManager.noPrefix(getActivity().getClass().getSimpleName()+"--onResume");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在fragment的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    public void onPause() {
        super.onPause();
        if(iPresenter!=null)
            iPresenter.onPause();
        LogManager.noPrefix(getActivity().getClass().getSimpleName()+"--onPasue");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在fragment的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(iPresenter!=null)
        {
            iPresenter.onDestory();
            iPresenter.detachV();
        }
        LogManager.noPrefix(getActivity().getClass().getSimpleName()+"--onDestory");
    }

}
