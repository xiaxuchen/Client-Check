package com.cxyz.check;

import android.widget.ListView;

import com.cxyz.check.CheckModel.ICheckModel;
import com.cxyz.check.CheckPresenter.IListViewPresenter;
import com.cxyz.check.CheckPresenter.ICheckPresenter;
import com.cxyz.check.CheckView.ICheckView_Imp;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.domain.User;

/**
 * Created by 28058 on 2018/9/25.
 */

public class ListViewAcitivity extends BaseActivity<ICheckPresenter> implements IBaseView {

    ICheckView_Imp mView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_listview_layout;
    }

    @Override
    public void initView() {
        mView=new ICheckView_Imp();


    }

    @Override
    public void initData() {


        iPresenter.mModel=new ICheckModel();
        iPresenter.mModel.setmListView(((ListView)findViewById(R.id.check_list)));

        mView=new ICheckView_Imp();
        mView.initActivity(getActivity());

    }

    @Override
    public void setEvent() {

        new IListViewPresenter().initListView(this,iPresenter.mModel.getmListView(),iPresenter.mModel.getListViewInfo(new User()));

    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenter();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


}
