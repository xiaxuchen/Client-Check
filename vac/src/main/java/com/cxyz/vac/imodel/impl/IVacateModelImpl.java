package com.cxyz.vac.imodel.impl;

import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.vac.constant.RequestCenter;
import com.cxyz.vac.imodel.IVacateModel;

/**
 * Created by Administrator on 2018/12/23.
 */

public class IVacateModelImpl extends IVacateModel{
    @Override
    public void vacate(String start, String end, String des, ModelListener<String, String> listener) {
        addCall(RequestCenter.vacate(start, end, des, UserManager.getInstance().getUser().getId(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                CheckResult<String> checkResult = (CheckResult<String>) responseObj;
                if(checkResult.isSuccess())
                    listener.onSuccess(checkResult.getData());
                else
                    listener.onFail(checkResult.getError());
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
