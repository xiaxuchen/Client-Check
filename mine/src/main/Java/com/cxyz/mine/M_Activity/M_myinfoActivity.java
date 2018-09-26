package com.cxyz.mine.M_Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.MinePrimary.IMinePresenter;
import com.cxyz.mine.MinePrimary.IMineView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class M_myinfoActivity extends BaseActivity<IMinePresenter> implements View.OnClickListener,IMineView{
    private Switch m_myinfo_switch;
    ImageView m_myinfo_headimg;
    TextView m_myinfo_name,m_myinfo_sex,m_myinfo_schoolcode,m_myinfo_class,
            m_myinfo_college,m_myinfo_edpower,m_myinfo_tel;
    private  TitleView m_myinfo_title;
    @Override
    public int getContentViewId() {
        return R.layout.m_myinfo_layout;
    }

    @Override
    public void initView() {
        m_myinfo_headimg= (ImageView) findViewById(R.id.m_myinfo_headimg);
        m_myinfo_sex= (TextView) findViewById(R.id.m_myinfo_sex);
        m_myinfo_schoolcode= (TextView) findViewById(R.id.m_myinfo_schoolcode);
        m_myinfo_class= (TextView) findViewById(R.id.m_myinfo_class);
        m_myinfo_college= (TextView) findViewById(R.id.m_myinfo_college);
        m_myinfo_edpower= (TextView) findViewById(R.id.m_myinfo_edpower);
        m_myinfo_tel= (TextView) findViewById(R.id.m_myinfo_tel);
        m_myinfo_switch= (Switch) findViewById(R.id.m_myinfo_switch);
        m_myinfo_name = (TextView) findViewById(R.id.m_myinfo_name);
        m_myinfo_title = (TitleView) findViewById(R.id.m_myinfo_title);
        m_myinfo_title.setTitle("个人信息");

    }

    @Override
    public void initData() {


    }

    @Override
    public void setEvent() {
        m_myinfo_switch.setOnClickListener(this);
        m_myinfo_headimg.setOnClickListener(this);
        m_myinfo_sex.setOnClickListener(this);
        m_myinfo_schoolcode.setOnClickListener(this);
        m_myinfo_class.setOnClickListener(this);
        m_myinfo_college.setOnClickListener(this);
        m_myinfo_edpower.setOnClickListener(this);
        m_myinfo_tel.setOnClickListener(this);
        m_myinfo_title.setOnClickListener(new TitleView.OnClickListener() {
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
    protected IMinePresenter createIPresenter() {
        return new IMinePresenter();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m_myinfo_switch:m_myinfo_switch();
        }

    }
    public void m_myinfo_switch(){

        if (m_myinfo_switch.isChecked()){
            ToastUtil.showShort("已开启");
        }else {
            ToastUtil.showShort("已关闭");
        }
    }

    @Override
    public void showMineInfo(User info) {
        m_myinfo_name.setText(info._name);
        m_myinfo_sex.setText(info.sex);
        m_myinfo_schoolcode.setText(info._id);
        m_myinfo_edpower.setText(info.power+"");
        m_myinfo_tel.setText(info.tel);

    }

    @Override
    public void showMineClass(Grade grade) {
        m_myinfo_class.setText(grade._name);
    }

    @Override
    public void showMineCollege(College college) {
        m_myinfo_college.setText(college._name);
    }


}
