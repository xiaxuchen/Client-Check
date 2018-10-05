package com.cxyz.commons.utils.HttpUtil.listener;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 封装回调及数据处理的参数
 */

public class DisposeDataHandler {
    public DisposeDataListener listener = null;
    public Class<?> clazz = null;
    public String mSource;

    /**
     * 封装基本的回调
     * @param listener  回调
     */
    public DisposeDataHandler(DisposeDataListener listener)
    {
        this.listener = listener;
    }

    /**
     * 在返回对象的时候使用此构造方法
     * @param listener 回调
     * @param clazz 对应的实体对象的Class
     */
    public DisposeDataHandler(DisposeDataListener listener,Class<?> clazz){
        this.listener = listener;
        this.clazz = clazz;
    }

    /**
     * 需要返回文件的时候使用此构造方法
     * @param listener 回调
     * @param source 文件存放路径
     */
    public DisposeDataHandler(DisposeDataListener listener,String source){
        this.listener = listener;
        this.clazz = clazz;
        this.mSource = source;
    }

}
