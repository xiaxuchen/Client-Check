package com.cxyz.check.model;

import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public abstract class IMyHistoryModel extends IBaseModel {

    /**
     * 获取历史考勤记录
     */
    public abstract void getHistory(Integer result,ModelListener<List<MyHistoryDto>, String> listener);

    /**
     * 加载更多考勤记录
     * @param start 开始条目
     * @param listener
     */
    public abstract void loadMoreHistory(Integer result,Integer start,ModelListener<List<MyHistoryDto>,String> listener);

}
