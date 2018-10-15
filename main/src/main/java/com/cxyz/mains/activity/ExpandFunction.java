package com.cxyz.mains.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;

/**
 * Created by Administrator on 2018/10/14.
 */

public class ExpandFunction extends BaseActivity {
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
        return R.layout.expandfunction_layout;
    }

    @Override
    public void initView() {
        bitmap1= BitmapFactory.decodeResource(getResources(),R.mipmap.newspaper);
        iv_expandfun_shcoolnews =findViewById(R.id.iv_expandfun_shcoolnews);
        iv_expandfun_shcoolnews.setBitmap(bitmap1);
        iv_expandfun_shcoolnews.setColor(Color.WHITE);
        iv_expandfun_shcoolnews.setOuterRingAlpha(50);
        bitmap2= BitmapFactory.decodeResource(getResources(),R.mipmap.notice);
        iv_expandfun_notice =findViewById(R.id.iv_expandfun_notice);
        iv_expandfun_notice.setBitmap(bitmap2);
        iv_expandfun_notice.setColor(Color.WHITE);
        iv_expandfun_notice.setOuterRingAlpha(50);
        bitmap3= BitmapFactory.decodeResource(getResources(),R.mipmap.classsight);
        iv_expandfun_classsight =findViewById(R.id.iv_expandfun_classsight);
        iv_expandfun_classsight.setBitmap(bitmap3);
        iv_expandfun_classsight.setColor(Color.WHITE);
        iv_expandfun_classsight.setOuterRingAlpha(50);
        bitmap4= BitmapFactory.decodeResource(getResources(),R.mipmap.member);
        iv_expandfun_member =findViewById(R.id.iv_expandfun_member);
        iv_expandfun_member.setBitmap(bitmap4);
        iv_expandfun_member.setColor(Color.WHITE);
        iv_expandfun_member.setOuterRingAlpha(50);
        bitmap5= BitmapFactory.decodeResource(getResources(),R.mipmap.communication);
        iv_expandfun_communication =findViewById(R.id.iv_expandfun_communication);
        iv_expandfun_communication.setBitmap(bitmap5);
        iv_expandfun_communication.setColor(Color.WHITE);
        iv_expandfun_communication.setOuterRingAlpha(50);
        bitmap6= BitmapFactory.decodeResource(getResources(),R.mipmap.classlist);
        iv_expandfun_classlist =findViewById(R.id.iv_expandfun_classlist);
        iv_expandfun_classlist.setBitmap(bitmap6);
        iv_expandfun_classlist.setColor(Color.WHITE);
        iv_expandfun_classlist.setOuterRingAlpha(50);
        tv_expand_title=findViewById(R.id.tv_expand_title);
        tv_expand_title.setTitle("更多功能");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_expand_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick() {
                onEditClick();
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
