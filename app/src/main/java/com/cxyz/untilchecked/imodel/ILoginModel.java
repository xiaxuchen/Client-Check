package com.cxyz.untilchecked.imodel;

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

    public interface getLoginInfoListener{
        public void getInfo(JSONObject info);
    }
}
