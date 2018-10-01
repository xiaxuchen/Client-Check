package com.cxyz.untilchecked.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.untilchecked.imodel.ILoginModel;
import com.cxyz.untilchecked.iview.ILoginView;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public abstract class ILoginPresenter extends IBasePresenter<ILoginModel,ILoginView> {
    /**
     *
     * @param id
     * @param pwd
     * @param type
     */
    public abstract void login(String id,String pwd,int type);

}
