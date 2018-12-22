package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.check.ipresenter.IMyCheckPresenter;
import com.cxyz.check.model.IMyCheckModel;
import com.cxyz.check.model.imodelimpl.IMyCheckIModelImpl;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public class IMyCheckPresenterImpl extends IMyCheckPresenter {
    @Override
    public void showRecords() {
        User user = UserManager.getInstance().getUser();
        mIView.showLoadingView();
        mIModle.getCheckRecords(user.getId(), user.getType(), user.getGradeId(),
                new IMyCheckModel.getCheckRecordsListener() {


            @Override
            public void onSuccess(CheckRecordDto dto) {
                mIView.hideLoadingView();
                mIView.showRecords(dto);
            }

            @Override
            public void onFail(String error) {
                mIView.hideLoadingView();
                mIView.showError(error);
            }
        });
    }

    @Override
    public IMyCheckModel createModel() {
        return new IMyCheckIModelImpl();
    }
}
