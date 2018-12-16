package com.cxyz.check.model;

import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public interface IHistoryModel extends IBaseModel {

    /**
     * 获取历史考勤记录
     */
    void getHistory(GetHistoryListener listener);

    /**
     * 加载更多考勤记录
     * @param start 开始条目
     * @param listener
     */
    void loadMoreHistory(int start,LoadMoreHistoryListener listener);

    interface GetHistoryListener
    {
        /**
         * 获取历史纪录成功的回调
         * @param history 历史纪录
         */
        void getHistorySuccess(List<CheckHistoryDto> history);

        /**
         * 获取历史纪录失败的回调
         * @param error 错误信息
         */
        void getHistoryFail(String error);
    }

    interface LoadMoreHistoryListener
    {
        /**
         * 获取历史纪录成功的回调
         * @param history 历史纪录
         */
        void LoadMoreSuccess(List<CheckHistoryDto> history);

        /**
         * 获取历史纪录失败的回调
         * @param error 错误信息
         */
        void LoadMoreFail(String error);
    }
}
