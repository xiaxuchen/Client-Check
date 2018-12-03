package com.cxyz.check.view;

import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.commons.IView.IBaseView;

/**
 * Created by Administrator on 2018/12/3.
 */

public interface ICheckView extends IBaseView {

    /**
     * 显示任务信息
     * @param taskDto
     */
    void showTask(CheckTaskDto taskDto);

    /**
     * 显示暂无任务
     */
    void showNoTask();

    /**
     * 显示加载任务
     */
    void showLoadTask();
}
