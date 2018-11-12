package com.cxyz.check.model;

import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public interface IMyCheckModel extends IBaseModel {


    /**
     * 获取我的考勤信息
     * @param id
     * @param listener
     */
    void getCheckRecords(String id,int type,int grade,getCheckRecordsListener listener);

    interface getCheckRecordsListener{
        //成功则返回考勤记录
        void onSuccess(CheckRecordDto dto);
        //失败则返回错误信息
        void onFail(String error);
    }
}
