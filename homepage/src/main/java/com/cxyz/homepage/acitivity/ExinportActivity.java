package com.cxyz.homepage.acitivity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;
import com.cxyz.homepage.icon.IconfontModule;
import com.cxyz.logiccommons.application.MyApp;

import java.util.Calendar;

public class ExinportActivity extends BaseActivity {
private TitleView tv_port_title;
private TextView tv_port_setbegintime;
private TextView tv_port_setfinshtime;
private Calendar calender;
private int bMonth,fMonth;
private int bDay,fDay;
private int bYear,fYear;
    @Override
    public int getContentViewId() {
        MyApp.withIcon(new IconfontModule());
        return R.layout.activity_eximport_layout;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void initView() {
        tv_port_setfinshtime=findViewById(R.id.tv_port_setfinshtime);
        tv_port_setbegintime=findViewById(R.id.tv_port_setbegintime);
        tv_port_title=findViewById(R.id.tv_port_title);
        tv_port_title.setTitle("报表下载");

    }

    @Override
    public void initData() {
        calender= Calendar.getInstance();
        bYear = calender.get(Calendar.YEAR);
        bMonth = calender.get(Calendar.MONTH);
        bDay = calender.get(Calendar.DAY_OF_MONTH);
        fYear = calender.get(Calendar.YEAR);
        fMonth = calender.get(Calendar.MONTH);
        fDay = calender.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public void setEvent() {
        tv_port_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick(View v) {
                onBackPressed();
            }

            @Override
            public void onMoreClick(View v) {

            }
        });
        tv_port_setfinshtime.setOnClickListener(tvfinshtimeListner);
        tv_port_setbegintime.setOnClickListener(tvbegintimeListner);

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
    //开始时间对话框
    private  View.OnClickListener  tvbegintimeListner=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            new MyDatePickerDialog(getActivity(), begintimetListener, bYear, bMonth, bDay).show();
        }
    };
    private MyDatePickerDialog.OnDateSetListener begintimetListener = new MyDatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            bYear = year;
            bMonth = monthOfYear;
            bDay = dayOfMonth;
            String days;
            if (bMonth + 1 < 10) {
                if (bDay < 10) {
                    days = new StringBuffer().append(bYear).append("-").append("").
                            append(bMonth + 1).append("-").append("").append(bDay).append("").toString();
                } else {
                    days = new StringBuffer().append(bYear).append("-").append("").
                            append(bMonth + 1).append("-").append(bDay).append("").toString();
                }

            } else {
                if (bDay < 10) {
                    days = new StringBuffer().append(bYear).append("-").
                            append(bMonth + 1).append("-").append("").append(bDay).append("").toString();
                } else {
                    days = new StringBuffer().append(bYear).append("-").
                            append(bMonth + 1).append("-").append(bDay).append("").toString();
                }

            }
            tv_port_setbegintime.setText(days);
            LogUtil.e("timepicker回调");

        }
    };
    //结束时间对话框
    private  View.OnClickListener  tvfinshtimeListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new MyDatePickerDialog(getActivity(), finishtimetListener, fYear, fMonth, fDay).show();
        }
    };
    private MyDatePickerDialog.OnDateSetListener finishtimetListener = new MyDatePickerDialog
            .OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            fYear = year;
            fMonth = monthOfYear;
            fDay = dayOfMonth;
            String days;
            if (fMonth + 1 < 10) {
                if (fDay < 10) {
                    days = new StringBuffer().append(fYear).append("-").append("").
                            append(fMonth + 1).append("-").append("").append(fDay).append("").toString();
                } else {
                    days = new StringBuffer().append(fYear).append("-").append("").
                            append(fMonth + 1).append("-").append(fDay).append("").toString();
                }

            } else {
                if (fDay < 10) {
                    days = new StringBuffer().append(fYear).append("-").
                            append(fMonth + 1).append("-").append("").append(fDay).append("").toString();
                } else {
                    days = new StringBuffer().append(fYear).append("-").
                            append(fMonth + 1).append("-").append(fDay).append("").toString();
                }
            }
            tv_port_setfinshtime.setText(days);


        }
    };
}
