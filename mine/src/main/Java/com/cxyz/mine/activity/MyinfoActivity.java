package com.cxyz.mine.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;


/**
 * Created by Administrator on 2018/10/14.
 */

public class MyinfoActivity extends BaseActivity {
    private TitleView tv_myinfo_title;
    private  CircleImage iv_circleview;
    private Bitmap bitmap;
    @Override
    public int getContentViewId() {
        return R.layout.myinfo_layout;
    }




    @Override
    public void initView() {
        tv_myinfo_title=findViewById(R.id.tv_myinfo_title);
        iv_circleview=(CircleImage)findViewById(R.id.iv_circleview);
        tv_myinfo_title.setTitle("个人信息");
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.beauty);
        iv_circleview.setBitmap(bitmap);
        iv_circleview.setmOuterRing(20);
        iv_circleview.setOuterRingAlpha(0);


    }

    @Override
    public void initData() {

    }
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void setEvent() {
        tv_myinfo_title.setOnClickListener(new TitleView.OnClickListener() {
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
