package com.cxyz.mine.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2018/10/17.
 */

public class check extends BaseActivity {
    private Calendar calender;
    private int bMonth,fMonth;
    private int bDay,fDay;
    private int bYear,fYear;
    private TextView tv_check_setbegintime;
    private  TextView tv_check_setfinshtime;
    private TitleView check_title;
    private PieChartData  checkchartdata;
    private PieChartView check_piechart;
    List<SliceValue> values = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
    private int[] data = {30, 20, 10, 15};
    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#2bc208"),};
    private String[] stateChar = {"缺勤", "请假", "迟到", "正常"};
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public int getContentViewId() {
        return R.layout.checklayout;
    }

    @Override
    public void initView() {
        calender = Calendar.getInstance();
        tv_check_setbegintime=findViewById(R.id.tv_check_setbegintime) ;
        tv_check_setfinshtime=findViewById(R.id.tv_check_setfinshtime);
        check_title=findViewById(R.id.check_title);
        check_piechart=findViewById(R.id.check_piechart);
        check_title.setTitle("考勤统计");
        initPieChart();

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
        setPieChartData();
    }

    @Override
    public void setEvent() {
        check_piechart.setOnValueTouchListener(checkListener);//设置点击事件监听
        check_title.setOnClickListener(new TitleView.OnClickListener() {
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
        tv_check_setfinshtime.setOnClickListener(tvfinshtimeListner);
        tv_check_setbegintime.setOnClickListener(tvbegintimeListner);

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
     private void initPieChart() {
         checkchartdata = new PieChartData();
         checkchartdata.setCenterText1FontSize(12);
         checkchartdata.setCenterText2FontSize(10);
         checkchartdata.setHasLabelsOutside(false); //扇形外面是否有标语
         checkchartdata.setHasLabelsOnlyForSelected(false);
         checkchartdata.setHasLabels(true);//显示表情
         checkchartdata.setHasLabelsOnlyForSelected(true);//不用点击显示占的百分比
         checkchartdata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
         checkchartdata.setHasCenterCircle(true);//是否是环形显示
         checkchartdata.setValues(values);//填充数据
         checkchartdata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
         checkchartdata.setCenterCircleScale(0.5f);//设置环形的大小级别
         check_piechart.setPieChartData(checkchartdata);
         check_piechart.setValueSelectionEnabled(true);//选择饼图某一块变大
         check_piechart.setAlpha(0.9f);//设置透明度
         check_piechart.setCircleFillRatio(1f);//设置饼图大小
         check_piechart.setViewportCalculationEnabled(true);//设置饼图自动适应大小

    }
    //圆饼图监听
    private PieChartOnValueSelectListener checkListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            checkchartdata.setCenterText1(stateChar[arg0]);
            checkchartdata.setCenterText1Color(colorData[arg0]);
            checkchartdata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            checkchartdata.setCenterText2Color(colorData[arg0]);

          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
            ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
        }
    };
    //开始时间对话框
    private  View.OnClickListener  tvbegintimeListner=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            new DatePickerDialog(check.this, begintimetListener, bYear, bMonth, bDay).show();
          ;

        }
    };
    private DatePickerDialog.OnDateSetListener begintimetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            bYear = year;
            bMonth = monthOfYear;
            bDay = dayOfMonth;
            String days;
            if (bMonth + 1 < 10) {
                if (bDay < 10) {
                    days = new StringBuffer().append(bYear).append("年").append("0").
                            append(bMonth + 1).append("月").append("0").append(bDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(bYear).append("年").append("0").
                            append(bMonth + 1).append("月").append(bDay).append("日").toString();
                }

            } else {
                if (bDay < 10) {
                    days = new StringBuffer().append(bYear).append("年").
                            append(bMonth + 1).append("月").append("0").append(bDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(bYear).append("年").
                            append(bMonth + 1).append("月").append(bDay).append("日").toString();
                }

            }
            tv_check_setbegintime.setText(days);
        }
    };
    //结束时间对话框
    private  View.OnClickListener  tvfinshtimeListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(check.this, finishtimetListener, fYear, fMonth, fDay).show();
        }
    };
    private DatePickerDialog.OnDateSetListener finishtimetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            fYear = year;
            fMonth = monthOfYear;
            fDay = dayOfMonth;
            String days;
            if (fMonth + 1 < 10) {
                if (fDay < 10) {
                    days = new StringBuffer().append(fYear).append("年").append("0").
                            append(fMonth + 1).append("月").append("0").append(fDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(fYear).append("年").append("0").
                            append(fMonth + 1).append("月").append(fDay).append("日").toString();
                }

            } else {
                if (fDay < 10) {
                    days = new StringBuffer().append(fYear).append("年").
                            append(fMonth + 1).append("月").append("0").append(fDay).append("日").toString();
                } else {
                    days = new StringBuffer().append(fYear).append("年").
                            append(fMonth + 1).append("月").append(fDay).append("日").toString();
                }

            }
            tv_check_setfinshtime.setText(days);
        }
    };



    private String calPercent(int i) {
        String result = "";
        int sum = 0;
        for (int i1 = 0; i1 < data.length; i1++) {
            sum += data[i1];
        }
        result = String.format("%.2f", (float) data[i] * 100 / sum) + "%";
        return result;
    }
    private void setPieChartData() {

        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            sliceValue.setLabel(stateChar[i]);
            values.add(sliceValue);
        }
    }


   /* DatePickerDialog dialog = new
            DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int
                dayOfMonth) {

            if(monthOfYear<=9){
                mouth1="0"+(monthOfYear+1);
            }else{
                mouth1=String.valueOf(monthOfYear+1);
            }
            if(dayOfMonth<=9){
                day1= "0"+dayOfMonth;
            }else{
                day1=String.valueOf(dayOfMonth);
            }
            dateStr = String.valueOf(year)+"-"+mouth1+"-"+day1;
            tv_check_setbegintime.setText(dateStr);


        }
    }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH));*/


}
