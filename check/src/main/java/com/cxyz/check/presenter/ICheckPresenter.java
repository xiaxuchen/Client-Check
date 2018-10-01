package com.cxyz.check.presenter;

import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class ICheckPresenter extends IBasePresenter<ICheckModel,ICheckView> {

    StuInfo_Check StuInfo_Check;
    public ICheckModel mModel;
    IBaseView mView;

    /**
     * 从服务器获取学生数据，并显示到listview中
     */

    public abstract void getStusToShow();

    public abstract List getGridViewInfo();

    public abstract ArrayList<HashMap<String,Object>> getstuInfo_check(StuInfo_Check StuInfo_Check);

}
