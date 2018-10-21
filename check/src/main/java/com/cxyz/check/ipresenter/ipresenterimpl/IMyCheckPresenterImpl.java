package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.ipresenter.IMyCheckPresenter;
import com.cxyz.check.model.IMyCheckModel;
import com.cxyz.check.model.imodelimpl.IMyCheckIModelImpl;
import com.cxyz.logiccommons.manager.UserManager;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public class IMyCheckPresenterImpl extends IMyCheckPresenter {
    @Override
    public void showRecords() {
        mIView.showLoadingView();
        mIModle.getRds(UserManager.getInstance().getUser().get_id(), new IMyCheckModel.getRdsListener() {

            @Override
            public void onSuccess(List<Map<String, Object>> data, int times, int checkerror, int lateAndEarly, int absent, int progress) {
                mIView.showRecords(data,times,checkerror,lateAndEarly,absent,progress);
            }

            @Override
            public void onFail(String error) {
                mIView.showError(error);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IMyCheckModel createModel() {
        return new IMyCheckIModelImpl();
    }
}
