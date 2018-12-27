package com.cxyz.commons.utils.HttpUtil.response;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class CommonJsonCallback implements Callback {

    private DisposeDataListener listener;
    private Type clazz;
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
                listener.onFailure(new OKHttpException("服务器无响应"));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        handler.post(() -> handlerResponse(result));
    }

    /**
     * 处理服务器响应参数
     * @param result
     */
    private void handlerResponse(String result)
    {
        LogUtil.e(result);
        if(TextUtils.isEmpty(result))
        {
            listener.onFailure(new OKHttpException(OKHttpException.EMPTY));
            return;
        }
        if(clazz!=null)
        {
            Object o = null;
            try {
                o = GsonUtil.fromJson(result, clazz);
                LogUtil.e(result);
            } catch (JSONException e) {
                e.printStackTrace();
                listener.onFailure("服务器异常");
            }
            if(o!=null)
            {
                listener.onSuccess(o);
            }else{
                listener.onFailure(new OKHttpException("数据异常"));
            }
            return;
        }
        listener.onSuccess(result);
    }
}
