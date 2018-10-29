package com.cxyz.imageloader.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cxyz.imageloader.config.ImageLoaderConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**实现对IML的封装
 * Created by 夏旭晨 on 2018/8/6.
 *
 * <h2>使用时如果需要更换默认加载图片和加载失败图片请修改DisplayImageOptions方法</h2>
 *
 * <h2>使用示例</h2><br/>
 * &nbsp;&nbsp;ImageLoaderManager ilm = ImageLoaderManager.getInstance(getApplicationContext);<br/>
 * &nbsp;&nbsp;ilm.displayImage(...)//根据情况选择重载的方法
 */

public class ImageLoaderManager {

    private static ImageLoader mImageLoader;
    private static ImageLoaderManager mInstance;

    //相关配置
    private static ImageLoaderConfig c = new ImageLoaderConfig();

    public static void init(ImageLoaderConfig c)
    {
        ImageLoaderManager.c = c;
    }

    public static ImageLoaderManager getInstance(Context context)
    {
        if(mInstance == null)
            mInstance = new ImageLoaderManager(context);
        return mInstance;
    }
    private ImageLoaderManager(Context context)
    {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(c.threadCount)//设置下载图片最大线程个数
                .threadPriority(Thread.NORM_PRIORITY-c.proprity)//设置下载图片优先级，先获取设备的一般优先级
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套尺寸的图片
                .memoryCache(new WeakMemoryCache())//设置为弱引用缓存
                .diskCacheSize(c.diskCacheSize)//设置硬盘缓存大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用md5命名文件
                .tasksProcessingOrder(QueueProcessingType.FIFO)//设置图片下载顺序以FIFO为标准
                .defaultDisplayImageOptions(getDefaultOptions())//设置默认选项
                .imageDownloader(new BaseImageDownloader(context,c.connect_timeout,c.read_timeout))//设置图片下载器
                .writeDebugLogs()//debug模式会输出日志
                .build();
        ImageLoader.getInstance().init(config);
        mImageLoader =ImageLoader.getInstance();
    }
    //实现默认Options的配置
    private DisplayImageOptions getDefaultOptions()
    {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(c.emptyImage)//设置加载之前的显示的图片
                .showImageOnFail(c.failImage)//加载失败显示的图片
                .cacheInMemory(true)//设置内存缓存可用
                .cacheOnDisk(true)//设置磁盘缓存可用
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码方式
                .decodingOptions(new BitmapFactory.Options())
                .build();

        return options;
    }

    /**
     * 使用默认配置加载图片
     * @param imageView 加载图片的imageView
     * @param url 加载图片的路径
     */
    public void displayImage(ImageView imageView, String url){
        displayImage(imageView,url,getDefaultOptions(),null);
    }

    /**
     *若需要更改默认配置同时又需要回调使用此方法
     * @param imageView 加载图片的imageView
     * @param url 加载图片的路径
     * @param options 配置参数
     * @param listener 回调的listener
     */
    public void displayImage(ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener){
        if(mImageLoader!=null)
            mImageLoader.displayImage(url,imageView,options,listener);
    }

    /**
     * 如果需要更改默认配置可以传入options
     * @param imageView 加载图片的imageView
     * @param url 加载图片的路径
     * @param options 配置参数
     */
    public void displayImage(ImageView imageView,String url,DisplayImageOptions options){
        displayImage(imageView,url,options,null);
    }

    /**
     * 图片加载过程中（不论成功与否），调用listener中的方法，详细请看ImageLoadingListener
     * @param imageView 加载图片的imageView
     * @param url 加载图片的路径
     * @param listener 回调的listener
     */
    public void displayImage(ImageView imageView,String url, ImageLoadingListener listener){
        displayImage(imageView,url,null,listener);
    }

}
