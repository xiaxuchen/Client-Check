package com.cxyz.mains.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.logiccommons.domain.User;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public abstract class ILoginModel extends IBaseModel {
    /**
     * 将id和密码发给服务器进行验证
     * @param id
     * @param pwd
     * @return
     */
    public abstract void getLoginInfo(String id, String pwd,int type,getLoginInfoListener listener);

    /**
     * getLoginInfo获取数据后将会执行此回调
     */
    public interface getLoginInfoListener{
        /**
         * 成功获取服务器信息
         * @param u 用户信息
         */
        void getInfoSuccess(User u);

        /**
         *  获取服务器信息失败
         * @param error 报错信息
         */
        void getInfoFail(Object error);
    }
}
