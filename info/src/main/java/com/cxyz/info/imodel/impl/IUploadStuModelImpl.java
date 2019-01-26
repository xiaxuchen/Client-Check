package com.cxyz.info.imodel.impl;

import com.cxyz.commons.context.ContextManager;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.info.constant.NetWorkConstant;
import com.cxyz.info.constant.RequestCenter;
import com.cxyz.info.imodel.IUploadStuModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.typevalue.UserType;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/12/17.
 */

public class IUploadStuModelImpl extends IUploadStuModel {

    @Override
    public void upload(File file,UploadListener listener) {

        addCall(RequestCenter.Upload(NetWorkConstant.UPLOAD_STU, UserType.STUDENT,file, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<List<String>> checkResult = (CheckResult<List<String>>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<List<String>>>(){}.getType());
                    if(checkResult.isSuccess()) {
                        final List<String> ids = checkResult.getData();
                        LogUtil.e(ids);
                        for (String id : ids)
                        {
                            JPushInterface.setAlias(ContextManager.getContext(),Integer.parseInt(id),id);
                            JPushInterface.deleteAlias(ContextManager.getContext(),Integer.parseInt(id));
                        }
                        listener.onSuccess("导入成功");
                    }
                    else
                        listener.onFail(checkResult.getError());
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

    @Override
    public void download(DownloadListener listener) {
        addCall(RequestCenter.DownloadTemplate(NetWorkConstant.DOWNLOAD_STU_TEMPLATE,"stuTemplate.xlsx",new HashMap<>(), new DisposeDownLoadListener() {
            @Override
            public void onProgress(int progrss) {
                listener.updateProgress(progrss);
            }

            @Override
            public void onSuccess(Object responseObj) {
                listener.onSuccess((File)responseObj);
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
