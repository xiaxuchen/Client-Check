package com.cxyz.mine.iview;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public interface IChangePwdView extends IBaseView {
    /**
     *  显示修改成功
     */
    void changeSuccess( String message);

    /**
     * 显示报错信息
     * @param message
     */
    void chanegFail(String message);
}
