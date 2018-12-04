package com.cxyz.check.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.DateUtil;

/**
 * Created by Administrator on 2018/12/2.
 */

@Route(path = "/check/CheckActivity")
public class CheckActivity extends BaseActivity<ICheckPresenter> implements ICheckView {

    //开始考勤按钮
    private Button btn_start;

    //考勤任务名称
    private TextView tv_task_name;

    //考勤任务时间
    private TextView tv_task_time;

    //考勤教室
    private TextView tv_room;

    //考勤发起人（如果是课程则为课程老师）
    private TextView tv_sponsor;

    /**
     * 考勤任务
     */
    private RelativeLayout rl_task;

    /**
     * 进度
     */
    private ProgressBar pb_load;

    /**
     * 显示暂无考勤记录
     */
    private TextView tv_no_task;

    /**
     * 任务完成情况id
     */
    private int compId;

    @Override
    public int getContentViewId() {
        return R.layout.activity_check_layout;
    }

    @Override
    protected void afterInit() {
        iPresenter.checkTask();
    }

    @Override
    public void initView() {
        btn_start = findViewById(R.id.btn_start);
        tv_task_name = findViewById(R.id.tv_task_name);
        tv_task_time = findViewById(R.id.tv_task_time);
        tv_room = findViewById(R.id.tv_room);
        tv_sponsor = findViewById(R.id.tv_sponsor);
        pb_load = findViewById(R.id.pb_load);
        tv_no_task = findViewById(R.id.tv_no_task);
        rl_task = findViewById(R.id.rl_task);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btn_start.setOnClickListener(view->{
            Intent intent = new Intent(getActivity(),DailyCheckActivity.class);
            intent.putExtra("compId",compId);
            startActivity(intent);
        });
    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenterImpl();
    }

    @Override
    public void showTask(CheckTaskDto taskDto) {
        pb_load.setVisibility(View.INVISIBLE);
        rl_task.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.VISIBLE);
        tv_sponsor.setText(taskDto.getSponsorName());
        tv_room.setText(taskDto.getSpot());
        tv_task_time.setText(DateUtil.dateToString(taskDto.getStart(), DateUtil.DatePattern.ONLY_HOUR_MINUTE)
                +DateUtil.dateToString(taskDto.getEnd(), DateUtil.DatePattern.ONLY_HOUR_MINUTE));
        compId = taskDto.getId();
    }

    @Override
    public void showNoTask() {
        btn_start.setVisibility(View.INVISIBLE);
        pb_load.setVisibility(View.INVISIBLE);
        rl_task.setVisibility(View.INVISIBLE);
        tv_no_task.setVisibility(View.VISIBLE);
    }


    @Override
    public void showLoadTask() {
        rl_task.setVisibility(View.INVISIBLE);
        pb_load.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
    }
}
