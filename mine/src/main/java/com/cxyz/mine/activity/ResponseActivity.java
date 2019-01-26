package com.cxyz.mine.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.IPresenter.IResponsePresenter;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ResponseActivity extends BaseActivity<IResponsePresenter> implements View.OnClickListener {
    private TitleView tvsetting_responsetitle;
    private EditText etsetting_eduserresponse;
    private Button btsetting_submit;
    private ProgressBar pbsetting_submit;

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting_userresponse_layout;
    }

    @Override
    public void initView() {
        pbsetting_submit = (ProgressBar) findViewById(R.id.pbsetting_submit);
        tvsetting_responsetitle = (TitleView) findViewById(R.id.tvsetting_responsetitle);
        etsetting_eduserresponse = (EditText) findViewById(R.id.etsetting_eduserresponse);
        btsetting_submit = (Button) findViewById(R.id.btsetting_submit);
        tvsetting_responsetitle.setTitle("用户反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        etsetting_eduserresponse.setOnClickListener(this);
        btsetting_submit.setOnClickListener(this);
        tvsetting_responsetitle.setBackClickListener(v -> onBackPressed());

    }

    @Override
    protected IResponsePresenter createIPresenter() {
        return new IResponsePresenter();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btsetting_submit)
            iPresenter.btsetting_submit(etsetting_eduserresponse, pbsetting_submit);

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

