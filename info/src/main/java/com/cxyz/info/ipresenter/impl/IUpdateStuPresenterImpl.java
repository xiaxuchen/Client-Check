package com.cxyz.info.ipresenter.impl;

import com.cxyz.info.imodel.IUploadStuModel;
import com.cxyz.info.imodel.impl.IUploadStuModelImpl;
import com.cxyz.info.ipresenter.IUploadStuPresenter;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public class IUpdateStuPresenterImpl extends IUploadStuPresenter {
    @Override
    public void download() {
        mIModle.download(new IUploadStuModel.DownloadListener() {
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
        mIModle.upload(file, new IUploadStuModel.UploadListener() {
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
    public IUploadStuModel createModel() {
        return new IUploadStuModelImpl();
    }
}
