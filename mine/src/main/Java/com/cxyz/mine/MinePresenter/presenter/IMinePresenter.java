package com.cxyz.mine.MinePresenter.presenter;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mine.MinePresenter.ipresenter.IMineView;

/**
 * Created by Administrator on 2018/9/25.
 */

public abstract class IMinePresenter extends IBasePresenter<IBaseModel,IMineView> {
    @Override
    public IBaseModel createModel() {
        return null;
    }
}
