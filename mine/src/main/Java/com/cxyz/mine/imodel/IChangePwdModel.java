package com.cxyz.mine.imodel;

import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public abstract class IChangePwdModel extends IBaseModel {

    /**
     * 修改密码
     * @param originPwd
     * @param newPwd
     * @param listener
     */
    public abstract void alterPwd(String originPwd,String newPwd,ModelListener<String,String> listener);

}
