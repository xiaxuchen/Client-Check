package com.cxyz.info.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.info.imodel.IUploadStuModel;
import com.cxyz.info.iview.IUploadStuView;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public abstract class IUploadStuPresenter extends IBasePresenter<IUploadStuModel,IUploadStuView> {

    public abstract void download();

    public abstract void upload(File file);
}
