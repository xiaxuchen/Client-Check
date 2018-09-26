package com.cxyz.mine.M_Activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class M_appointmentActivity extends BaseActivity implements View.OnClickListener{
    private  Spinner m_appointment_spinner;
   private  TitleView m_appointment_title;
    private EditText m_appointment_edapply,m_appointment_edapplytime,m_appointment_edapplyreason;
    Button m_appointment_submit;
    @Override
    public int getContentViewId() {
        return R.layout.m_appointment_layout;
    }

    @Override
    public void initView() {
        m_appointment_edapply= (EditText) findViewById(R.id.m_appointment_edapply);
        m_appointment_edapplytime= (EditText) findViewById(R.id.m_appointment_edapplytime);
        m_appointment_edapplyreason= (EditText) findViewById(R.id.m_appointment_edapplyreason);
        m_appointment_submit= (Button) findViewById(R.id.m_appointment_submit);
        m_appointment_spinner= (Spinner) findViewById(R.id.m_appointment_spinner);
        m_appointment_title= (TitleView) findViewById(R.id.m_appointment_title);
        m_appointment_title.setTitle("请假预约");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        m_appointment_submit.setOnClickListener(this);
        m_appointment_title.setOnClickListener(new TitleView.OnClickListener() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.m_appointment_submit: m_appointment_submit();break;
        }

    }
    public void m_appointment_submit(){
        ToastUtil.showShort("已提交");
    }
   /* public void m_appointment_spinner(){
        m_appointment_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.timelength_data);
                ToastUtil.showShort(""+languages[position]);
            }
        });
 }*/
}
