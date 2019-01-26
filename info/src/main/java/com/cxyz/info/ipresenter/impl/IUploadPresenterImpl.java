package com.cxyz.info.ipresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.info.imodel.IUploadModel;
import com.cxyz.info.imodel.impl.IUploadModelImpl;
import com.cxyz.info.ipresenter.IUploadPresenter;

/**
 * Created by Administrator on 2019/1/2.
 */

public class IUploadPresenterImpl extends IUploadPresenter {
    @Override
    public IUploadModel createModel() {
        return new IUploadModelImpl();
    }

    @Override
    public void check() {
        mIView.showLoadingView();
        mIModle.isUserImportEnable(new IBaseModel.ModelListener<Boolean, String>() {
            @Override
            public void onSuccess(Boolean data) {
                if(data)
                    mIView.userImportEnable();
                else
                    onFail("不可导入");
            }

            @Override
            public void onFail(String s) {
                mIView.error(s);
            }
        });

        mIModle.isLessonImportEnable(new IBaseModel.ModelListener<Boolean, String>() {
            @Override
            public void onSuccess(Boolean data) {
                if(data)
                    mIView.lessonImportEnable();
                else
                    onFail("不可导入");
            }

            @Override
            public void onFail(String s) {
                mIView.error(s);
            }
        });
    }
}
