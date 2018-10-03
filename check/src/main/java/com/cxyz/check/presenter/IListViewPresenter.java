package com.cxyz.check.presenter;

import android.app.Activity;
import android.widget.ListView;

import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.model.IListViewModel;
import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.checkTools.showstus_Adapter;
import com.cxyz.check.view.IListView;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class IListViewPresenter extends IBasePresenter<IListViewModel,IListView> {

    StuInfo_Check StuInfo_Check;
    public IListViewModel mModel;
    IBaseView mView;

    public abstract void initListView(Activity activity, ListView mListView, StuInfo_Check StuInfo_Check);

    public abstract ArrayList<HashMap<String,Object>> getstuInfo_list(StuInfo_Check StuInfo_Check);

    public abstract  StuInfo_Check getStuInfo_Check(User user);
}
