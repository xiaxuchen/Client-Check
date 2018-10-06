package com.cxyz.homepage.acitivity;

import android.content.Intent;
import android.widget.ListView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.domain.RecordDetail;
import com.cxyz.commons.manager.UserManager;
import com.cxyz.homepage.R;
import com.cxyz.homepage.ipresenter.MassageList_Presenter;
import com.cxyz.homepage.ipresenter.MassageList_PresenterImpl;
import com.cxyz.homepage.iview.MassageListView;
import com.cxyz.homepage.myAdapter.Massage_SmpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个消息页面
 */

public class Massage_Activity extends BaseActivity<MassageList_Presenter> implements MassageListView{
    private ListView lv_massage;
    @Override
    public int getContentViewId() {
        return R.layout.activity_massage;
    }
    @Override
    public void initView() {
        lv_massage = (ListView)findViewById(R.id.lv_massage);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    public void initData() {
        //UserManager.getInstance().getUser().get_id()
        iPresenter.getMassageData(UserManager.getInstance().getUser().get_id());
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected MassageList_Presenter createIPresenter() {
        return new MassageList_PresenterImpl();
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected boolean isStateBar() {
        return true;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    @Override
    public void setListItem(List<RecordDetail> listItem) {
        lv_massage.setAdapter(new Massage_SmpleAdapter(getActivity(),listItem,R.layout.item_massage));
    }

    @Override
    public void refreshData(ArrayList inListDataBean) {

    }
}
