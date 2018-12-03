package com.cxyz.check.ipresenter;

import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by Administrator on 2018/12/3.
 */

public abstract class ICheckPresenter extends IBasePresenter<ICheckModel,ICheckView> {
    public abstract void checkTask();
}
