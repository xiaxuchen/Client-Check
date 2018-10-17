package com.cxyz.mine.activity;

import android.graphics.Color;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2018/10/15.
 */

public class PieChartActivity extends BaseActivity {
    private TitleView tv_checkcondition_title;
    //饼形图控件
    private PieChartData piedayChardata,pieweekChardata,piemonthChardata,pietermChardata;
    //数据
    private PieChartView  pv_checkcondition_daycheck,pv_checkcondition_weekcheck,pv_checkcondition_monthcheck,pv_checkcondition_termcheck;
    List<SliceValue> values = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
    private int[] data = {30, 20, 10, 15};
    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#2bc208"),};
    private String[] stateChar = {"缺勤", "请假", "迟到", "正常"};



    @Override
    public int getContentViewId() {
        return R.layout.piechart_layout;
    }

    @Override
    public void initView() {
        tv_checkcondition_title=findViewById(R.id.tv_checkcondition_title);
        pv_checkcondition_daycheck =  findViewById(R.id.pv_checkcondition_daycheck);
        pv_checkcondition_weekcheck=findViewById(R.id.pv_checkcondition_weekcheck);
        pv_checkcondition_monthcheck=findViewById(R.id.pv_checkcondition_monthcheck);
        pv_checkcondition_termcheck=findViewById(R.id.pv_checkcondition_termcheck);
        tv_checkcondition_title.setTitle("考勤统计");
        initPieChartData();
        setPieChartData();
        initPieChart(piedayChardata,pv_checkcondition_daycheck);
        initPieChart(pieweekChardata,pv_checkcondition_weekcheck);
        initPieChart(piemonthChardata,pv_checkcondition_monthcheck);
        initPieChart(pietermChardata,pv_checkcondition_termcheck);

    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        pv_checkcondition_daycheck.setOnValueTouchListener(dayselectListener);//设置点击事件监听
        pv_checkcondition_weekcheck.setOnValueTouchListener(weekselectListener);
        pv_checkcondition_monthcheck.setOnValueTouchListener(monthselectListener);
        pv_checkcondition_termcheck.setOnValueTouchListener(termselectListener);
        tv_checkcondition_title.setOnClickListener(new TitleView.OnClickListener() {
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

    /**
     * 获取数据
     */
    private void setPieChartData() {

        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            sliceValue.setLabel(stateChar[i]);
            values.add(sliceValue);
        }
    }


    /**
     * 初始化
     */
   /* private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setCenterText1FontSize(12);
        pieChardata.setCenterText2FontSize(10);
        pieChardata.setHasLabelsOutside(false); //扇形外面是否有标语
        pieChardata.setHasLabelsOnlyForSelected(false);
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(true);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pv_checkcondition_daycheck.setPieChartData(pieChardata);
        pv_checkcondition_daycheck.setValueSelectionEnabled(true);//选择饼图某一块变大
        pv_checkcondition_daycheck.setAlpha(0.9f);//设置透明度
        pv_checkcondition_daycheck.setCircleFillRatio(1f);//设置饼图大小
        pv_checkcondition_daycheck.setViewportCalculationEnabled(true);//设置饼图自动适应大小

    }*/
   private  void  initPieChartData(){
       piedayChardata = new PieChartData();
       pieweekChardata = new PieChartData();
       piemonthChardata = new PieChartData();
       pietermChardata = new PieChartData();

   }
    private  void initPieChart(PieChartData pieChardata,PieChartView pieChartView ){
        pieChardata.setCenterText1FontSize(14);
        pieChardata.setCenterText2FontSize(8);
        pieChardata.setHasLabelsOutside(false); //扇形外面是否有标语
        pieChardata.setHasLabelsOnlyForSelected(false);
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(true);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pieChartView.setPieChartData(pieChardata);
        pieChartView.setValueSelectionEnabled(true);//选择饼图某一块变大
        pieChartView.setAlpha(0.9f);//设置透明度
        pieChartView.setCircleFillRatio(1f);//设置饼图大小
        pieChartView.setViewportCalculationEnabled(true);//设置饼图自动适应大小
    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener dayselectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            piedayChardata.setCenterText1(stateChar[arg0]);
            piedayChardata.setCenterText1Color(colorData[arg0]);
            piedayChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            piedayChardata.setCenterText2Color(colorData[arg0]);

          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
            ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
        }
    };
    private PieChartOnValueSelectListener weekselectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pieweekChardata.setCenterText1(stateChar[arg0]);
            pieweekChardata.setCenterText1Color(colorData[arg0]);
            pieweekChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            pieweekChardata.setCenterText2Color(colorData[arg0]);

          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
            ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
        }
    };
    private PieChartOnValueSelectListener monthselectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            piemonthChardata.setCenterText1(stateChar[arg0]);
            piemonthChardata.setCenterText1Color(colorData[arg0]);
            piemonthChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            piemonthChardata.setCenterText2Color(colorData[arg0]);

          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
            ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
        }
    };
    private PieChartOnValueSelectListener termselectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pietermChardata.setCenterText1(stateChar[arg0]);
            pietermChardata.setCenterText1Color(colorData[arg0]);
            pietermChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            pietermChardata.setCenterText2Color(colorData[arg0]);

          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
            ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
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

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
