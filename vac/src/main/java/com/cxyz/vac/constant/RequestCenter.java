package com.cxyz.vac.constant;


import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/23.
 */

public class RequestCenter {

    /**
     * 请假
     * @param start 开始时间
     * @param end 结束时间
     * @param sponsorId 请假人id
     * @param des 请假事由
     * @param listener
     * @return
     */
    public static okhttp3.Call vacate(String start, String end, String sponsorId, String des, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("start","\""+start+"\"");
        map.put("end","\""+end+"\"");
        map.put("sponsorId",sponsorId);
        map.put("des",des);
        try {
            return CommonOkHttpClient.post(NetWorkConstant.COMMIT_VAC_URL,new RequestParams(map),new DisposeDataHandler(listener,new TypeToken<CheckResult<String>>(){}.getType()));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
