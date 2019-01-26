package com.cxyz.info.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.info.imodel.IUploadModel;
import com.cxyz.info.iview.IUploadView;

/**
 * Created by Administrator on 2019/1/2.
 */

public abstract class IUploadPresenter extends IBasePresenter<IUploadModel,IUploadView> {

    /**
     * 检查导入权限
     */
    public abstract void check();

}
