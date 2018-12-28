package com.cxyz.homepage.acitivity;

import android.view.View;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;

/**
 * Created by ${喻济生} on 2018/11/24.
 */

public class NoDailyTaskActivity extends BaseActivity {
    private TitleView tv_nodailycheck_title;
    @Override
    public int getContentViewId() {
        return R.layout.activity_nodailycheck_layout;
    }

    @Override
    public void initView() {
        tv_nodailycheck_title=findViewById(R.id.tv_nodailycheck_title);
        tv_nodailycheck_title.setTitle("暂无考勤记录");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_nodailycheck_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick(View v) {
                onBackPressed();
            }

            @Override
            public void onMoreClick(View v) {

            }
        });

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
}
