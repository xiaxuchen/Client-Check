package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.homepage.imodel.IHomeModel;
import com.cxyz.homepage.imodel.impl.IHomeModelImpl;
import com.cxyz.homepage.ipresenter.IHomePresenter;

/**
 * Created by 28058 on 2018/9/29.
 */

public class IHomePresenterImpl extends IHomePresenter {


    @Override
    public IHomeModel createModel() {
        return new IHomeModelImpl();
    }
}