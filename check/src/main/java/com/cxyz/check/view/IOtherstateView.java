package com.cxyz.check.view;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by Administrator on 2018/12/15.
 */

public interface IOtherstateView extends IBaseView {

    /**
     * 显示提交成功
     * @param info
     */
    void showCommitSuccess(String info);

    /**
     * 显示提交失败
     * @param error
     */
    void showError(String error);
}
