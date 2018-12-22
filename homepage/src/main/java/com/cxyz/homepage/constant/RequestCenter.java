package com.cxyz.homepage.constant;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class RequestCenter {

    public static void getMySubjects(RequestParams params,DisposeDataListener listener){
        ToastUtil.showShort(params.toString());
        try {
            CommonOkHttpClient.get(NetWorkHomeUrl.CLAZZS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网路状态异常");
        }
    }
    /**
     * 通过用户id和所查记录类型查找违规记录(考勤情况)
     * @param listener 回调
     */
    public static void getRecords(RequestParams params, DisposeDataListener listener)
    {
//        Map<String,String> map = new HashMap<>();
//        map.put("method","getRecordDetails");
//        map.put("id",id);
//        map.put("type",type+"");
        ToastUtil.showShort(params.toString());
//        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkHomeUrl.CLAZZS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    /**
     * 通过班级id和日期获取当天所有的考勤任务（课程信息）
     * @param id 班级id
     * @param date 日期
     * @param listener 服务器响应后的回调
     */
    public static void getTaskInfos(int id, Date date,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("method","getTaskInfos");
        map.put("grade",id+"");
        map.put("date",date.getDate());
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkHomeUrl.TASKINFOS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

}
