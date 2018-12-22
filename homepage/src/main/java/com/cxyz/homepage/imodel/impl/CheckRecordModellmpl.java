package com.cxyz.homepage.imodel.impl;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.dto.StatisticDto;
import com.cxyz.homepage.dto.StatisticRecordDto;
import com.cxyz.homepage.imodel.CheckRecordModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by ${喻济生} on 2018/12/19.
 */

public class CheckRecordModellmpl implements CheckRecordModel {

    @Override
    public void getRecord(String start, String end, Integer gradeID,final getRecordListener listener) {
        if (start.contains("2") || end.contains("2")) {
            //获取考勤人数
            try {


                RequestCenter.getTaskRecord(start, end, gradeID, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) throws JSONException {

                        try {
                            CheckResult<StatisticDto> checkResult = (CheckResult<StatisticDto>) GsonUtil.fromJson(responseObj.toString(),
                                    new TypeToken<CheckResult<StatisticDto>>() {
                                    }.getType());
                            if (checkResult.isSuccess()) {
                                listener.onSuccess(checkResult.getData());
                            } else {
                                listener.onFail(checkResult.getError());
                                ToastUtil.showShort("获取失败1111");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.d("异常");
                        }
                    }

                    @Override
                    public void onFailure(Object error) {
                        listener.onFail("服务器异常");
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                if(listener!=null)
                    listener.onFail("网络状态异常");
            }
        }

    }

    @Override
    public void getStasticRecord(String start, String end, Integer gradeID, Integer typeResult, getStasticRecordListener listener) {
        try {
            RequestCenter.getStaticRecoeds(start, end, gradeID, typeResult, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) throws JSONException {
                    try {
                        CheckResult<StatisticRecordDto> checkResult= (CheckResult<StatisticRecordDto>) GsonUtil.fromJson(responseObj.toString(),
                                new TypeToken<CheckResult<StatisticRecordDto>>(){}.getType());
                        if (checkResult.isSuccess()) {
                            listener.onSuccess(checkResult.getData());
                        } else {
                            listener.onFail(checkResult.getError());
                            LogUtil.d("异常2");
                    }
                }catch (Exception e){
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Object error) {
                    listener.onFail("服务器异常");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            if(listener!=null)
                listener.onFail("网络状态异常");
        }
    }

}
