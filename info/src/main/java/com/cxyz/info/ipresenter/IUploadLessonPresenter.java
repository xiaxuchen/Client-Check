package com.cxyz.info.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.info.imodel.IUploadLessonModel;
import com.cxyz.info.iview.IUploadLessonView;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public abstract class IUploadLessonPresenter extends IBasePresenter<IUploadLessonModel,IUploadLessonView> {

    public abstract void download();

    public abstract void upload(File file);
}
