package com.cxyz.vac.iview;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by Administrator on 2018/12/23.
 */

public interface IVacateView extends IBaseView {

    /**
     * 显示提交成功
     * @param info
     */
    void showSuccess(String info);

    /**
     * 显示错误信息
     * @param error
     */
    void showFail(String error);
}
