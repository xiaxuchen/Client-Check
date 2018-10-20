package com.cxyz.homepage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.homepage.R;
import com.cxyz.homepage.ipresenter.TaskInfoPresenter;
import com.cxyz.homepage.ipresenter.mpl.TakInfoPresenterImpl;
import com.cxyz.homepage.iview.TaskInfosPagerView;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;


/**
 * Created by 鱼塘主 on 2018/9/25.
 */
@Route(path="/homepage/HomeFragment")
public class HomeFragment extends BaseFragment<TaskInfoPresenter> implements TaskInfosPagerView{


    //用于显示第几周
    private TextView tv_week;
    //用于显示日期
    private TextView tv_date;
    //用于显示准确位置
    private TextView tv_location;
    //签到
    private LinearLayout ll_signin;
    //考勤图表
    private LinearLayout ll_checkpic;
    //月历课次
    private LinearLayout ll_month_lessons;
    //请假申请
    private LinearLayout ll_vacation;
    //班级课表
    private LinearLayout ll_grade_lessons;
    //成绩情况
    private LinearLayout ll_score;
    //统计报告
    private LinearLayout ll_statistic;
    //其他信息
    private LinearLayout ll_otherinfo;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle bundle) {

    }
    /**
     * 初始化view的
     * @param view mRootView
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_week = findViewById(R.id.tv_week);
        tv_date = findViewById(R.id.tv_date);
        tv_location = findViewById(R.id.tv_location);
        ll_checkpic = findViewById(R.id.ll_checkpic);
        ll_grade_lessons = findViewById(R.id.ll_grade_lessons);
        ll_month_lessons = findViewById(R.id.ll_month_lessons);
        ll_otherinfo = findViewById(R.id.ll_otherinfo);
        ll_score = findViewById(R.id.ll_score);
        ll_signin = findViewById(R.id.ll_signin);
        ll_statistic = findViewById(R.id.ll_statistic);
        ll_vacation = findViewById(R.id.ll_vacation);
    }
    @Override
    protected TaskInfoPresenter createIPresenter() {
        return new TakInfoPresenterImpl();
    }

    /**
     * 设置监听的
     */
    @Override
    protected void setListener() {
    }

    /**
     * 显示加载的
     */
    @Override
    public void showLoadingView() {
    }

    /**
     * 隐藏加载的
     */
    @Override
    public void hideLoadingView() {
    }

    /**
     * 接口回调的
     * @param taskInfosData
     */
    @Override
    public void setTaskInfosData(List<TaskInfo> taskInfosData) {
    }

}
