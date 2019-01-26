package com.cxyz.untilchecked.tinker.applicationlike;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.application.BaseApplication;
import com.cxyz.commons.context.ContextManager;
import com.cxyz.commons.utils.CrashHandler;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.constant.Constant;
import com.cxyz.untilchecked.BuildConfig;
import com.cxyz.untilchecked.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2019/1/23.
 */

public class MyApplication extends BaseApplication {

    private ApplicationLike tinkerApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        initTinker();
        initToast();
        initCrach();
        initSpUtil();
        initRefresh();
        initCommonOkHttpClient();
        initARouter(true);
        ContextManager.setContext(getApplicationContext());
    }

    private void initTinker()
    {

        if (BuildConfig.TINKER_ENABLE) {
            // 我们可以从这里获得Tinker加载过程的信息
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

            // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
            TinkerPatch.init(tinkerApplicationLike)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true);

            // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            new FetchPatchHandler().fetchPatchWithInterval(3);
        }
    }


    private void initJpush()
    {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
    }

    /**
     * 初始化ToastUtil
     */
    private void initToast()
    {
        //初始化ToastUtils
        ToastUtil.init(getApplicationContext());
    }

    /**
     * 初始化SpUtil
     */
    private void initSpUtil()
    {
        //初始化SpUtils
        SpUtil.init(getApplicationContext());
    }

    /**
     * 初始化全局异常
     */
    private void initCrach()
    {
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance().init(getApplicationContext(), Constant.ROOT_URL+"/envir/uploadBugs"));
    }


    /**
     * 初始化okhttp
     */
    private void initCommonOkHttpClient()
    {
        //初始化CommonOkHttp
        CommonOkHttpClient.init(getApplicationContext());
    }

    /**
     * 初始化ARouter
     * @param isDebug 是否显示错误
     */
    private void initARouter(boolean isDebug)
    {
        if(isDebug)
        {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    /**
     * 初始化refresh
     */
    private void initRefresh()
    {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.gray, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

}
