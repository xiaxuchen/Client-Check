package com.cxyz.check.ipresenter;

import com.cxyz.check.model.IHistoryModel;
import com.cxyz.check.view.IHistoryView;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by Administrator on 2018/12/5.
 */

public abstract class IHistoryPresenter extends IBasePresenter<IHistoryModel,IHistoryView> {

    /**
     * 获取历史考勤记录
     */
    public abstract void getHistory();

    /**
     * 加载更多考勤记录
     */
    public abstract void loadMoreHistory();
}
