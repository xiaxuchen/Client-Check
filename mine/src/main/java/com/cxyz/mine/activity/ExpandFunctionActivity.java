package com.cxyz.mine.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class ExpandFunctionActivity extends BaseActivity {
    private TitleView tv_expand_title;
    private  CircleImage  iv_expandfun_notice;
    private  CircleImage  iv_expandfun_classsight;
    private CircleImage   iv_expandfun_member;
    private CircleImage  iv_expandfun_communication;
    private CircleImage iv_expandfun_classlist;
    private  CircleImage iv_expandfun_shcoolnews;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6;
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_expandfunction_layout;
    }

    @Override
    public void initView() {
        bitmap1= BitmapFactory.decodeResource(getResources(),R.mipmap.newspaper);
        iv_expandfun_shcoolnews =findViewById(R.id.iv_expandfun_shcoolnews);
        iv_expandfun_shcoolnews.setBitmap(bitmap1);
        initImage(iv_expandfun_shcoolnews);
        bitmap2= BitmapFactory.decodeResource(getResources(),R.mipmap.notice);
        iv_expandfun_notice =findViewById(R.id.iv_expandfun_notice);
        iv_expandfun_notice.setBitmap(bitmap2);
        initImage(iv_expandfun_notice);
        bitmap3= BitmapFactory.decodeResource(getResources(),R.mipmap.classsight);
        iv_expandfun_classsight =findViewById(R.id.iv_expandfun_classsight);
        iv_expandfun_classsight.setBitmap(bitmap3);
        initImage(iv_expandfun_classsight);
        bitmap4= BitmapFactory.decodeResource(getResources(),R.mipmap.member);
        iv_expandfun_member =findViewById(R.id.iv_expandfun_member);
        iv_expandfun_member.setBitmap(bitmap4);
        initImage(iv_expandfun_member);
        bitmap5= BitmapFactory.decodeResource(getResources(),R.mipmap.communication);
        iv_expandfun_communication =findViewById(R.id.iv_expandfun_communication);
        iv_expandfun_communication.setBitmap(bitmap5);
        initImage(iv_expandfun_communication);
        bitmap6= BitmapFactory.decodeResource(getResources(),R.mipmap.classlist);
        iv_expandfun_classlist =findViewById(R.id.iv_expandfun_classlist);
        iv_expandfun_classlist.setBitmap(bitmap6);
        initImage(iv_expandfun_classlist);
        tv_expand_title=findViewById(R.id.tv_expand_title);
        tv_expand_title.setTitle("更多功能");
    }

    private void initImage(CircleImage c)
    {
        c.setColor(Color.WHITE);
        c.setOuterRingAlpha(50);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_expand_title.setOnClickListener(new TitleView.OnClickListener() {
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
