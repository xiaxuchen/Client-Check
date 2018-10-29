package com.cxyz.autoupdate.core;

import com.cxyz.autoupdate.UpdateDownloadListener;

import java.io.Serializable;

/**
 * Created by xiaxuchen on 18-10-27.
 */

public class UpdateInfo implements Serializable {

    //下载成功的提示信息,默认为"下载完成"
    public String success;
    //下载失败的提示信息,默认为"下载失败"
    public String fail;
    //正在下载的提示信息,默认为"正在下载"
    public String progressing;
    //通知栏的大图标,可以不设置
    public Integer largeIcon;
    //通知栏的小图标,可以不设置
    public Integer smallIcon;
    //通知栏的title
    public String title;
    //下载url,必须设置
    public String url;
    //更新的回调监听,若不设置,则采用默认
    public UpdateDownloadListener listener;

}
