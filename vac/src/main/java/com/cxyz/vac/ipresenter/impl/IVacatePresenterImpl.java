package com.cxyz.vac.ipresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.vac.imodel.IVacateModel;
import com.cxyz.vac.imodel.impl.IVacateModelImpl;
import com.cxyz.vac.ipresenter.IVacatePresenter;

/**
 * Created by Administrator on 2018/12/23.
 */

public class IVacatePresenterImpl extends IVacatePresenter {
    @Override
    public void vacate(String start, String end, String des) {
        mIView.showLoadingView();
        mIModle.vacate(start, end, des, new IBaseModel.ModelListener<String, String>() {
            @Override
            public void onSuccess(String data) {
                mIView.showSuccess(data);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.showFail(s);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IVacateModel createModel() {
        return new IVacateModelImpl();
    }
}
