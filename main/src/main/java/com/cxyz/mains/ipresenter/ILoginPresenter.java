package com.cxyz.mains.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mains.imodel.ILoginModel;
import com.cxyz.mains.iview.ILoginView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public abstract class ILoginPresenter extends IBasePresenter<ILoginModel,ILoginView> {
    /**
     * 登录
     * @param id 用户id
     * @param pwd 密码
     * @param type 用户类型
     */
    public abstract void login(String id,String pwd,int type);

}
