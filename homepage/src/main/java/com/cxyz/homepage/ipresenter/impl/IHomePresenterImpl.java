package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.homepage.dto.CheckTaskDto;
import com.cxyz.homepage.imodel.IHomeModel;
import com.cxyz.homepage.imodel.impl.IHomeModelImpl;
import com.cxyz.homepage.ipresenter.IHomePresenter;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.logiccommons.typevalue.TaskType;

/**
 * Created by 28058 on 2018/9/29.
 */

public class IHomePresenterImpl extends IHomePresenter {


    @Override
    public void checkTask() {
        User u = UserManager.getInstance().getUser();
        mIView.showLoadingView();
        mIModle.checkComp(u.getId(), 0, TaskType.DAILYCHECK, new IHomeModel.CheckListener() {
            @Override
            public void onSuccess(CheckTaskDto taskDto) {
                //请求成功显示成功逻辑
                mIView.hideLoadingView();
                mIView.showTask(taskDto);
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