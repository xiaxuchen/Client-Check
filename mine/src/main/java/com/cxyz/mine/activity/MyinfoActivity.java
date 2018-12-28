package com.cxyz.mine.activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.StringUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.domain.College;
import com.cxyz.logiccommons.domain.Grade;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mine.IPresenter.presenter.IMyinfoPresenter;
import com.cxyz.mine.R;
import com.cxyz.mine.iview.IMyinfoView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;


/**
 * Created by Administrator on 2018/10/14.
 */

public class MyinfoActivity extends BaseActivity <IMyinfoPresenter>implements IMyinfoView {
    private TextView tv_myinfo_username;
    private  TextView tv_myinfo_usersex;
    private  TextView tv_myinfo_usercode;
    private TextView tv_myinfo_userclass;
    private TextView  tv_myinfo_usercollege;
    private TextView tv_myinfo_usertel;
    private TitleView tv_myinfo_title;
    private QMUIRadiusImageView iv_myinfo_circleview;
    private Bitmap bitmap;
    @Override
    public int getContentViewId() {
        return R.layout.activity_myinfo_layout;
    }

    @Override
    public void initView() {
        tv_myinfo_usercode=findViewById(R.id.tv_myinfo_usercode);
        tv_myinfo_usersex=findViewById(R.id.tv_myinfo_usersex);
        tv_myinfo_username=findViewById(R.id.tv_myinfo_username);
        tv_myinfo_userclass=findViewById(R.id.tv_myinfo_userclass);
        tv_myinfo_usercollege=findViewById(R.id.tv_myinfo_usercollege);
        tv_myinfo_usertel=findViewById(R.id.tv_myinfo_usertel);
        tv_myinfo_title=findViewById(R.id.tv_myinfo_title);
        iv_myinfo_circleview=findViewById(R.id.iv_myinfo_circleview);
        tv_myinfo_title.setTitle("个人信息");
        iv_myinfo_circleview.setImageResource(R.drawable.beauty);
        iv_myinfo_circleview.setCircle(true);
        iv_myinfo_circleview.setBorderWidth(6);
        iv_myinfo_circleview.setClickable(true);
        iv_myinfo_circleview.setBorderColor(Color.GRAY);
        //设置信息
        User u = UserManager.getInstance().getUser();
        tv_myinfo_username.setText(StringUtil.nullStrToEmpty(u.getName()));
        tv_myinfo_usersex.setText(u.getSex());
        tv_myinfo_usercode.setText("萍乡学院");
        tv_myinfo_usertel.setText(StringUtil.nullStrToEmpty(u.getPhone()));
        tv_myinfo_usercollege.setText(StringUtil.nullStrToEmpty(u.getCollegeName()));
        tv_myinfo_userclass.setText(StringUtil.nullStrToEmpty(u.getGradeName()));

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
        tv_myinfo_title.setBackClickListener(v -> onBackPressed());
        iPresenter.getInfo();
        iPresenter.getClassname();
        iPresenter.getCollege();
    }

    @Override
    protected IMyinfoPresenter createIPresenter() {
        return new IMyinfoPresenter();
    }


    @Override
    public void showMyInfo(User info) {

    }

    @Override
    public void showMyClass(Grade grade) {

    }

    @Override
    public void showMyCollege(College college) {

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
