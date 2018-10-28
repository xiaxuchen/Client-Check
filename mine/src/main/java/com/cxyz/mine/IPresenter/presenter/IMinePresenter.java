package com.cxyz.mine.IPresenter.presenter;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mine.iview.IMineView;

/**
 * Created by Administrator on 2018/9/25.
 */

public abstract class IMinePresenter extends IBasePresenter<IBaseModel,IMineView> {

    //更新
    public abstract void update();
}
