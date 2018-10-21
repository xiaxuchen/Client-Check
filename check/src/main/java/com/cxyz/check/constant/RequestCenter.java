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


    public static void getStus(int grade, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap();
        map.put("method","getGradeStus");
        map.put("grade",grade+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkConstant.GET_STUS,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    /**
     * 通过用户id和所查记录类型查找违规记录(考勤情况)
     * @param id 用户id
     * @param type 记录类型
     * @param listener 回调
     */
    public static void getRecords(String id, Integer type, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("method","getRecordDetails");
        map.put("id",id);
        map.put("type",type+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkConstant.RDS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    /**
     * 通过学生id获取考勤统计结果
     * @param id
     * @param listener
     */
    public static void getStatistic(String id,int grade,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("grade",grade+"");
        map.put("method","querySelfStatistic");
        try {
            CommonOkHttpClient.post(NetWorkConstant.STATISTIC_URL,new RequestParams(map),new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }
}
