package com.cxyz.homepage.constant;

import android.accounts.NetworkErrorException;
import android.os.Environment;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.dto.GradeTaskDto;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class RequestCenter {

    public static Call getMySubjects(RequestParams params,DisposeDataListener listener){
        ToastUtil.showShort(params.toString());
        try {
           return CommonOkHttpClient.get(NetWorkHomeUrl.CLAZZS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网路状态异常");
        }
        return null;
    }
    /**
     * 通过用户id和所查记录类型查找违规记录(考勤情况)
     * @param listener 回调
     */
    public static Call getRecords(RequestParams params, DisposeDataListener listener)
    {
//        Map<String,String> map = new HashMap<>();
//        map.put("method","getRecordDetails");
//        map.put("id",id);
//        map.put("type",type+"");
        ToastUtil.showShort(params.toString());
//        RequestParams params = new RequestParams(map);
        try {
           return CommonOkHttpClient.post(NetWorkHomeUrl.CLAZZS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     * 通过班级id和日期获取当天所有的考勤任务（课程信息）
     * @param id 班级id
     * @param date 日期
     * @param listener 服务器响应后的回调
     */
    public static Call getTaskInfos(int id, Date date,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("method","getTaskInfos");
        map.put("grade",id+"");
        map.put("date",date.getDate());
        RequestParams params = new RequestParams(map);
        try {
          return   CommonOkHttpClient.post(NetWorkHomeUrl.TASKINFOS_URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     *
     * @param start  开始时间
     * @param end  结束时间
     * @param gradeId  学院id

     * @param listener  服务器响应后的回调
     */
    public static Call getTaskRecord(String start,String end,int gradeId ,DisposeDataListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("start",start+"");
        map.put("end",end+"");
        map.put("gradeId",gradeId+"");
        RequestParams params = new RequestParams(map);
        try {
           return CommonOkHttpClient.get(NetWorkHomeUrl.GET_STATISTIC,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }
    /**
     *
     * @param start  开始时间
     * @param end  结束时间
     * @param gradeId  学院id
     * @param resultType  异常类型
     * @param listener  服务器响应后的回调
     */
    public static Call getStaticRecoeds(String start, String end, int gradeId, int resultType, DisposeDataListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("start",start+"");
        map.put("end",end+"");
        map.put("gradeId",gradeId+"");
        map.put("resultType",resultType+"");
        RequestParams params = new RequestParams(map);
        try {
            return CommonOkHttpClient.get(NetWorkHomeUrl.GET_STATISTICRECORD,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     * 获取学院的所有班级信息
     * @param collegeId
     * @param listener
     */
    public  static  Call  getCollegeGradeID(int collegeId,DisposeDataListener listener){
        Map<String,String> map = new HashMap<>();
        map.put("collegeId",collegeId+"");

        RequestParams params = new RequestParams(map);
        try {
            return CommonOkHttpClient.get(NetWorkHomeUrl.GET_COLLEGEID,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }


    /**
     * 获取班级和任务信息
     * @param sponsorId 发起人id
     * @param sponsorType 发起人类型
     * @param listener
     * @return
     */
    public static Call getGradeTasks(String sponsorId,Integer sponsorType,DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("sponsorId",sponsorId);
        map.put("sponsorType",sponsorType+"");

        RequestParams params = new RequestParams(map);
        try {
            return CommonOkHttpClient.get(NetWorkHomeUrl.GET_GRADE_TASKS,params,new DisposeDataHandler(listener,
                    new TypeToken<CheckResult<List<GradeTaskDto>>>(){}.getType()));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     * 获取统计的excel
     * @param sponsorId
     * @param sponsorType
     * @param gradeId
     * @param taskName
     * @param listener
     * @return
     */
    public static Call getStatisticExcel(String sponsorId,Integer sponsorType,
                                         Integer gradeId,String taskName,DisposeDownLoadListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("sponsorId",sponsorId);
        map.put("sponsorType",sponsorType+"");
        map.put("gradeId",gradeId+"");
        map.put("taskName",taskName);

        RequestParams params = new RequestParams(map);
        try {
            return CommonOkHttpClient.getFile(NetWorkHomeUrl.GET_STATISTIC_EXCEL,params,new DisposeDataHandler(listener, Environment.getExternalStorageDirectory().getAbsolutePath()+"/data/statisic.xlsx"));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }
}
