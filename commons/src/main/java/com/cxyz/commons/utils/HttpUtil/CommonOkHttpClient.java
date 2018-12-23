package com.cxyz.commons.utils.HttpUtil;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.request.CommonRequest;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.HttpUtil.response.CommonFileCallback;
import com.cxyz.commons.utils.HttpUtil.response.CommonJsonCallback;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 基于OkHttp的异步网络加载框架
 * <h1>使用</h1>
 * <ul>
 *     <li>1.根据情况选择client的相应情求方法，post、get、getFile<br></br>
 *     <li>2.传入url，params和DisposeDataHandler，详细请看RequestParams和DisPoseDataHandler<br></br>
 *     <li>3.如果没有参数params可以指定为null
 * </ul>
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 5;
    private static OkHttpClient client;
    private static Context context;

    private CommonOkHttpClient(){}

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
        builder.hostnameVerifier((s, sslSession) -> true);
        builder.cookieJar(new CookieJar()
        {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                LogUtil.e(url.host());
                LogUtil.e(cookies.size());
                LogUtil.e(url.toString());
                cookieStore.put(url.host(),cookies);
                for(Cookie cookie:cookies){
                    if(cookie == null)
                    {
                        continue;
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = new ArrayList<>();
                List<Cookie> cookies1 = cookieStore.get(url.host());
                if(cookies1 == null || cookies1.isEmpty())
                    return cookies;
                for(Cookie cookie:cookies1){
                    if(cookie == null)
                    {
                        LogUtil.e("cookie为空");
                        continue;
                    }
                    if(!cookie.name().equals("rememberMe"))
                        cookies.add(cookie);
                }

                return cookies;
            }
        });

        //生成client对象
        client = builder.build();
    }

    /**
     * 判断网络状态需要context，所以在application中就必须先进行初始化
     * @param c
     */
    public static void init(Context c)
    {
        context = c;
    }


    /**
     * get方式请求网络
     * @param url 请求的地址
     * @param params 请求参数
     * @param handler 请求信息的封装类，详细请看DisposeDataHandler
     * @return
     */
    public static Call get(String url, RequestParams params, DisposeDataHandler handler) throws NetworkErrorException {
        if(!NetWorkUtil.isNetWorkEnable(context))
            throw new NetworkErrorException();
        Request request = CommonRequest.createGetRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    /**
     * post方式请求网络
     * @param url 请求的地址
     * @param params 请求参数
     * @param handler 请求信息的封装类，详细请看DisposeDataHandler
     * @return
     */
    public static Call post(String url, RequestParams params, DisposeDataHandler handler) throws NetworkErrorException {
        if(!NetWorkUtil.isNetWorkEnable(context))
            throw new NetworkErrorException();
        Request request = CommonRequest.createPostRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }

    /**
     * post方式请求网络
     * @param url 请求的地址
     * @param params 请求参数
     * @param handler 请求信息的封装类，详细请看DisposeDataHandler
     * @return
     */
    public static Call uploadFile(String url, RequestParams params, DisposeDataHandler handler) throws NetworkErrorException {
        if(!NetWorkUtil.isNetWorkEnable(context))
            throw new NetworkErrorException();
        Request request = CommonRequest.createMultiPostRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
        return call;
    }



    /**
     * 下载文件
     * @param url 请求的地址
     * @param params 请求参数
     * @param handler 请求信息的封装类，详细请看DisposeDataHandler
     * @return
     */
    public static Call getFile(String url, RequestParams params, DisposeDataHandler handler) throws NetworkErrorException {
        if(!NetWorkUtil.isNetWorkEnable(context))
            throw new NetworkErrorException();
        Request request = CommonRequest.createGetRequest(url, params);
        Call call = client.newCall(request);
        call.enqueue(new CommonFileCallback(handler));
        return call;
    }
}
