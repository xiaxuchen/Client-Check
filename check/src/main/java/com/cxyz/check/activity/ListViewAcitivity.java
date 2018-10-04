package com.cxyz.check.activity;

import com.cxyz.check.R;
import com.cxyz.check.ipresenter.IListViewPresenter;
import com.cxyz.check.view.IListView;
import com.cxyz.commons.activity.BaseActivity;

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
/*        showstus_Adapter myAdapter = new showstus_Adapter(getActivity(),iPresenter.getstuInfo_list(iPresenter.getStuInfo_Check(new User())));
        ListView lv=(ListView)findViewById(R.id.listview_check);
        lv.setAdapter(myAdapter);*/
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IListViewPresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


}
