package com.cxyz.mine.MineView.mineactivity.settingactivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePresenter.presenter.IMinePresenter;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class Setting_responseActivity extends BaseActivity implements View.OnClickListener{
    private IMinePresenter iMinePresenter;
   private TitleView tvsetting_responsetitle;
    private  EditText edsetting_eduserresponse;
    private  Button btsetting_submit;
    private ProgressBar pbsetting_submit;
    @Override
    public int getContentViewId() {
        return R.layout.activity_setting_userresponse_layout;
    }

    @Override
    public void initView() {
        iMinePresenter=new IMinePresenter();
        pbsetting_submit= (ProgressBar) findViewById(R.id.pbsetting_submit);
        tvsetting_responsetitle= (TitleView) findViewById(R.id.tvsetting_responsetitle);
        edsetting_eduserresponse= (EditText) findViewById(R.id.edsetting_eduserresponse);
        btsetting_submit= (Button) findViewById(R.id.btsetting_submit);
        tvsetting_responsetitle.setTitle("用户反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        edsetting_eduserresponse.setOnClickListener(this);
        btsetting_submit.setOnClickListener(this);
        tvsetting_responsetitle.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onNameClick() {

            }

            @Override
            public void onSetClick() {

            }
        });

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btsetting_submit:iMinePresenter.btsetting_submit();break;
        }

    }
}
