package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.homepage.imodel.IHomeModel;
import com.cxyz.homepage.imodel.impl.IHomeModelImpl;
import com.cxyz.homepage.ipresenter.IHomePresenter;
import com.cxyz.logiccommons.domain.TaskInfo;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;

/**
 * Created by 28058 on 2018/9/29.
 */

public class IHomePresenterImpl extends IHomePresenter {


    @Override
    public void checkTask() {
        User u = UserManager.getInstance().getUser();
        mIView.showLoadingView();
        mIModle.checkComp(u.get_id(), u.getType(), new IHomeModel.CheckListener() {
            @Override
            public void onSuccess(TaskInfo taskInfo) {
                //请求成功显示成功逻辑
                mIView.hideLoadingView();
                mIView.showTask(taskInfo);
            }

            @Override
            public void onFail(String error) {
                //请求失败或数据错误显示失败逻辑
                mIView.hideLoadingView();
                mIView.showNoTask(error);
            }
        });
    }


    @Override
    public IHomeModel createModel() {
        return new IHomeModelImpl();
    }
}