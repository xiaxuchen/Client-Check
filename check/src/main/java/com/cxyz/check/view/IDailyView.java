package com.cxyz.check.view;

import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.commons.IView.IBaseView;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public interface IDailyView extends IBaseView {
    /**
     * 显示学生信息到列表中
     * @param stus
     */
    void showStus(List<GradeStusDto> stus);

    /**
     * 显示错误信息
     * @param error
     */
    void showError(String error);

    /**
     * 显示提交时的错误信息
      * @param error 错误信息
     */
    void showCommitError(String error);

    /**
     * 显示提交结果
     * @param info
     */
    void showCommitResult(String info);

    /**
     * 显示加载学生信息
     */
    void showLoadStus();

    /**
     * 改变loadview为暂无
     */
    void changeLoadStus();
}
