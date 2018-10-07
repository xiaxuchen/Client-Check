package com.cxyz.check.view;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.logiccommons.domain.Student;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public interface IDailyView extends IBaseView {
    /**
     * 显示学生信息到列表中
     * @param stus
     */
    public void showStus(List<Student> stus);

    /**
     * 显示错误信息
     * @param error
     */
    public void showError(Object error);

    /**
     * 显示提交结果
     * @param info
     */
    public void showCommitResult(String info);
}
