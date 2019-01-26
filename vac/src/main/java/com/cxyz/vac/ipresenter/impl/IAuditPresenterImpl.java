package com.cxyz.vac.ipresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.imodel.IAuditModel;
import com.cxyz.vac.imodel.impl.IAuditModelImpl;
import com.cxyz.vac.ipresenter.IAuditPresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class IAuditPresenterImpl extends IAuditPresenter {
    @Override
    public void getVacates() {
        mIView.showLoadingView();
        mIModle.getVacatesToAudit(new IBaseModel.ModelListener<List<VacateDto>, String>() {
            @Override
            public void onSuccess(List<VacateDto> data) {
                mIView.getVacatesSuccess(data);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.getVacatesError(s);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public void auditVac(int position,Integer id, Integer state, String info) {
        mIView.showLoadingView();
        mIModle.auditVacate(id, state, info, UserManager.getInstance().getUser().getPower(), new IBaseModel.ModelListener<String, String>() {
            @Override
            public void onSuccess(String data) {
                mIView.auditSuccess(position,state);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.auditFail(s);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IAuditModel createModel() {
        return new IAuditModelImpl();
    }
}
