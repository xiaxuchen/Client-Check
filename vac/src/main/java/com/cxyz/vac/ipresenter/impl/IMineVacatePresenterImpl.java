package com.cxyz.vac.ipresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.imodel.IMineVacateModel;
import com.cxyz.vac.imodel.impl.IMineVacateModelImpl;
import com.cxyz.vac.ipresenter.IMineVacatePresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class IMineVacatePresenterImpl extends IMineVacatePresenter{
    @Override
    public void getVacates() {
        mIView.showLoadingView();
        mIModle.getVacates(new IBaseModel.ModelListener<List<VacateDto>, String>() {
            @Override
            public void onSuccess(List<VacateDto> data) {
                mIView.showVacates(data);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.getVacatesFail(s);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IMineVacateModel createModel() {
        return new IMineVacateModelImpl();
    }
}
