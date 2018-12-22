package com.cxyz.check.model;

import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/12/9.
 */

public abstract class IAlterModel extends IBaseModel {
    /**
     * 获取考勤记录信息
     * @param compId 考勤id
     * @param gradeId 班级id
     */
    public abstract void getAlterRecords(int compId,int gradeId,getAlterRecordsListener listener);

    /**
     * 更新记录
     * @param compId 任务完成情况id
     * @param dtos
     * @param listener 回调
     */
    public abstract void updateRecords(int compId,List<AlterRecordDto> dtos,updateRecordsListener listener);

    public interface updateRecordsListener{
        /**
         * 成功的回调
         * @param info 信息
         */
        void onSuccess(String info);

        /**
         * 失败的回调
         * @param error 错误信息
         */
        void onFail(String error);
    }

    public interface getAlterRecordsListener{
        /**
         * 成功的回调
         * @param dtos 历史记录详情
         */
        void onSuccess(List<AlterRecordDto> dtos);

        /**
         * 失败的回调
         * @param error 错误信息
         */
        void onFail(String error);
    }
}
