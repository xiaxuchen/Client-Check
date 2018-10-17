package com.cxyz.check.ipresenter;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;

import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class ICheckPresenter extends IBasePresenter<IBaseModel,IBaseView> {

    /**
     * 从服务器获取学生数据，并显示到listview中
     */

    public abstract void getStusToShow();

    public abstract List getGridViewInfo();

}
