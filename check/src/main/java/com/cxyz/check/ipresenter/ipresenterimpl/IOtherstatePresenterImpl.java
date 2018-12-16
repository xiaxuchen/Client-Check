package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.ipresenter.IOtherstatePresenter;
import com.cxyz.check.model.IOtherstateModel;
import com.cxyz.check.model.imodelimpl.IOtherstateModelImpl;

/**
 * Created by Administrator on 2018/12/15.
 */

public class IOtherstatePresenterImpl extends IOtherstatePresenter {
    @Override
    public void commitOtherstate(CommitCheckDto dto) {
        mIView.showLoadingView();
        mIModle.commitOtherstate(dto, new IOtherstateModel.CommitListener() {
            @Override
            public void onSuccess(String info) {
                mIView.hideLoadingView();
                mIView.showCommitSuccess(info);
            }

            @Override
            public void onFail(String error) {
                mIView.hideLoadingView();
                mIView.showError(error);
            }
        });
    }

    @Override
    public IOtherstateModel createModel() {
        return new IOtherstateModelImpl();
    }
}
