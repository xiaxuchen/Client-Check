package com.cxyz.mine.imodel.impl;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mine.constant.RequestCenter;
import com.cxyz.mine.imodel.IChangePwdModel;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public class IChangePwdModelmpl extends IChangePwdModel {
    @Override
    public void alterPwd(String originPwd, String newPwd, ModelListener<String, String> listener) {
        User user = UserManager.getInstance().getUser();
        addCall(RequestCenter.alterPwd(user.getId(), user.getType(), originPwd, newPwd, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> cr = (CheckResult<String>) GsonUtil.fromJson(responseObj.toString(), new TypeToken<CheckResult<String>>(){}.getType());
                    if(cr.isSuccess())
                        listener.onSuccess(cr.getData());
                    else
                        listener.onFail(cr.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof Exception)
                    listener.onFail(((Exception) error).getMessage());
                else
                    listener.onFail(error.toString());
            }
        }));
    }
}
