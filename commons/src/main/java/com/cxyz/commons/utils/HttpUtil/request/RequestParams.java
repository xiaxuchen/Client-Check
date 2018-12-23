package com.cxyz.commons.utils.HttpUtil.request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 对请求参数的封装
 */
public class RequestParams {

    public ConcurrentHashMap<String,String> urlParams = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String,Object> fileparams = new ConcurrentHashMap<String, Object>();

    public RequestParams(){
        this((Map<String,String>)null);
    }

    /**
     * 传入map作为请求的参数，建议在多个参数的情况下使用
     * @param source
     */
    public RequestParams(Map<String,String> source){
        if(source!=null)
        {
            for(Map.Entry<String,String> entry:source.entrySet())
            {
                put(entry.getKey(),entry.getValue());
            }
        }
    }

    /**
     * 传入键值对作为请求参数，建议单个参数的时候使用
     * @param key 键
     * @param value 值
     */
    public RequestParams(final String key,final String value)
    {
        this(new HashMap<String, String>(){
            {
                this.put(key, value);
            }
        });
    }

    /**
     * 添加键值对
     * @param key
     * @param value
     * @return
     */
    public RequestParams put(String key,String value)
    {
        this.urlParams.put(key,value);
        return this;
    }


    /**
     * 请求参数为文件，一般上传文件使用
     * @param key 键
     * @param file 文件
     * @return
     */
    public RequestParams put(String key,Object file)
    {
        this.fileparams.put(key,file);
        return this;
    }

    /**
     * 检查是否有参数
     * @return
     */
    public boolean hasParams()
    {
        if(fileparams.size()>0||urlParams.size()>0)
            return true;
        else
            return false;
    }
}

