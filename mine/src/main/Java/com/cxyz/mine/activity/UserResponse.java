package com.cxyz.mine.activity;

import android.app.AlertDialog;
import android.view.LayoutInflater;
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
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_layout,null );
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setView(view);
                builder.setTitle("考勤详情");
                builder.create();
                builder.show();
            }
        });
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
