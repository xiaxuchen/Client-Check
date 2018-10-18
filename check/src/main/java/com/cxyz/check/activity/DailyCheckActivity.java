package com.cxyz.check.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cxyz.check.R;
import com.cxyz.check.adapter.StusAdapter;
import com.cxyz.check.ipresenter.IDailyPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IDailyPresenterImpl;
import com.cxyz.check.view.IDailyView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.Student;
import com.cxyz.logiccommons.domain.TaskCompletion;
import com.cxyz.logiccommons.manager.UserManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyCheckActivity extends BaseActivity<IDailyPresenter> implements IDailyView {

    private TitleView tv_title;

    private ListView lv_stus;

    private ProgressBar pb_load;

    private Button btn_commit;

    private StusAdapter adapter;

    private TaskCompletion completion;

    //记录不良情况的map
    private Map<String,CheckRecord> crs;

    private AlertDialog alertDialog;

    //当前考勤任务
    private TaskCompletion c;

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_check_layout;
    }

    @Override
    public void initView() {
        tv_title = (TitleView) findViewById(R.id.tv_title);
        lv_stus = (ListView) findViewById(R.id.lv_stus);
        pb_load = (ProgressBar) findViewById(R.id.pb_load);
        btn_commit = (Button) findViewById(R.id.btn_commit);

        tv_title.setTitle("日常考勤");
    }

    @Override
    public void initData() {
        crs = new HashMap<>();
        c = new TaskCompletion(1);
        c.setState(TaskCompletion.NORMAL);
    }

    @Override
    public void setEvent() {
        tv_title.setOnClickListener(new TitleView.OnClickListenerWrapper()
        {
            @Override
            public void onBackClick() {
                super.onBackClick();
                onBackPressed();
            }
        });

        lv_stus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showStateDialog(position,view);
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.commit(crs,c);
            }
        });
    }

    /**
     * 显示选择状态的对话框
     * @param position
     * @param view
     */
    private void showStateDialog(final int position,final View view)
    {
        final String[] items = new String[]{"迟到","请假","已到达","缺勤","早退"};
        final int [] values = new int[]{CheckRecord.LATE,CheckRecord.VACATE,CheckRecord.CANCLE,CheckRecord.ABSENTEEISM,CheckRecord.EARLYLEAVE};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");
        //从crs中试图获取到记录，如果没有则是到达
        int result = 2;
        final Student stu = adapter.getItem(position);
        final CheckRecord r = crs.get(stu.get_id());
        if(r!=null)
            result = r.getResult();
        builder.setSingleChoiceItems(items,result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which != 2)
                {
                    if(r==null)
                    {
                        CheckRecord cr = new CheckRecord();
                        cr.setStudent(stu);
                        cr.setResult(values[which]);
                        crs.put(stu.get_id(),cr);
                    }else if(r.getResult() != values[which])
                    {
                        r.setResult(values[which]);
                        crs.put(stu.get_id(),r);
                    }

                }else
                {
                    if(r != null)
                    {
                        crs.remove(r);
                        dialog.dismiss();
                    }
                    else
                        return;
                }
                TextView tv_state = (TextView)view.findViewById(R.id.tv_state);
                tv_state.setText("当前状态:"+items[which]);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getStusToShow(((Student) UserManager.getInstance().getUser()).getGrade().get_id());
    }

    @Override
    protected IDailyPresenter createIPresenter() {
        return new IDailyPresenterImpl();
    }

    @Override
    public void showLoadingView() {
        pb_load.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        pb_load.setVisibility(View.GONE);
    }

    @Override
    public void showStus(List<Student> stus) {
        adapter = new StusAdapter(getActivity(),stus,R.layout.item_list_stus_layout);
        lv_stus.setAdapter(adapter);
    }

    @Override
    public void showError(Object error) {
        ToastUtil.showShort(error.toString());
    }

    @Override
    public void showCommitResult(String info) {
        ToastUtil.showShort(info);
    }

    /**
     * TODO 可能会出现异常情况，后期需要调整
     * @param intent 跳转时所用的
     */
    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        LogUtil.e("我正在初始comp");
        int compId = intent.getIntExtra("compId", -1);
        completion.set_id(compId);
    }
}
