package com.cxyz.check.ipresenter;

import com.cxyz.check.model.IListViewModel;
import com.cxyz.check.view.IListView;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;

/**
 * Created by 28058 on 2018/9/26.
 */

public abstract class IListViewPresenter extends IBasePresenter<IListViewModel,IListView> {

    public IListViewModel mModel;
    IBaseView mView;

   /* public abstract void initListView(Activity activity, ListView mListView, StuInfo_Check StuInfo_Check);

    public abstract ArrayList<HashMap<String,Object>> getstuInfo_list(StuInfo_Check StuInfo_Check);

    public abstract  StuInfo_Check getStuInfo_Check(User user);*/
}
