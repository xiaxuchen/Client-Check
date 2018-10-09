package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.homepage.imodel.TaskInfos_Model;
import com.cxyz.homepage.iview.TaskInfosPagerView;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public abstract class TaskInfoPresenter extends IBasePresenter<TaskInfos_Model,TaskInfosPagerView>{
    public abstract void getTaskInfoData(String grede_id);
}
