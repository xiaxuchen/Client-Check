package com.cxyz.untilchecked;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by 夏旭晨 on 2018/9/25.
 */

public abstract class IExamplePresenter extends IBasePresenter<IBaseModel,IExampleView>{
    public abstract void login(String username,String password);
}
