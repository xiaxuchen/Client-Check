package com.cxyz.commons.utils.HttpUtil.response;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.JsonUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class CommonJsonCallback implements Callback {

    private DisposeDataListener listener;
    private Class<?> clazz;
    private Handler handler;

    public CommonJsonCallback(DisposeDataHandler disposeDataHandler){
        listener = disposeDataHandler.listener;
        clazz = disposeDataHandler.clazz;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(new OKHttpException(""));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(result);
            }
        });
    }

    /**
     * 处理服务器响应参数
     * @param result
     */
    private void handlerResponse(String result)
    {
        if(TextUtils.isEmpty(result))
            return;
        if(clazz!=null)
        {
            Object o = null;
            o = JsonUtil.jsonToObject(result, clazz);
            if(o!=null)
            {
                listener.onSuccess(o);
            }else{
                listener.onFailure(new OKHttpException("无法转化为相应的对象，请检查Json或者Class是否正确"));
            }
            return;
        }
        listener.onSuccess(result);
    }
}
