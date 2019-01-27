package com.cxyz.mine.IPresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mine.imodel.IChangePwdModel;
import com.cxyz.mine.iview.IChangePwdView;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public abstract class IChangePwdPresenter extends IBasePresenter<IChangePwdModel,IChangePwdView> {

    /**
     * 修改密码
     * @param originPwd 原密码
     * @param newPwd 新密码
     */
    public  abstract void alterPwd(String originPwd,String newPwd);
}
