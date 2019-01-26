package com.cxyz.info.imodel.impl;

import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.info.constant.RequestCenter;
import com.cxyz.info.imodel.IUploadModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.manager.UserManager;

/**
 * Created by Administrator on 2019/1/2.
 */

public class IUploadModelImpl extends IUploadModel {
    @Override
    public void isUserImportEnable(ModelListener<Boolean, String> listener) {
        Integer gradeId = UserManager.getInstance().getUser().getGradeId();
        if(gradeId == null)
        {
            listener.onFail("您没有此权限");
            return;
        }
        addCall(RequestCenter.isUserImportEnable(gradeId, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                CheckResult<Boolean> cr = (CheckResult<Boolean>) responseObj;
                if(cr.isSuccess())
                    listener.onSuccess(cr.getData());
                else
                    listener.onFail(cr.getError());
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

    @Override
    public void isLessonImportEnable(ModelListener<Boolean, String> listener) {
        Integer gradeId = UserManager.getInstance().getUser().getGradeId();
        if(gradeId == null)
        {
            listener.onFail("您没有此权限");
            return;
        }
        addCall(RequestCenter.isLessonImportEnable(gradeId, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                CheckResult<Boolean> cr = (CheckResult<Boolean>) responseObj;
                if(cr.isSuccess())
                    listener.onSuccess(cr.getData());
                else
                    listener.onFail(cr.getError());
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
