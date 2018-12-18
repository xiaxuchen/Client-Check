package com.cxyz.homepage.acitivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;
import com.cxyz.homepage.adapter.CheckRecordListAdapter;
import com.cxyz.homepage.bean.CheckRecordBean;
import com.cxyz.homepage.dto.TaskRecordDto;

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

public class CheckRedordActivity extends BaseActivity {
    private TaskRecordDto info;
    private Calendar calender;
    private int bMonth,fMonth;
    private int bDay,fDay;
    private int bYear,fYear;
    private TextView tv_check_setbegintime;
    private  TextView tv_check_setfinshtime;
    private Spinner sp_check_classname;
    private TextView tv_check_classname;
    private TextView tv_check_actul;
    private TextView tv_check_apply;
    private TextView tv_check_late;
    private TextView tv_check_absent;
    private TextView tv_check_moreinfo;
    private TitleView tv_check_title;
    private LinearLayout ll_check_apply;
    private LinearLayout ll_check_late;
    private LinearLayout ll_check_absent;
    private String spstring;
    private PieChartData checkchartdata;
    private PieChartView check_piechart;
    private ListView lv_ckeck_stuinfo;
    private List<CheckRecordBean>  mData;
    List<SliceValue> values = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
   /* private int[] data = {30, 20, 10, 15};*/
    private int[] data ;/*= {info.getAbsent(), info.getApply(), info.getLate(), info.getNormal()};*/
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
        return R.layout.activity_checkrecordlayout;
    }

    @Override
    public void initView() {
        lv_ckeck_stuinfo=findViewById(R.id.lv_ckeck_stuinfo);
        calender = Calendar.getInstance();
        tv_check_setbegintime=findViewById(R.id.tv_check_setbegintime) ;
        tv_check_setfinshtime=findViewById(R.id.tv_check_setfinshtime);
        tv_check_title=findViewById(R.id.tv_check_title);
        check_piechart=findViewById(R.id.check_piechart);
        tv_check_title.setTitle("考勤统计");
        initPieChart();
        sp_check_classname=findViewById(R.id.sp_check_classname);
        tv_check_classname=findViewById(R.id.tv_check_classname);
        tv_check_actul=findViewById(R.id.tv_check_actul);
        tv_check_apply=findViewById(R.id.tv_check_apply);
        tv_check_late=findViewById(R.id.tv_check_late);
        tv_check_absent=findViewById(R.id.tv_check_absent);
        ll_check_apply=findViewById(R.id.ll_check_apply);
        ll_check_late=findViewById(R.id.ll_check_late);
        ll_check_absent=findViewById(R.id.ll_check_absent);
        int all=info.getAbsent()+info.getNormal()+info.getLate()+info.getApply();
        int actul=info.getLate()+info.getNormal();
           tv_check_actul.setText("实际到达人数"+actul+"/"+all+"人");
        tv_check_apply.setText(""+info.getApply());
        tv_check_absent.setText(""+info.getAbsent());
        tv_check_late.setText(""+info.getLate());
        LayoutInflater inflater = getLayoutInflater();
        //创建自定义Adapter的对象
        CheckRecordListAdapter adapter = new CheckRecordListAdapter(inflater, mData);
        //将布局添加到ListView中
        lv_ckeck_stuinfo.setAdapter(adapter);
    }

    @Override
    public void initData() {

        info=new TaskRecordDto();
        calender= Calendar.getInstance();
        bYear = calender.get(Calendar.YEAR);
        bMonth = calender.get(Calendar.MONTH);
        bDay = calender.get(Calendar.DAY_OF_MONTH);
        fYear = calender.get(Calendar.YEAR);
        fMonth = calender.get(Calendar.MONTH);
        fDay = calender.get(Calendar.DAY_OF_MONTH);
        loadinfo(info);
        data=new int [4];
        data[0]=info.getAbsent();
        data[1]=info.getApply();
        data[2]=info.getLate();
        data[3]=info.getNormal();
        setPieChartData();
        mData = new ArrayList<CheckRecordBean>();
        CheckRecordBean tangmaru = new CheckRecordBean("唐马儒", "17478001", "离散数学","12-09-14:17");
        CheckRecordBean lisi = new CheckRecordBean("张全蛋", "17478002", "数据库概论","11-08-14:17");
        CheckRecordBean zhangsan = new CheckRecordBean("大队长", "17478003", "马克思主义基本原理","12-09-14:17");
        CheckRecordBean wangwu = new CheckRecordBean("李春花", "17478004", "体育","12-09-14:17");
        mData.add(tangmaru);
        mData.add(zhangsan);
        mData.add(lisi);
        mData.add(wangwu);
    }

    @Override
    public void setEvent() {
        sp_check_classname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShort(""+sp_check_classname.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ll_check_apply.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               /* tv_check_moreinfo.setText(info.getApplyname());*/
            }
        });
        ll_check_late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  tv_check_moreinfo.setText(info.getLatename());*/
            }
        });
        ll_check_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* tv_check_moreinfo.setText(info.getAbsentname());*/
            }
        });
        check_piechart.setOnValueTouchListener(checkListener);//设置点击事件监听
        tv_check_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick(View v) {
                onBackPressed();
            }

            @Override
            public void onMoreClick(View v) {

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
         checkchartdata.setHasLabelsOutside(true); //扇形外面是否有标语
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
            new DatePickerDialog(CheckRedordActivity.this, begintimetListener, bYear, bMonth, bDay).show();
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
            info.setStarttime(DateUtil.stringToDate(days, DateUtil.DatePattern.ONLY_DAY));
        }
    };
    //结束时间对话框
    private  View.OnClickListener  tvfinshtimeListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(CheckRedordActivity.this, finishtimetListener, fYear, fMonth, fDay).show();
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
            info.setEndtime(DateUtil.stringToDate(days, DateUtil.DatePattern.ONLY_DAY));
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
private void loadinfo(TaskRecordDto info){
    info.setAbsent(1);
    info.setApply(2);
    info.setLate(3);
    info.setNormal(48);
    info.setAbsentname("张全蛋");
    info.setApplyname("唐马儒"+","+"大队长");
    info.setLatename("王尼玛"+","+"王蜜桃"+","+"木子");

}
}
