package com.cxyz.mine.IPresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.mine.IPresenter.IChangePwdPresenter;
import com.cxyz.mine.imodel.impl.IChangePwdModelmpl;
import com.cxyz.mine.imodel.IChangePwdModel;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public class IChangePwdPresenterImpl extends IChangePwdPresenter {



    @Override
    public IChangePwdModel createModel() {
        return new IChangePwdModelmpl();
    }


    @Override
    public void alterPwd(String originPwd, String newPwd) {
        mIView.showLoadingView();
        mIModle.alterPwd(originPwd, newPwd, new IBaseModel.ModelListener<String, String>() {
            @Override
            public void onSuccess(String data) {
                mIView.changeSuccess(data);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.chanegFail(s);
                mIView.hideLoadingView();
            }
        });
    }
}
