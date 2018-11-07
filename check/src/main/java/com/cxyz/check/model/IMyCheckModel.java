package com.cxyz.check.model;

import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public interface IMyCheckModel extends IBaseModel {

    void getRds(String id,getRdsListener listener);

    interface getRdsListener{
        //成功则返回考勤记录
        void onSuccess(List<Map<String,Object>> data,int times,int checkerror,int lateAndEarly,int absent,int progress);
        //失败则返回错误信息
        void onFail(String error);
    }
}
