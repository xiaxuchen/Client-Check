package com.cxyz.check.model;

import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by Administrator on 2018/12/3.
 */

public interface ICheckModel extends IBaseModel {
    /**
     * 通过用户id和类型检查当前是否有考勤任务
     * @param checkerId 考勤人id
     * @param type 考勤人类型
     * @param listener 请求完成后的回调
     */
    void checkComp(String checkerId, int checkerType, int type, CheckListener listener);

    interface CheckListener{

        /**
         * 请求成功
         */
        void onSuccess(CheckTaskDto checkTaskDto);

        /**
         * 请求失败
         */
        void onFail(String error);
    }
}
