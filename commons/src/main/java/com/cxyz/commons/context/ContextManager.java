package com.cxyz.commons.context;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 獲取context
 * Created by Administrator on 2019/1/2.
 */

public class ContextManager {

    private static Context context;

    //检测内存泄漏利器
    private static RefWatcher refWatcher;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ContextManager.context = context;
        initIconify();
        refWatcher = setupLeakCanary();
    }

    private static void initIconify()
    {
        Iconify.with(new FontAwesomeModule());
    }

    private static RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(getContext()))
        {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install((Application) getContext());
    }

    public static RefWatcher getRefWatcher()
    {
        return refWatcher;
    }
}
