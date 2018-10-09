package com.cxyz.mains.imodel;

import com.cxyz.commons.IModel.IBaseModel;

import org.json.JSONObject;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public interface ILoginModel extends IBaseModel {
    /**
     * 将id和密码发给服务器进行验证
     * @param id
     * @param pwd
     * @return
     */
    public void getLoginInfo(String id, String pwd,int type,getLoginInfoListener listener);

    /**
     * getLoginInfo获取数据后将会执行此回调
     */
    public interface getLoginInfoListener{
        /**
         * 成功获取服务器信息
         * @param info 获取到的信息
         */
        public void getInfoSuccess(JSONObject info);

        /**
         *  获取服务器信息失败
         * @param error 报错信息
         */
        public void getInfoFail(Object error);
    }
}
