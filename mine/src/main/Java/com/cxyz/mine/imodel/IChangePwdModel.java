package com.cxyz.mine.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.logiccommons.domain.User;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public abstract class IChangePwdModel extends IBaseModel {

    public interface getChangeInfoListener {
        /**
         * 成功获取服务器信息
         *
         * @param u 用户信息
         */
        public void getInfoSuccess(User u);

        /**
         * 获取服务器信息失败
         *
         * @param error 报错信息
         */
        public void getInfoFail(Object error);
    }
}
