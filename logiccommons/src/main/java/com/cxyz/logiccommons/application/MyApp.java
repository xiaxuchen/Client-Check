package com.cxyz.logiccommons.application;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.application.BaseApplication;
import com.cxyz.commons.utils.CrashHandler;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/9/20.
 */

public class MyApp extends BaseApplication {

    private static RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter(true);
        setupLeakCanary();
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

    //注册LeakCanary
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context)
    {
        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
        return refWatcher;
    }


}
