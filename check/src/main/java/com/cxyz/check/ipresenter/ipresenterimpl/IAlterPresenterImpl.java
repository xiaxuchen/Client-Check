package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.check.ipresenter.IAlterPresenter;
import com.cxyz.check.model.IAlterModel;
import com.cxyz.check.model.imodelimpl.IAlterModelImpl;
import com.cxyz.logiccommons.manager.UserManager;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */

public class IAlterPresenterImpl extends IAlterPresenter {
    @Override
    public void getAlterRecords(Integer compId) {
        mIView.showLoadingView();
        mIModle.getAlterRecords(compId, UserManager.getInstance().getUser().getGradeId(), new IAlterModel.getAlterRecordsListener() {
            @Override
            public void onSuccess(List<AlterRecordDto> dtos) {
                mIView.showAlterRecords(dtos);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String error) {
                mIView.showGetAltersError(error);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public void commitAlted(Integer compId, List<AlterRecordDto> alted) {
        mIView.showLoadingView();
        mIModle.updateRecords(compId, alted, new IAlterModel.updateRecordsListener() {
            @Override
            public void onSuccess(String info) {
                mIView.hideLoadingView();
                mIView.showAlterSuccess(info);
            }

            @Override
            public void onFail(String error) {
                mIView.hideLoadingView();
                mIView.showAlterFail(error);
            }
        });
    }

    @Override
    public IAlterModel createModel() {
        return new IAlterModelImpl();
    }
}
