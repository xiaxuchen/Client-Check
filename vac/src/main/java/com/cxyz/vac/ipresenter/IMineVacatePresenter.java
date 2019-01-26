package com.cxyz.vac.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.vac.imodel.IMineVacateModel;
import com.cxyz.vac.iview.IMineVacateView;

/**
 * Created by Administrator on 2018/12/31.
 */

public abstract class IMineVacatePresenter extends IBasePresenter<IMineVacateModel,IMineVacateView> {

    /**
     * 获取请假信息
     */
    public abstract void getVacates();
}
