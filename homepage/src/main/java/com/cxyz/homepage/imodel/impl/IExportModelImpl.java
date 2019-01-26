package com.cxyz.homepage.imodel.impl;

import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.dto.GradeTaskDto;
import com.cxyz.homepage.imodel.IExportModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class IExportModelImpl extends IExportModel {
    @Override
    public void getGradeTasks(ModelListener<List<GradeTaskDto>,String> listener) {
        User user = UserManager.getInstance().getUser();
        addCall(RequestCenter.getGradeTasks(user.getId(), user.getType(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                CheckResult<List<GradeTaskDto>> cr = (CheckResult<List<GradeTaskDto>>) responseObj;
                if(cr.isSuccess())
                {
                    listener.onSuccess(cr.getData());
                }else {
                    listener.onFail(cr.getError());
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

    @Override
    public void getStatisticExcel(Integer gradeId, String taskName,getExcelListener listener) {
        User user = UserManager.getInstance().getUser();
        addCall(RequestCenter.getStatisticExcel(user.getId(), user.getType(), gradeId, taskName, new DisposeDownLoadListener() {
            @Override
            public void onProgress(int progrss) {
                listener.onProgress(progrss);
            }

            @Override
            public void onSuccess(Object responseObj) {
                if(responseObj instanceof String)
                    listener.onFail(responseObj.toString());
                else if(responseObj instanceof File)
                    listener.onSuccess((File) responseObj);
                else
                    listener.onFail(responseObj.toString());

            }

            @Override
            public void onFailure(Object error) {
                String msg;
                if(error instanceof  Exception)
                    msg = ((Exception) error).getMessage();
                else
                    msg = error.toString();
                listener.onFail(msg);
            }
        }));
    }
}
