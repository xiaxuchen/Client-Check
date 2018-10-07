package com.cxyz.mains.iview;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public interface ILoginView extends IBaseView{

    /**
     *  显示登录成功界面
     */
    void loginSuccess();

    /**
     * 显示报错信息
     * @param message
     */
    void loginFail(String message);
}
