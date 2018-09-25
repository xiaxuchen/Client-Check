package com.cxyz.commons.utils.HttpUtil.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 用来封装请求
 */

public class CommonRequest {
    /**
     *
     * @param url
     * @param params
     * @return 返回一个创建好的发送GET请求的Request对象
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null)
        {
            for(Map.Entry<String,String> entry:params.urlParams.entrySet())
            {
                urlBuilder.append(entry.getKey()+"="+entry.getValue()+"&");
            }
        }
        return new Request.Builder().get().url(urlBuilder.substring(0,urlBuilder.length()-1).toString()).build();
    }
    /**
     *
     * @param url
     * @param params
     * @return 返回一个创建好的发送POST请求的Request对象
     */
    public static Request createPostRequest(String url,RequestParams params)
    {
        FormBody body = null;
        if(params != null)
        {
            FormBody.Builder builder = new FormBody.Builder();
            for(Map.Entry<String,String> entry:params.urlParams.entrySet())
            {
                builder.add(entry.getKey(),entry.getValue());
            }
            body = builder.build();
        }
        return new Request.Builder().post(body).url(url).build();
    }

    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
    /**
     *
     * @param url
     * @param params
     * @return 返回一个创建好的发送文件请求的Request对象
     */
    public static Request createMultiPostRequest(String url,RequestParams params)
    {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if(params != null)
        {
            for(Map.Entry<String,Object> entry:params.fileparams.entrySet())
            {
                if(entry.getValue() instanceof File)
                {
                    builder.addPart(MultipartBody.Part.createFormData(entry.getKey(),null,
                            RequestBody.create(FILE_TYPE,(File)entry.getValue())));
                }else{
                    builder.addFormDataPart(entry.getKey(),String.valueOf(entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(builder.build()).build();
    }

}
