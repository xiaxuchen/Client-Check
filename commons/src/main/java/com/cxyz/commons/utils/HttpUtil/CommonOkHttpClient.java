package com.cxyz.commons.utils.HttpUtil;

import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.request.CommonRequest;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.HttpUtil.response.CommonFileCallback;
import com.cxyz.commons.utils.HttpUtil.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 基于OkHttp的异步网络加载框架
 * <h1>使用</h1>
 * <ul>
 *     <li>1.创建CommonOkHttpClient对象client<br/>
 *     <li>2.根据情况选择client的相应情求方法，post、get、getFile<br/>
 *     <li>3.传入url，params和DisposeDataHandler，详细请看RequestParams和DisPoseDataHandler<br/>
 *     <li>4.如果没有参数params可以指定为null
 * </ul>
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 10;
    private static OkHttpClient client;

    static {
        //创建okhttpclient的构建者
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //填充超时时间
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(3 * TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(3 * TIME_OUT, TimeUnit.SECONDS);
        //设置为可转发
        builder.followRedirects(true);
        //https支持
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        //生成client对象
        client = builder.build();
    }

    public static Call get(String url, RequestParams params, DisposeDataHandler handler) {
        Request request = CommonRequest.createGetRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    public static Call post(String url, RequestParams params, DisposeDataHandler handler) {
        Request request = CommonRequest.createPostRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    public static Call getFile(String url, RequestParams params, DisposeDataHandler handler) {
        Request request = CommonRequest.createGetRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonFileCallback(handler));
        return call;
    }
}
