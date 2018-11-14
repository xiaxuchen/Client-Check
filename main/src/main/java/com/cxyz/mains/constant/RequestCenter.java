package com.cxyz.mains.constant;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;

/**
 * Created by 夏旭晨 on 2018/10/14.
 */

public class RequestCenter {

    public static void login(RequestParams params, DisposeDataListener listener){
        try {
            CommonOkHttpClient.get(NetWorkConstant.LOGIN_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }
}
