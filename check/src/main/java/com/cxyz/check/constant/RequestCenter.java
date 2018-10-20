package com.cxyz.check.constant;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class RequestCenter {

    /**
     * 请求网络，获取当前是否有待考勤的任务
     * @param id 用户id
     * @param type 用户类型
     * @param listener 接受到服务器响应后的回调
     * @throws NetworkErrorException
     */
    public static void checkComp(String id, int type, DisposeDataListener listener) throws NetworkErrorException {
        Map<String,String> map = new HashMap();
        map.put("method","checkComp");
        map.put("id",id);
        map.put("type",type+"");
        RequestParams params = new RequestParams(map);
        CommonOkHttpClient.post(NetWorkConstant.CHECKCOMP_URL,params,new DisposeDataHandler(listener));
    }
}
