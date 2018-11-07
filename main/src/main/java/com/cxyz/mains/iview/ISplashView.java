package com.cxyz.mains.iview;

import com.cxyz.commons.IView.IBaseView;

import java.io.File;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public interface ISplashView extends IBaseView {
    /**
     * 显示更新的视图
     * @param versionCode 版本号
     * @param des 更新说明
     * @param url 新版本更新地址
     */
    public void showUpdateView(int versionCode,String des,String url);

    /**
     * 显示下载进度
     * @param progress 当前进度
     * @param max 最大进度
     */
    public void showDownload(int progress,int max);

    /**
     * 安装app
     */
    public void installApp(File app);

    /**
     * 退出闪屏页面
     */
    public void exitSplash();

    /**
     * 发现没有更新回调
     */
    public void noUpdate();

    /**
     * 显示自动登录成功
     */
    public void autoLoginSuccess();

    /**
     * 显示自动登录失败
     */
    public void autoLoginFail(String info);
}
