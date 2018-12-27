package com.cxyz.vac.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.vac.imodel.IVacateModel;
import com.cxyz.vac.iview.IVacateView;

/**
 * Created by Administrator on 2018/12/23.
 */

public abstract class IVacatePresenter extends IBasePresenter<IVacateModel,IVacateView> {

    public abstract void vacate(String start,String end,String des);
}
