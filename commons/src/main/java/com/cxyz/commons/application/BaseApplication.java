package com.cxyz.commons.application;

import android.support.multidex.MultiDexApplication;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public class BaseApplication extends MultiDexApplication {

//    //检测内存泄漏利器
//    private static RefWatcher refWatcher;
//    //activity栈
//    private static BaseApplication instance;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        refWatcher= setupLeakCanary();
//        instance = this;
//    }
//
//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this))
//        {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//
//    public static RefWatcher getRefWatcher(Context context)
//    {
//        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
//        return BaseApplication.refWatcher;
//    }
//
//    public static Application getApplication()
//    {
//        return instance;
//    }
}

