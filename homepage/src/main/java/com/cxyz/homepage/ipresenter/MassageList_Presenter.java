package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.homepage.imodel.MassageList_Model;
import com.cxyz.homepage.iview.MassageListView;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public abstract class MassageList_Presenter extends IBasePresenter<MassageList_Model,MassageListView>{
    public abstract void getMassageData(String User_id);
}
