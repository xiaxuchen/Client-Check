package com.cxyz.check.ipresenter;

import com.cxyz.check.model.IMyCheckModel;
import com.cxyz.check.view.IMyCheckView;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public abstract class IMyCheckPresenter extends IBasePresenter<IMyCheckModel,IMyCheckView>{

    public abstract void showRecords();
}
