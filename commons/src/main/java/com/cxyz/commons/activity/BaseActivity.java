package com.cxyz.commons.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.manager.ActivityStackManager;
import com.cxyz.commons.manager.ScreenManager;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.SpUtil;

/**
*
*<h1>1.注意事项</h1>
* <ul>
* <li>Activity对象必须持有一个IPresenter对象同时实现IView接口(MVP模式)
* <li>使用的时候若要重写onCreate方法一定要调用super.onCreate(savedInstanceState)，一般不用重写
* </ul>
*<h1>2.设置屏幕说明</h1>
* 如果需要设置有无标题栏、是否全屏、是否使用沉浸式状态栏、是否旋转屏幕，
* 请重写isShowTitle()、isFullScreen()、isStateBar()、isScreenRotate()方法进行设置
* <h1>3.方便使用的方法</h1>
* <li>当跳转到其他activity时需要传递数据时可以使用startActivity(Class<? extends BaseActivity> clz, Bundle bundle)方法
* <li>当跳转到其他activity时需要传递数据时的同时又需要返回数据时可以使用startActivityForResult(Class<? extends  BaseActivity> cls, Bundle bundle,int requestCode)
* <li>需要获取当前activity对象时，可以调用getActivity()方法
* <li>需要获取当前MyApp对象时，可以调用getMyApp()方法
* <li>需要获取当前SpUtil对象时，可以调用getSpUtil()方法
* </ul>
*<h1>4.抽象方法</h1>
* <ul>
* <li>initData()方法中对数据进行初始化
* <li>initView()方法中对控件进行初始化
* <li>setEvent()方法中对事件进行注册
* <li>通过createIPresenter方法创建IPresenter
* <li>getContentViewId()设置布局的id
* </ul>
* <h1>Created by 夏旭晨 on 2018/9/19.</h1>
* */
public abstract class BaseActivity<p extends IBasePresenter> extends Activity implements IBaseView {

    protected p iPresenter;

    private ScreenManager screenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onCreate");
        super.onCreate(savedInstanceState);
        //requestWindowFeature必须在setContentView之前调用
        baseInit();
        setContentView(getContentViewId());
        afterSetContent();
        initData();
        initView();
        setEvent();
    }

    /**
     * 在setContentView方法后紧接着调用
     */
    protected void afterSetContent() {
    }

    /***
     * 初始化
     */
    private void baseInit()
    {
        iPresenter = createIPresenter();
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        screenManager = ScreenManager.getInstance();
        if(iPresenter != null)
            iPresenter.attachV(this);
        initScreen();
    }

    /*初始化屏幕*/
    private void initScreen() {
        if(!isShowTitle()){
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        screenManager.setStatusBar(isStateBar(), this);
        screenManager.setScreenRotate(isScreenRotate(), this);
        screenManager.setFullScreen(isFullScreen(), this);
    }

    /*
    * 设置布局
    * */
    public abstract int getContentViewId();

    public abstract void initView();

    public abstract void initData();

    public abstract  void setEvent();

    /***
     * 获取所在activity
     * @return
     */
    protected Activity getActivity()
    {
        return this;
    }

    /**
     * 获取SpUtil对象
     * @return
     */
    protected SpUtil getSpUtil()
    {
        return SpUtil.getInstance();
    }

    /**
     * 获取MyApp对象
     * @return
     */
    protected MyApp getMyApp()
    {
        return (MyApp)getApplication();
    }

    /**
     * 是否设置标题栏
     *默认不显示，重写该方法来选择是否显示
     * @return
     */
    protected boolean isShowTitle() {
        return false;
    }


    /**
     * 设置是否显示状态栏
     * 默认不全屏，重写该方法来选择是否显示
     */
    protected boolean isFullScreen() {
        return false;
    }

    /**
     * 设置是否竖屏
     * 默认旋屏，重写该方法来选择
     */
    protected boolean isScreenRotate()
    {
        return true;
    }

    /***
     * 设置是否使用沉浸式状态栏
     * 默认为不使用，重写该方法来选择是否使用
     * @return
     */
    protected boolean isStateBar()
    {
        return false;
    }



    /**
     * [含有Bundle通过Class打开界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<? extends  BaseActivity> cls, Bundle bundle,int requestCode)
    {
        Intent intent = new Intent(); intent.setClass(this, cls);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<? extends BaseActivity> clz, Bundle bundle)
    {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null)
        {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 设置你的IPresenter为返回值，生命周期方法会被自动调用
     * @return
     */
    protected abstract  p createIPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        if(iPresenter!=null)
            iPresenter.onStart();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(iPresenter!=null)
            iPresenter.onStop();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(iPresenter!=null)
            iPresenter.onResume();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(iPresenter!=null)
            iPresenter.onPause();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onPasue");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(iPresenter!=null)
        {
            iPresenter.onDestory();
            iPresenter.detachV();
        }
        ActivityStackManager.getActivityStackManager().popActivity(this);
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onDestory");
    }

    /**
     * 当由其他activity打开时进行一个处理
     */
    protected void handleIntent(Intent intent)
    {

    };

}
