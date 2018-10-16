package com.cxyz.mains.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class MoreSetting extends BaseActivity {
    private TextView tv_moresetting_expand;
    private TitleView tv_moresetting_title;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.moresetting_layout;
    }

    @Override
    public void initView() {
        tv_moresetting_expand=findViewById(R.id.tv_moresetting_expand);
        tv_moresetting_title=findViewById(R.id.tv_moresetting_title);
        tv_moresetting_title.setTitle("更多设置");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_moresetting_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ExpandFunction.class);
                startActivity(intent);
            }
        });
        tv_moresetting_title.setOnClickListener(new TitleView.OnClickListener() {
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
}
