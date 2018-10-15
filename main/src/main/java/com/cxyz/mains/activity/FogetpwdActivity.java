package com.cxyz.mains.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;


/**
 * Created by Administrator on 2018/10/13.
 */

public class FogetpwdActivity extends BaseActivity {
   private TitleView tv_forgetpwd_title;
   private Button bt_forgetpwd_next;


      /*  qmBtn = findViewById(R.id.button);
        QMUIRoundButtonDrawable background = (QMUIRoundButtonDrawable) qmBtn.getBackground();
        background.setBgData(ColorStateList.valueOf(getResources().getColor(R.color.app_off)));*/


    @Override
    public int getContentViewId() {
        return R.layout.activity_forgetpwd_layout;
    }

    @Override
    public void initView() {
        bt_forgetpwd_next=findViewById(R.id.bt_forgetpwd_next);
        tv_forgetpwd_title=findViewById(R.id.tv_forgetpwd_title);
        tv_forgetpwd_title.setTitle("忘记密码");


    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
     bt_forgetpwd_next.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
    /*   Intent intent=new Intent(getApplicationContext(),RestPwdActivity.class);
       startActivity(intent);*/
       Intent intent=new Intent(getApplicationContext(),MoreSetting.class);
       startActivity(intent);
      }
     });
     tv_forgetpwd_title.setOnClickListener(new TitleView.OnClickListener() {
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
}
