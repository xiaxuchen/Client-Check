package com.cxyz.check.ipresenter;

import com.cxyz.check.model.IDailyModel;
import com.cxyz.check.view.IDailyView;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.TaskCompletion;

import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public abstract class IDailyPresenter extends IBasePresenter<IDailyModel,IDailyView> {
    /**
     * 获取学生数据并显示
     * @param grade
     */
    public abstract void getStusToShow(int grade);

    /**
     * 提交考勤情况
     */
    public abstract void commit(Map<String,CheckRecord> crs, TaskCompletion completion);
}
