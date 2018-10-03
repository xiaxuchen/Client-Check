package com.cxyz.untilchecked;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.User;

/**
 * Created by 夏旭晨 on 2018/9/25.
 */

public class IExamplePresenterImpl extends IExamplePresenter {
    @Override
    public void login(String username, String password) {
        User user = new User();
        if("username".equals("zhangsan"))
            mIView.showLogin();
        else
            mIView.showError();
    }

    @Override
    public IBaseModel createModel() {
        return null;
    }
}
