package com.cxyz.commons.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.application.BaseApplication;
import com.cxyz.commons.manager.ActivityStackManager;
import com.cxyz.commons.manager.ScreenManager;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.SpUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.squareup.leakcanary.RefWatcher;

/**
*
*<h1>1.注意事项</h1>
* <ul>
* <li>Activity对象必须持有一个IPresenter对象同时实现IView接口(MVP模式)</li>
* <li>使用的时候若要重写onCreate方法一定要调用super.onCreate(savedInstanceState)，一般不用重写</li>
* </ul>
*<h1>2.设置屏幕说明</h1>
* 如果需要设置有无标题栏、是否全屏、是否使用沉浸式状态栏、是否旋转屏幕，
* 请重写isShowTitle()、isFullScreen()、isStateBar()、isScreenRotate()方法进行设置
* <h1>3.方便使用的方法</h1>
* <li>当跳转到其他activity时需要传递数据时可以使用startActivity(Class<? extends BaseActivity> clz, Bundle bundle)方法</li>
* <li>当跳转到其他activity时需要传递数据时的同时又需要返回数据时可以使用startActivityForResult(Class<? extends  BaseActivity> cls, Bundle bundle,int requestCode)</li>
* <li>需要获取当前activity对象时，可以调用getActivity()方法</li>
* <li>需要获取当前MyApp对象时，可以调用getMyApp()方法</li>
* <li>需要获取当前SpUtil对象时，可以调用getSpUtil()方法</li>
* </ul>
*<h1>4.抽象方法</h1>
* <ul>
* <li>initData()方法中对数据进行初始化</li>
* <li>initView()方法中对控件进行初始化</li>
* <li>setEvent()方法中对事件进行注册</li>
* <li>通过createIPresenter方法创建IPresenter</li>
* <li>getContentViewId()设置布局的id</li>
* </ul>
* <h1>Created by 夏旭晨 on 2018/9/19.</h1>
* */
public abstract class BaseActivity<p extends IBasePresenter> extends Activity implements IBaseView {

    /**
     * Activity持有的IPresenter引用，通过重写createIPresenter()方法获取实例
     */
    protected p iPresenter;

    /**
     * 加载的进度条
     */
    private QMUITipDialog loadingView;

    private ScreenManager screenManager;

    /**
     * 进行Activity的初始化，如果createIPresenter()方法返回了一个IPresenter对象，
     * 则会自动注册到Activity的生命周期，并且将IView依附到IPresenter
     * @param savedInstanceState 临时存储数据的bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onCreate");
        //requestWindowFeature必须在setContentView之前调用
        baseInit();
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if(getIntent()!=null)
            handleIntent(getIntent());
        afterSetContent();
        initData();
        initView();
        setEvent();
        afterInit();
    }

    /**
     * 在setEvent之后调用
     */
    protected  void afterInit(){};

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
    * 通过重写此方法设置布局文件
    * */
    public abstract int getContentViewId();

    /**
     * 在此方法中进行一些初始化view的操作
     */
    public abstract void initView();

    /**
     * 在此方法中进行一些初始化数据的操作
     */
    public abstract void initData();

    /**
     * 在此方法中设置事件
     */
    public abstract  void setEvent();

    /***
     * 获取所在activity
     * @return this
     */
    protected Activity getActivity()
    {
        return this;
    }

    /**
     * 获取SpUtil对象
     * @return spUtil对象
     */
    protected SpUtil getSpUtil()
    {
        return SpUtil.getInstance();
    }

    /**
     * 获取MyApp对象
     * @return MyApp对象
     */
    protected BaseApplication getMyApp()
    {
        return (BaseApplication)getApplication();
    }

    /**
     * 是否设置标题栏
     * 默认不显示，重写该方法来选择是否显示
     * @return
     */
    protected boolean isShowTitle() {
        return false;
    }


    /**
     * 设置是否全屏
     * 默认不全屏，重写该方法来选择是否显示
     */
    protected boolean isFullScreen() {
        return false;
    }

    /**
     * 设置是否竖屏
     * 默认竖屏屏，重写该方法来选择
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
        return true;
    }



    /**
     * [含有Bundle通过Class打开界面]
     *
     * @param cls 所跳转的activity的class
     * @param bundle 需要传递的数据
     * @param requestCode 请求码
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
     * @param clz 所跳转的activity的class
     * @param bundle 需要传递的数据
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

    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在activity的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    protected void onStart() {
        super.onStart();
        if(iPresenter!=null)
            iPresenter.onStart();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onStart");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在activity的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    protected void onStop() {
        super.onStop();
        if(iPresenter!=null)
            iPresenter.onStop();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onStop");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在activity的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(iPresenter!=null)
            iPresenter.onResume();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onResume");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在activity的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(iPresenter!=null)
            iPresenter.onPause();
        LogUtil.i_withoutPre(getActivity().getClass().getSimpleName()+"--onPasue");
    }
    /**
     * 生命周期方法，在其中回调了IPresenter中相应的方法
     * 在其中还打印了当前生命周期的日志，日志内容为类名+方法名+生命周期
     * 本来写在activity的生命周期方法中的代码可以写到IPresenter中
     */
    @Override
    protected void onDestroy() {
        //注册LeakCanary
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getActivity());
        //注销EventBus
        refWatcher.watch(this);
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
     * 当由其他组件跳转并需要根据intent带来的数据显示view时调用
     * @param intent 跳转时所用的
     */
    protected void handleIntent(Intent intent)
    {
    };

    /**
     * 直接通过class文件跳转
     * @param clazz
     */
    public void startActivity(Class clazz)
    {
        startActivity(clazz,null);
    }

    /**
     * loadingview是否可以取消，默认不可以
     * @return
     */
    protected boolean loadingViewCancelable(){
        return false;
    }

    /**
     * 通过设置此方法来loadingview的设置文字
     * @return
     */
    protected String loadingText()
    {
        return "正在加载";
    }

    private QMUITipDialog createLoadingView()
    {
        QMUITipDialog.Builder builder = new QMUITipDialog.Builder(getActivity());
        builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING);
        builder.setTipWord(loadingText());
        return builder.create(loadingViewCancelable());
    }


    /**
     * 可以通过重写loadingText、loadingViewCancelable方法设置是否可取消，和文字
     */
    @Override
    public void showLoadingView() {
        if(loadingView == null)
            loadingView = createLoadingView();
        loadingView.show();
    }

    @Override
    public void hideLoadingView() {
        if(loadingView == null)
            loadingView = createLoadingView();
        loadingView.dismiss();
    }
}
