package com.cxyz.homepage.acitivity;

import android.widget.ListView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.homepage.R;
import com.cxyz.homepage.ipresenter.MassageList_Presenter;
import com.cxyz.homepage.ipresenter.MassageList_PresenterImpl;

/**
 * 这是一个消息页面
 */

public class Massage_Activity extends BaseActivity<MassageList_Presenter> {
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
    public void initData() {
       // lv_massage.setAdapter();
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected MassageList_Presenter createIPresenter() {
        return new MassageList_PresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


}
