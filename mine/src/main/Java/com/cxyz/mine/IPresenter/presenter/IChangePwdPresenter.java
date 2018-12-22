package com.cxyz.mine.IPresenter.presenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mine.imodel.IChangePwdModel;
import com.cxyz.mine.iview.IChangePwdView;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public abstract class IChangePwdPresenter extends IBasePresenter<IChangePwdModel,IChangePwdView> {
    /**
     * @param oldpwd 原密码
     */
    public abstract void testpwd(String oldpwd);


    public  abstract void changepwd(String newpwd);
}
