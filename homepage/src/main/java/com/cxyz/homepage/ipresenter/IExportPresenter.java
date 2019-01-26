package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.homepage.imodel.IExportModel;
import com.cxyz.homepage.iview.IExportView;

/**
 * Created by Administrator on 2019/1/2.
 */

public abstract class IExportPresenter extends IBasePresenter<IExportModel,IExportView> {

    /**
     * 获取班级的任务信息
     */
    public abstract void getGradeTasks();

    /**
     * 下载统计excel
     * @param gradeId
     * @param taskName
     */
    public abstract void getStatisticExcel(Integer gradeId,String taskName);
}
