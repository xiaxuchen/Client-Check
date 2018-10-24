package com.cxyz.mine.activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.domain.College;
import com.cxyz.logiccommons.domain.Grade;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mine.IPresenter.presenter.IMyinfoPresenter;
import com.cxyz.mine.R;
import com.cxyz.mine.iview.IMyinfoView;


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
    private CircleImage iv_myinfo_circleview;
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
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.beauty);
        iv_myinfo_circleview.setBitmap(bitmap);
        iv_myinfo_circleview.setmOuterRing(20);
        iv_myinfo_circleview.setOuterRingAlpha(0);
        //设置信息
        User u = UserManager.getInstance().getUser();
        tv_myinfo_username.setText(u.get_name());
        tv_myinfo_usersex.setText(u.getSex());
        tv_myinfo_usercollege.setText(u.getCollege()==null?u.getCollege_name():u.getCollege().get_name());
        tv_myinfo_usercode.setText("萍乡学院");
        tv_myinfo_usertel.setText(u.getTel()==null?"":u.getTel());
        if(u.getType() == User.STUDNET)
        {
            tv_myinfo_usercollege.setText(u.getCollege_name()==null?"暂无":u.getCollege_name());
            tv_myinfo_userclass.setText(u.getGrade().get_name());
        }

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
                overridePendingTransition(R.anim.back_next,R.anim.back_exit);
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
}
