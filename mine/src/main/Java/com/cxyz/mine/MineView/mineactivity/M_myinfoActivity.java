package com.cxyz.mine.MineView.mineactivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePresenter.presenter.IMyinfoPresenter;
import com.cxyz.mine.MinePresenter.ipresenter.IMyinfoView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class M_myinfoActivity extends BaseActivity<IMyinfoPresenter> implements View.OnClickListener,IMyinfoView{
    private Switch stmyinfo_switch;
    private ImageView ivmyinfo_headimg;
    private TextView tvmyinfo_name,tvmyinfo_sex,tvmyinfo_schoolcode,tvmyinfo_class,
            tvmyinfo_college,tvmyinfo_edpower,tvmyinfo_tel;
    private  TitleView tvmyinfo_title;
    @Override
    public int getContentViewId() {
        return R.layout.activity_myinfo_layout;
    }

    @Override
    public void initView() {
        ivmyinfo_headimg= (ImageView) findViewById(R.id.ivmyinfo_headimg);
        tvmyinfo_sex= (TextView) findViewById(R.id.tvmyinfo_sex);
        tvmyinfo_schoolcode= (TextView) findViewById(R.id.tvmyinfo_schoolcode);
        tvmyinfo_class= (TextView) findViewById(R.id.tvmyinfo_class);
        tvmyinfo_college= (TextView) findViewById(R.id.tvmyinfo_college);
        tvmyinfo_edpower= (TextView) findViewById(R.id.tvmyinfo_edpower);
        tvmyinfo_tel= (TextView) findViewById(R.id.tvmyinfo_tel);
        stmyinfo_switch= (Switch) findViewById(R.id.stmyinfo_switch);
        tvmyinfo_name = (TextView) findViewById(R.id.tvmyinfo_name);
        tvmyinfo_title = (TitleView) findViewById(R.id.tvmyinfo_title);
        tvmyinfo_title.setTitle("个人信息");

    }

    @Override
    public void initData() {


    }

    @Override
    public void setEvent() {
        stmyinfo_switch.setOnClickListener(this);
        ivmyinfo_headimg.setOnClickListener(this);
        tvmyinfo_sex.setOnClickListener(this);
        tvmyinfo_schoolcode.setOnClickListener(this);
        tvmyinfo_class.setOnClickListener(this);
        tvmyinfo_college.setOnClickListener(this);
        tvmyinfo_edpower.setOnClickListener(this);
        tvmyinfo_tel.setOnClickListener(this);
        tvmyinfo_title.setOnClickListener(new TitleView.OnClickListener() {
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
        iPresenter.getInfo();
        iPresenter.getClassname();
        iPresenter.getCollege();

    }

    @Override
    protected IMyinfoPresenter createIPresenter() {
        return new IMyinfoPresenter();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stmyinfo_switch:stmyinfo_switch();
        }

    }
    public void stmyinfo_switch(){

        if (stmyinfo_switch.isChecked()){

            ToastUtil.showShort("已开启");
        }else {

            ToastUtil.showShort("已关闭");
        }
    }

    @Override
    public void showMyInfo(User info) {
        tvmyinfo_name.setText(info._name);
        tvmyinfo_sex.setText(info.sex);
        tvmyinfo_schoolcode.setText(info._id);
        tvmyinfo_edpower.setText(info.power+"");
        tvmyinfo_tel.setText(info.tel);

    }

    @Override
    public void showMyClass(Grade grade) {
        tvmyinfo_class.setText(grade._name);
    }

    @Override
    public void showMyCollege(College college) {
        tvmyinfo_college.setText(college._name);
    }


}
