package com.cxyz.mains.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mains.imodel.ISplashModel;
import com.cxyz.mains.iview.ISplashView;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public abstract class ISplashPresenter extends IBasePresenter<ISplashModel,ISplashView> {
    /**
     * 更新app
     */
    public abstract void Update();

    /**
     * 下载app
     */
    public abstract void download(String  url);

}
