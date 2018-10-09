package com.cxyz.mains.iview;

import com.cxyz.commons.IView.IBaseView;

import java.io.File;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public interface ISplashView extends IBaseView {
    /**
     * 显示更新的视图
     * @param version 版本号
     * @param des 更新说明
     * @param url 新版本更新地址
     */
    public void showUpdateView(String version,String des,String url);

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

    public void finishSplash();
}
