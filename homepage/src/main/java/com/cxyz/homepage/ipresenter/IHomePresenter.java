package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.homepage.imodel.IHomeModel;
import com.cxyz.homepage.iview.IHomeView;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class IHomePresenter extends IBasePresenter<IHomeModel,IHomeView> {

    /**
     * 检查当前是否有考勤任务再选择进行考勤
     * @return
     */
    public abstract void checkTask();

}
