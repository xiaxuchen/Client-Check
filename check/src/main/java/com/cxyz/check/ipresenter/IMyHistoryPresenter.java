package com.cxyz.check.ipresenter;

import com.cxyz.check.model.IMyHistoryModel;
import com.cxyz.check.view.IMyHistoryView;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by Administrator on 2018/12/27.
 */

public abstract class IMyHistoryPresenter extends IBasePresenter<IMyHistoryModel,IMyHistoryView> {

    /**
     * 获取历史考勤记录
     */
    public abstract void getHistory(Integer result);

    /**
     * 加载更多考勤记录
     * @param start 开始条目
     */
    public abstract void loadMoreHistory(Integer result,Integer start);
}
