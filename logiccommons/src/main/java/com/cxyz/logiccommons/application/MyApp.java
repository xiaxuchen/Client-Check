package com.cxyz.logiccommons.application;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.application.BaseApplication;
import com.cxyz.commons.utils.CrashHandler;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.R;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 夏旭晨 on 2018/9/20.
 */

public class MyApp extends BaseApplication {

    private HashMap<String,Object> attributes;

    private static HashMap<Class,IconFontDescriptor> ICONS = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        attributes = new HashMap<>();
        initToast();
        initCrach();
        initSpUtil();
        initRefresh();
        initCommonOkHttpClient();
        initARouter(true);
        initIconify();
    }

    public static void withIcon(IconFontDescriptor descriptor)
    {
        if(ICONS.containsKey(descriptor.getClass()))
            return;
        ICONS.put(descriptor.getClass(),descriptor);
        Iconify.with(descriptor);
    }

    private void initIconify()
    {
        Iconify.with(new FontAwesomeModule());
    }

    private void initJpush()
    {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    /**
     * 初始化ToastUtil
     */
    private void initToast()
    {
        //初始化ToastUtils
        ToastUtil.init(getApplicationContext());
    }

    /**
     * 初始化SpUtil
     */
    private void initSpUtil()
    {
        //初始化SpUtils
        SpUtil.init(getApplicationContext());
    }

    /**
     * 初始化全局异常
     */
    private void initCrach()
    {
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance().init(this,"http://192.168.155.2:8080/Server_Check/envir/uploadBugs"));
    }


    /**
     * 初始化okhttp
     */
    private void initCommonOkHttpClient()
    {
        //初始化CommonOkHttp
        CommonOkHttpClient.init(getApplicationContext());
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

    /**
     * 初始化refresh
     */
    private void initRefresh()
    {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.gray, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }


    /***
     * 添加或者覆盖一个key所对应的全局map中的值
     * @param key 所设置属性的键
     * @param value 所设置属性的值
     */
    public void setAttribute(String key,Object value)
    {
        attributes.put(key,value);
    }

    /***
     *获取给出的key在全局map中所对应的value
     * @param key 要获取的属性的键
     * @return 返回key所对应的对象，若没有则返回null
     */
    public Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    /***
     * 获取全局map给出的key所对应的value
     * @param key 要移除的属性的键
     */
    public void removeAttribute(String key)
    {
        attributes.remove(key);
    }

    /***
     * 清空所有全局属性（不常用，使用的时候请与他人讨论）
     */
    public void clearAttribute() {
        attributes.clear();
    }

    /***
     * 获取map中是否包含相应的key
     */
    public boolean containsKey(Object key) {
        return attributes.containsKey(key);
    }

    /***
     * 获取map中是否包含相应的值
     */
    public boolean containsValue(Object value) {
        return attributes.containsValue(value);
    }

    /***
     * 可以将一个map中的所有键值对都添加进全局属性
     * @param map 想添加多个属性所存放的map
     */
    public void putAll(Map map) {
        attributes.putAll(map);
    }

    /***
     * 获取全局属性的大小
     * @return 全局属性的大小
     */
    public int size() {
        return attributes.size();
    }

}
