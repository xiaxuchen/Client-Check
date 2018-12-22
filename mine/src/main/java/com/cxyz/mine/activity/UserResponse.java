package com.cxyz.mine.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class UserResponse extends BaseActivity {
    private Button bt_userresponse_commit;
    private TitleView tv_userresponse_title;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_userreponse_layout;

    }

    @Override
    public void initView() {
        bt_userresponse_commit=findViewById(R.id.bt_userresponse_commit);
        tv_userresponse_title=findViewById(R.id.tv_userresponse_title);
        tv_userresponse_title.setTitle("用户反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        bt_userresponse_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        tv_userresponse_title.setBackClickListener(v -> onBackPressed());
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
