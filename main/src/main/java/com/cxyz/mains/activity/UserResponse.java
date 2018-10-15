package com.cxyz.mains.activity;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class UserResponse extends BaseActivity {
    private TitleView tv_userresponse_title;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.userreponse_layout;

    }

    @Override
    public void initView() {
        tv_userresponse_title=findViewById(R.id.tv_userresponse_title);
        tv_userresponse_title.setTitle("用户反馈");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_userresponse_title.setOnClickListener(new TitleView.OnClickListener() {
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
