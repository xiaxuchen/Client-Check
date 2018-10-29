package com.cxyz.imageloader.config;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cxyz.imageloader.R;

/**
 * Created by xiaxuchen on 18-10-28.
 */

public class ImageLoaderConfig {
    private static final int THREAD_COUNT = 4;//最多的线程数
    private static final int PROPRITY = 2;//图片下载的优先级
    private static final int CONNECT_TIMEOUT = 5*1000;//连接超时
    private static final int DISK_CACHE_SIZE =50*1024;//最大缓存图片空间
    private static final int READ_TIMEOUT = 30*1000;//读取超时

    /**
     * 最多的线程数,默认4
     */
    public int threadCount = THREAD_COUNT;
    /**
     * 图片下载的优先级,默认2
     */
    public int proprity = PROPRITY;
    /**
     * 连接超时,默认5秒
     */
    public int connect_timeout = CONNECT_TIMEOUT;
    /**
     * 最大缓存图片空间,默认50M
     */
    public int diskCacheSize = DISK_CACHE_SIZE;
    /**
     * 读取超时,默认30秒
     */
    public int read_timeout = READ_TIMEOUT;
    /**
     * 未加载时的图片
     */
    public int emptyImage = R.mipmap.ic_launcher;
    /**
     * 加载失败的图片
     */
    public int failImage = R.mipmap.ic_launcher;


}
