package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.model.imodelimpl.ICheckModelImpl;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.logiccommons.typevalue.TaskType;

/**
 * Created by Administrator on 2018/12/3.
 */

public class ICheckPresenterImpl extends ICheckPresenter {

    @Override
    public void checkTask() {
        User u = UserManager.getInstance().getUser();
        mIView.showLoadTask();
        mIModle.checkComp(u.getId(), 0, TaskType.DAILYCHECK, new ICheckModel.CheckListener() {
            @Override
            public void onSuccess(CheckTaskDto taskDto) {
                //请求成功显示成功逻辑
                mIView.showTask(taskDto);
            }

            @Override
            public void onFail(String error) {
                //请求失败或数据错误显示失败逻辑
                mIView.hideLoadingView();
                mIView.showNoTask();
            }
        });
    }


    @Override
    public ICheckModel createModel() {
        return new ICheckModelImpl();
    }
}
