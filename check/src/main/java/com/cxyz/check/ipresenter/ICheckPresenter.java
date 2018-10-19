package com.cxyz.check.ipresenter;

import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.IPresenter.IBasePresenter;

import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class ICheckPresenter extends IBasePresenter<ICheckModel,ICheckView> {

    /**
     * 从服务器获取学生数据，并显示到listview中
     */

    public abstract void getStusToShow();

    public abstract List getGridViewInfo();

    /**
     * 检查当前是否有考勤任务再选择进行考勤
     * @return
     */
    public abstract void checkTask();

}
