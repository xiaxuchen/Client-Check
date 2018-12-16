package com.cxyz.mine.activity;

import android.view.KeyEvent;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class ApponitmentActivity extends BaseActivity {
    private TitleView tv_appointment_title;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_appointment_layout;
    }

    @Override
    public void initView() {
        tv_appointment_title=findViewById(R.id.tv_appointment_title);
        tv_appointment_title.setTitle("请假预约");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_appointment_title.setBackClickListener(v -> onBackPressed());

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
            overridePendingTransition(R.anim.back_next,R.anim.back_exit);
        }
        return true;
    }
}
