package com.cxyz.info.iview;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by Administrator on 2019/1/2.
 */

public interface IUploadView extends IBaseView {
    /**
     * 用户信息可导入
     */
    void userImportEnable();

    /**
     * 课程信息可导入
     */
    void lessonImportEnable();

    /**
     * 检查失败或不可导入
     * @param error
     */
    void error(String error);
}
