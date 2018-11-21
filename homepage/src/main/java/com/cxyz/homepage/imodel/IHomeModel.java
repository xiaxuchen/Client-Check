package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.homepage.dto.CheckTaskDto;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public interface IHomeModel extends IBaseModel {
    /**
     * 通过用户id和类型检查当前是否有考勤任务
     * @param id 用户id
     * @param type 用户类型
     * @return 考勤信息
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
