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

import okhttp3.Call;

/**
 * Created by Administrator on 2018/12/23.
 */

public class RequestCenter {

    /**
     * 请假
     * @param start 开始时间
     * @param end 结束时间
     * @param sponsorId 请假人id
     * @param sponsorType 请假人类型
     * @param type 请假类型
     * @param len 请假天数
     * @param des 请假事由
     * @param listener
     * @return
     */
    public static okhttp3.Call vacate(String start, String end,Integer len,Integer sponsorType,Integer type, String sponsorId, String des, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("start","\""+start+"\"");
        map.put("end","\""+end+"\"");
        map.put("sponsorType",sponsorType+"");
        map.put("type",type+"");
        map.put("len",len+"");
        map.put("sponsorId",sponsorId);
        map.put("des",des);
        try {
            return CommonOkHttpClient.post(NetWorkConstant.COMMIT_VAC_URL,new RequestParams(map),new DisposeDataHandler(listener,new TypeToken<CheckResult<String>>(){}.getType()));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取请假信息审核
     * @param checkerId 请假人id
     * @param state 请假状态
     * @param listener
     * @return
     */
    public static okhttp3.Call getVacatesToAudit(String checkerId,Integer state,DisposeDataListener listener){
        HashMap<String,String> map = new HashMap<>();
        map.put("checkerId",checkerId);
        if(state != null)
            map.put("state",state+"");
        try {
            return CommonOkHttpClient.get(NetWorkConstant.GET_VACATES_TO_AUDIT,new RequestParams(map),new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     * 审核请假
     * @param id 请假id
     * @param state 状态
     * @param info 回复
     * @param power 审核人的power
     * @param listener
     */
    public static Call auditVac(Integer id,Integer state,String info,Integer power,DisposeDataListener listener)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id+"");
        map.put("state",state+"");
        if(info!=null)
            map.put("info",info);
        map.put("power",power+"");
        try {
            return CommonOkHttpClient.post(NetWorkConstant.AUDIT_VACATE,new RequestParams(map),new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }


    /**
     * 获取请假信息
     * @param sponsorId 请假人id
     * @param state 状态
     * @param sponsorType 请假人类型
     * @param listener
     */
    public static Call getVacates(String sponsorId,Integer sponsorType,Integer state,DisposeDataListener listener)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("sponsorId",sponsorId);
        if(state!=null)
            map.put("state",state+"");
        map.put("sponsorType",sponsorType+"");
        try {
            return CommonOkHttpClient.get(NetWorkConstant.GET_VACATES,new RequestParams(map),new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

}
