package com.cxyz.mine.IPresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.mine.imodel.IMineFragmentModel;
import com.cxyz.mine.iview.IMineFragementView;

/**
 * Created by Administrator on 2018/10/22.
 */

public  abstract class IMineFragmentPresenter extends IBasePresenter<IMineFragmentModel,IMineFragementView>{
    /**
     * 更新app
     */
    public abstract void Update();

}
