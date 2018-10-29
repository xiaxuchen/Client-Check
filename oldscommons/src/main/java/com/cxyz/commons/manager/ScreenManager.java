package com.cxyz.commons.manager;


import android.content.pm.ActivityInfo;
import android.view.WindowManager;

import com.cxyz.commons.activity.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * 屏幕管理类
 */
public class ScreenManager {
    private static ScreenManager instance;

    private ScreenManager() {
    }

    public synchronized static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * 窗口全屏
     */
    public void setFullScreen(boolean isChange, BaseActivity mActivity) {
        if (!isChange) {
            return;
        }
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * [沉浸状态栏]
     */
    public void setStatusBar(boolean isChange, BaseActivity activity) {
        if(!isChange)
            return;
        QMUIStatusBarHelper.translucent(activity);
    }

    /***
     * 设置旋转屏幕
     * @param isChange 是否可旋转
     * @param mActivity 当前的activity
     */
    public void setScreenRotate ( boolean isChange, BaseActivity mActivity){
        if (!isChange) {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}

