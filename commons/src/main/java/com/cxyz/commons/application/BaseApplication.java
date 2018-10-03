package com.cxyz.commons.application;

import android.app.Application;

import com.cxyz.commons.manager.ActivityStackManager;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public class BaseApplication extends Application {

    private ActivityStackManager stackManager;

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public ActivityStackManager manageActivity()
    {
        if(stackManager != null)
            return stackManager;
        return null;
    }

    public static Application getApplication()
    {
        return instance;
    }
}

