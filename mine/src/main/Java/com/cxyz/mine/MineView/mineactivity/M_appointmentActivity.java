package com.cxyz.mine.MineView.mineactivity;

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
    private  Spinner spappointment_spinner;
   private  TitleView tvappointment_title;
    private EditText edappointment_apply,edappointment_applytime,edappointment_applyreason;
    Button btappointment_submit;
    @Override
    public int getContentViewId() {
        return R.layout.activity_appointment_layout;
    }
    @Override
    public void initView() {
        edappointment_apply= (EditText) findViewById(R.id.edappointment_apply);
        edappointment_applytime= (EditText) findViewById(R.id.edappointment_applytime);
        edappointment_applyreason= (EditText) findViewById(R.id.edappointment_applyreason);
        btappointment_submit= (Button) findViewById(R.id.btappointment_submit);
        spappointment_spinner= (Spinner) findViewById(R.id.spappointment_spinner);
        tvappointment_title= (TitleView) findViewById(R.id.tvappointment_title);
        edappointment_apply= (EditText) findViewById(R.id.edappointment_apply);
        edappointment_applytime=(EditText) findViewById(R.id.edappointment_applytime);
        edappointment_applyreason=(EditText)findViewById(R.id.edappointment_applyreason);
        tvappointment_title.setTitle("请假预约");

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btappointment_submit.setOnClickListener(this);
        tvappointment_title.setOnClickListener(new TitleView.OnClickListener() {
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
        spappointment_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] timelength = getResources().getStringArray(R.array.timelength_data);
                ToastUtil.showShort("你选择了 "+timelength[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

            case R.id.btappointment_submit: btappointment_submit();break;
        }

    }
    public void btappointment_submit(){
        ToastUtil.showShort("已提交");
    }

}
