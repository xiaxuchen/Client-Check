package com.cxyz.homepage.acitivity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;
import com.cxyz.homepage.adapter.CheckRecordListAdapter;
import com.cxyz.homepage.bean.CheckRecordBean;
import com.cxyz.homepage.dto.CheckTaskDto;
import com.cxyz.homepage.dto.GradeDto;
import com.cxyz.homepage.dto.StatisticDto;
import com.cxyz.homepage.dto.StatisticRecordDto;
import com.cxyz.homepage.icon.IconfontModule;
import com.cxyz.homepage.ipresenter.CheckRecordPresenter;
import com.cxyz.homepage.ipresenter.impl.CheckRecordPresenterlmpl;
import com.cxyz.homepage.view.CheckRecordView;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.joanzapata.iconify.Iconify;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2018/10/17.
 */

public class CheckRedordActivity extends BaseActivity<CheckRecordPresenter>  implements CheckRecordView{
    private ArrayAdapter<String> arrayAdapter;
    private String[] subject;
    private List<String> subjectList;
    private Calendar calender;
    private LinearLayout ll_check_early;
    private TextView tv_check_early;
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
    private TitleView tv_check_title;
    private LinearLayout ll_check_apply;
    private LinearLayout ll_check_late;
    private LinearLayout ll_check_absent;
    private PieChartData checkchartdata;
    private PieChartView check_piechart;
    private ListView lv_ckeck_stuinfo;
    private List<CheckRecordBean>  mData;
    int late=0,absent=0,early=0,vacate=0,normal=0;
    int   PersonCount;
    private LayoutInflater inflater;
    List<SliceValue> values = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
    private int[] data/* = {1,1,1,1,1}*/;
    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#1E90FF"),
            Color.parseColor("#2bc208")};
    private String[] stateChar = {"缺勤", "请假", "迟到", "早退","正常"};

    @Override
    public int getContentViewId() {
        Iconify.with(new IconfontModule());
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
        sp_check_classname=findViewById(R.id.sp_check_classname);
        tv_check_classname=findViewById(R.id.tv_check_classname);
        tv_check_actul=findViewById(R.id.tv_check_actul);
        tv_check_apply=findViewById(R.id.tv_check_apply);
        tv_check_late=findViewById(R.id.tv_check_late);
        tv_check_early=findViewById(R.id.tv_check_early);
        tv_check_absent=findViewById(R.id.tv_check_absent);
        ll_check_apply=findViewById(R.id.ll_check_apply);
        ll_check_late=findViewById(R.id.ll_check_late);
        ll_check_absent=findViewById(R.id.ll_check_absent);
        ll_check_early=findViewById(R.id.ll_check_early);
        /*初始化字符串数组*/
        subject = new String[] { "1701软件工程", "1702软件工程", "1701软件技术", "1702软件技术",
                "1701数字媒体","1702数字媒体","1701数字媒体（专）","1702数字媒体" };
        subjectList = new ArrayList<String>();

        for (int i = 0; i < subject.length; i++) {
            subjectList.add(subject[i]);
        }
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, subjectList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_check_classname.setAdapter(arrayAdapter);
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
       /* setPieChartData();*/
        mData = new ArrayList<CheckRecordBean>();
    }

    @Override
    public void setEvent() {
        //Spinner设置监听
        sp_check_classname.setBackgroundColor(Color.WHITE);
        sp_check_classname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               tv_check_classname.setText(sp_check_classname.getSelectedItem().toString()+"");
                TextView tv = (TextView)view;
                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);   //设置居中
                tv.setTextColor(getResources().getColor(R.color.app_black));
                getInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //早退监听
        ll_check_early.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_check_setbegintime.getText().toString().contains("2")||tv_check_setfinshtime.getText().toString().contains("2")){
                    iPresenter.getStasticRecord(tv_check_setbegintime.getText().toString(),
                            tv_check_setfinshtime.getText().toString(),1702,CheckRecordResult.EARLYLEAVE);
                }
            }
        });
        //请假监听
        ll_check_apply.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(tv_check_setbegintime.getText().toString().contains("2")||tv_check_setfinshtime.getText().toString().contains("2")){
                    iPresenter.getStasticRecord(tv_check_setbegintime.getText().toString(),
                            tv_check_setfinshtime.getText().toString(),1702,CheckRecordResult.VACATE);
                }
            }
        });
        //迟到监听
        ll_check_late.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_check_setbegintime.getText().toString().contains("2")||tv_check_setfinshtime.getText().toString().contains("2")){
                    iPresenter.getStasticRecord(tv_check_setbegintime.getText().toString(),
                            tv_check_setfinshtime.getText().toString(),1702,CheckRecordResult.LATE);
                }
            }
        });
        //缺勤监听
        ll_check_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_check_setbegintime.getText().toString().contains("2")||tv_check_setfinshtime.getText().toString().contains("2")){
                    iPresenter.getStasticRecord(tv_check_setbegintime.getText().toString(),
                            tv_check_setfinshtime.getText().toString(),1702,CheckRecordResult.ABSENTEEISM);
                }
            }
        });
        //饼状图设置点击事件监听
        check_piechart.setOnValueTouchListener(checkListener);
        //titlebar监听
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
    protected CheckRecordPresenterlmpl createIPresenter() {
        return new CheckRecordPresenterlmpl();
    }

    //开始时间对话框
    private  View.OnClickListener  tvbegintimeListner=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            new MyDatePickerDialog(CheckRedordActivity.this, begintimetListener, bYear, bMonth, bDay).show();
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
            tv_check_setbegintime.setText(days);
            LogUtil.e("caomima");
            getInfo();
        }
    };
    //结束时间对话框
    private  View.OnClickListener  tvfinshtimeListner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new MyDatePickerDialog(CheckRedordActivity.this, finishtimetListener, fYear, fMonth, fDay).show();
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
            tv_check_setfinshtime.setText(days);
            getInfo();

        }
    };




    @Override
    public void setCheckTaskData(CheckTaskDto CheckTaskData) {

    }

    @Override
    public void setGradeData(GradeDto gradeData) {

    }

    @Override
    public void setStatisticData(StatisticDto statisticData) {
        PersonCount=statisticData.getPersonCount();
        data = new int[5];
     for (int i=0;i<=PersonCount;i++) {
         if (statisticData.getResults().get(i).getResultType() == CheckRecordResult.ABSENTEEISM) {
             absent = statisticData.getResults().get(i).getCount();
             absent++;
         } else if (statisticData.getResults().get(i).getResultType() == CheckRecordResult.EARLYLEAVE) {
             early = statisticData.getResults().get(i).getCount();
             early++;
         } else if (statisticData.getResults().get(i).getResultType() == CheckRecordResult.LATE) {
             late = statisticData.getResults().get(i).getCount();
             late++;
         } else if (statisticData.getResults().get(i).getResultType() == CheckRecordResult.VACATE) {
             vacate = statisticData.getResults().get(i).getCount();
             vacate++;
         }
         tv_check_absent.setText(absent + "");
         tv_check_early.setText(early + "");
         tv_check_late.setText(late + "");
         tv_check_apply.setText(vacate + "");
         normal = PersonCount-absent-early-late-vacate;
         data[0] = absent;
         data[1] = vacate;
         data[2] = late;
         data[3] = early;
         data[4] = normal;
         tv_check_actul.setText("实际到达人数：" + normal + "/"+statisticData.getPersonCount());
     /*    private String[] stateChar = {"缺勤", "请假", "迟到", "早退","正常"};*/

         initPieChart();
         setPieChartData();
     }


    }
    @Override
    public void setStatisticRecordData(List<StatisticRecordDto> statisticRecordData) {
        mData=new ArrayList<CheckRecordBean>();
        inflater = getLayoutInflater();
        String time="";
        //创建自定义Adapter的对象
        CheckRecordListAdapter adapter = new CheckRecordListAdapter(inflater, mData);
        //将布局添加到ListView中
        lv_ckeck_stuinfo.setAdapter(adapter);
     /*   CheckRecordBean lisi = new CheckRecordBean("张全蛋", "17478002", "数据库概论","11-08-14:17");
        mData.add(lisi);*/
        for (int i=0;i<PersonCount;i++) {
            if (statisticRecordData.get(i).getStu().getId()!=null) {
                Timestamp ts=statisticRecordData.get(i).getTime();
                Date date = new Date();
                int month,day,hour,min;
                try{
                    date=ts;
                    month=date.getMonth();
                    day=date.getDay();
                    hour=date.getHours();
                    min=date.getMinutes();
                    time=month+"-"+day+"-"+hour+":"+min;
                }catch (Exception e){
                    e.printStackTrace();
                }
                CheckRecordBean stu = new CheckRecordBean(statisticRecordData.get(i).getStu().getName(), statisticRecordData.get(i).getStu().getId(),
                        statisticRecordData.get(i).getSubject(),time );
                mData.add(stu);
            }
        }


    }


    @Override
    public void showFail() {

    }
    public  void  getInfo(){
       if(tv_check_setbegintime.getText().toString().contains("2") && tv_check_setfinshtime.getText().toString().contains("2")){
           iPresenter.getRecord(tv_check_setbegintime.getText().toString(),tv_check_setfinshtime.getText().toString(),1702);
       }
    }
    private void initPieChart() {
        checkchartdata = new PieChartData();
        checkchartdata.setCenterText1FontSize(12);
        checkchartdata.setCenterText2FontSize(10);
        checkchartdata.setHasLabelsOutside(true); //扇形外面是否有标语
        checkchartdata.setHasLabelsOnlyForSelected(false);
        checkchartdata.setHasLabels(false);//显示表情
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

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {

            LogUtil.e(values);
            LogUtil.e(values.size());
            //选择对应图形后，在中间部分显示相应信息
            checkchartdata.setCenterText1(stateChar[arg0]);
            checkchartdata.setCenterText1Color(colorData[arg0]);
            checkchartdata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            checkchartdata.setCenterText2Color(colorData[arg0]);
          /*  Toast.makeText(getApplicationContext(), stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();*/
           // ToastUtil.showShort(stateChar[arg0] + ":" + value.getValue());
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
        values.clear();   //清除加载的数据，防止多次加载
        for (int j = 0; j < data.length; ++j) {
            SliceValue sliceValue = new SliceValue((float) data[j], colorData[j]);
            sliceValue.setLabel(stateChar[j]);
            values.add(sliceValue);
        }
    }
}
