package com.cxyz.check.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.checkTools.showstus_Adapter;
import com.cxyz.check.presenter.IListViewPresenter;
import com.cxyz.check.presenter.ICheckPresenter;
import com.cxyz.check.R;
import com.cxyz.check.presenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.presenter.ipresenterimpl.IListViewPresenterImpl;
import com.cxyz.check.view.IListView;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 28058 on 2018/9/25.
 */

public class ListViewAcitivity extends BaseActivity<IListViewPresenter> implements IListView {


    @Override
    public int getContentViewId() {
        return R.layout.check_activity_listview_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        showstus_Adapter myAdapter = new showstus_Adapter(getActivity(),iPresenter.getstuInfo_list(iPresenter.getStuInfo_Check(new User())));
        ListView lv=(ListView)findViewById(R.id.listview_check);
        lv.setAdapter(myAdapter);
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IListViewPresenter createIPresenter() {
        return new IListViewPresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


}
