package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.logiccommons.domain.TaskInfo;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public interface IHomeView extends IBaseView {

    /**
     * 检查到当前有任务则弹出对话框确认考勤
     * @param taskInfo
     */
    void showTask(TaskInfo taskInfo);

    /**
     * 没有任务或错误时显示错误信息
     * @param info 错误信息
     */
    void showNoTask(String info);
}
