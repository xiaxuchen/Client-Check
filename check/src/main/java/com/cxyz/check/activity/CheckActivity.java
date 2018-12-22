package com.cxyz.check.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.check.icon.IconfontModule;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.logiccommons.application.MyApp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private TextView tv_info;

    //报告异常
    private TextView tv_otherstate;

    /**
     * 任务完成情况id
     */
    private int compId = -1;
    
    //刷新按钮
    private ImageView iv_refresh;

    //历史考勤
    private LinearLayout ll_history;

    //忘记考勤
    private LinearLayout ll_forget;

    //临时考勤
    private LinearLayout ll_part_time;

    @Override
    public int getContentViewId() {
        MyApp.withIcon(new IconfontModule());
        return R.layout.activity_check_layout;
    }

    @Override
    protected boolean eventBusEnable() {
        return true;
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
        tv_otherstate = findViewById(R.id.tv_otherstate);
        pb_load = findViewById(R.id.pb_load);
        tv_info = findViewById(R.id.tv_info);
        rl_task = findViewById(R.id.rl_task);
        iv_refresh = findViewById(R.id.iv_refresh);
        ll_forget = findViewById(R.id.ll_forget);
        ll_history = findViewById(R.id.ll_history);
        ll_part_time = findViewById(R.id.ll_part_time);
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
        iv_refresh.setOnClickListener(view -> /*重新加载*/iPresenter.checkTask());
        //点击历史考勤跳转至历史考勤界面
        ll_history.setOnClickListener(view->startActivity(CheckHistoryActivity.class));
        tv_otherstate.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(),OtherstateActivity.class);
            intent.putExtra("compId",compId);
            startActivity(intent);
        });

    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenterImpl();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdate(CheckTask task)
    {
        iPresenter.checkTask();
    };

    public static class CheckTask{}

    @Override
    public void showTask(CheckTaskDto taskDto) {
        pb_load.setVisibility(View.INVISIBLE);
        tv_otherstate.setVisibility(View.VISIBLE);
        rl_task.setVisibility(View.VISIBLE);
        tv_info.setText("");
        btn_start.setVisibility(View.VISIBLE);
        iv_refresh.setVisibility(View.INVISIBLE);
        tv_sponsor.setText(taskDto.getSponsorName());
        tv_room.setText(taskDto.getSpot());
        tv_task_time.setText(DateUtil.dateToString(taskDto.getStart(), DateUtil.DatePattern.ONLY_HOUR_MINUTE)
                +"-"+DateUtil.dateToString(taskDto.getEnd(), DateUtil.DatePattern.ONLY_HOUR_MINUTE));
        compId = taskDto.getId();
    }

    @Override
    public void showNoTask() {
        btn_start.setVisibility(View.INVISIBLE);
        tv_otherstate.setVisibility(View.INVISIBLE);
        iv_refresh.setVisibility(View.VISIBLE);
        pb_load.setVisibility(View.INVISIBLE);
        rl_task.setVisibility(View.INVISIBLE);
        tv_info.setText("暂无考勤任务!");
    }


    @Override
    public void showLoadTask() {
        rl_task.setVisibility(View.INVISIBLE);
        iv_refresh.setVisibility(View.INVISIBLE);
        tv_otherstate.setVisibility(View.INVISIBLE);
        tv_info.setText("正在检测考勤...");
        pb_load.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
    }

}
