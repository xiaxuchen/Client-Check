package com.cxyz.check.constant;

import android.accounts.NetworkErrorException;

import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.LogUtil;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class RequestCenter {


    public static void getStus(int grade, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap();
        map.put("grade",grade+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.get(NetWorkConstant.GET_STUS,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    /**
     * 通过用户id和所查记录类型查找违规记录(考勤情况)
     * @param id 用户id
     * @param type 记录类型
     * @param grade 班级id
     * @param listener 回调
     */
    public static void getGradeCheck(String id, Integer type,int grade, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("type",type+"");
        map.put("grade",grade+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.get(NetWorkConstant.GRADE_CHECK_URL,params,new DisposeDataHandler(listener));
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

    /**
     * 提交考勤
     * @param commitCheck
     * @param listener
     */
    public static void commitCheck(String commitCheck,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("commitCheck",commitCheck);
        RequestParams params = new RequestParams(map);
        //发送请求
        try {
            CommonOkHttpClient.post(NetWorkConstant.COMMIT_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            if(listener!=null)
                listener.onFailure("网络状态异常");
        }
    }

    /**
     * 请求网络，获取当前是否有待考勤的任务
     * @param checkerId 用户id
     * @param checkerType 用户类型
     * @param type 考勤类型
     * @param listener 接受到服务器响应后的回调
     * @throws NetworkErrorException
     */
    public static void checkComp(String checkerId, int checkerType,int type,DisposeDataListener listener) throws NetworkErrorException {
        LogUtil.e(checkerId);
        LogUtil.e(checkerType+"");
        LogUtil.e(type+"");
        Map<String,String> map = new HashMap();
        map.put("checkerId",checkerId);
        map.put("checkerType",checkerType+"");
        map.put("type",type+"");
        RequestParams params = new RequestParams(map);
        LogUtil.e(NetWorkConstant.CHECKCOMP_URL);
        LogUtil.e(map.toString());
        CommonOkHttpClient.get(NetWorkConstant.CHECKCOMP_URL,params,new DisposeDataHandler(listener));
    }

    /**
     * 获取历史考勤记录
     * @param id 考勤人id
     * @param type 考勤人类型
     * @param listener 回调
     */
    public static void getHistory(String id,int type,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap();
        map.put("id",id);
        map.put("type",type+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.get(NetWorkConstant.HISTORY,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    /**
     * 获取更多历史考勤记录
     * @param id 考勤人id
     * @param type 考勤人类型
     * @param start 开始条目
     * @param listener 回调
     */
    public static void loadMore(String id,int type,int start,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap();
        map.put("id",id);
        map.put("type",type+"");
        map.put("start",start+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.get(NetWorkConstant.LOAD_MORE,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    public static void getAlterRecords(Integer compId,Integer gradeId,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("compId",compId+"");
        map.put("gradeId",gradeId+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.get(NetWorkConstant.ALTER_RECORDS,params, new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }

    public static void updateAlteds(Integer compId, List<AlterRecordDto> dtos,String updaterId,int updaterType,DisposeDataListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("compId",compId+"");
        map.put("updaterId",updaterId);
        map.put("updaterType",updaterType+"");
        try {
            map.put("alteds", GsonUtil.toJson(dtos));
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFailure("数据异常");
            return;
        }
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkConstant.UPDATE_RECORDS,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
    }
}
