package com.cxyz.info.imodel.impl;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.info.constant.NetWorkConstant;
import com.cxyz.info.constant.RequestCenter;
import com.cxyz.info.imodel.IUploadLessonModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.typevalue.TaskType;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/12/17.
 */

public class IUploadLessonModelImpl extends IUploadLessonModel {


    @Override
    public void upload(File file,UploadListener listener) {

        addCall((RequestCenter.Upload(NetWorkConstant.UPLOAD_LESSON, TaskType.DAILYCHECK,file, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> checkResult = (CheckResult<String>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<String>>(){}.getType());
                    if(checkResult.isSuccess())
                        listener.onSuccess(checkResult.getData());
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
        })));
    }

    @Override
    public void download(DownloadListener listener) {
        addCall(RequestCenter.DownloadTemplate(NetWorkConstant.DOWNLOAD_LESSON_TEMPLATE,"lessonTemplate.xlsx",new HashMap<>(), new DisposeDownLoadListener() {
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
