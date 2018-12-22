package com.cxyz.info.ipresenter.impl;

import com.cxyz.info.imodel.IUploadLessonModel;
import com.cxyz.info.imodel.impl.IUploadLessonModelImpl;
import com.cxyz.info.ipresenter.IUploadLessonPresenter;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public class IUpdateLessonPresenterImpl extends IUploadLessonPresenter {
    @Override
    public void download() {
        mIModle.download(new IUploadLessonModel.DownloadListener() {
            @Override
            public void onSuccess(File file) {
                mIView.showSuccess(file);
            }

            @Override
            public void updateProgress(int progress) {
                mIView.updateProgress(progress);
            }

            @Override
            public void onFail(String error) {
                mIView.showFail(error);
            }
        });
    }

    @Override
    public void upload(File file) {
        mIView.showLoadingView();
        mIModle.upload(file, new IUploadLessonModel.UploadListener() {
            @Override
            public void onSuccess(String info) {
                mIView.UploadSuccess(info);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String error) {
                mIView.showFail(error);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IUploadLessonModel createModel() {
        return new IUploadLessonModelImpl();
    }
}
