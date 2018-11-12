package com.cxyz.check.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.adapter.StusAdapter;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.ipresenter.IDailyPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IDailyPresenterImpl;
import com.cxyz.check.view.IDailyView;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.IView.IDefaultView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = "/check/DailyCheckActivity")
public class DailyCheckActivity extends BaseActivity<IDailyPresenter> implements IDailyView {

    private TitleView tv_title;

    private ListView lv_stus;

    private QMUIEmptyView qmuiev_load;

    private Button btn_commit;

    private StusAdapter adapter;

    //记录不良情况的map
    private Map<String,CommitCheckDto.StuInfo> stuInfoMap;

    private AlertDialog alertDialog;

    //当前考勤任务
    private int compId;

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_check_layout;
    }

    @Override
    public void initView() {
        tv_title = (TitleView) findViewById(R.id.tv_title);
        lv_stus = (ListView) findViewById(R.id.lv_stus);
        qmuiev_load = (QMUIEmptyView) findViewById(R.id.qmuiev_load);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        tv_title.setTitle("日常考勤");

        qmuiev_load.setDetailText("正在加载中...");
    }


    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            stuInfoMap = new ArrayMap<>();
        }else
        {
            stuInfoMap = new HashMap<>();
        }
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
                iPresenter.commit(stuInfoMap,compId);
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

        final int [] values = new int[]{CheckRecordResult.
                LATE,CheckRecordResult.VACATE,CheckRecordResult.NORMAL
                ,CheckRecordResult.ABSENTEEISM,CheckRecordResult.EARLYLEAVE};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");

        //从crs中试图获取到记录，如果没有则是到达
        int result = 2;
        final GradeStusDto stu = adapter.getItem(position);
        final CommitCheckDto.StuInfo stuInfo = stuInfoMap.get(stu.getId());
        if(stuInfo!=null)
            result = this.getIndex(values,stuInfo.getResult());
        builder.setSingleChoiceItems(items,result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which != 2)
                {
                    if(stuInfo==null)
                    {
                        CommitCheckDto.StuInfo stuInfo = new CommitCheckDto.StuInfo();
                        stuInfo.setId(stu.getId());
                        stuInfo.setResult(values[which]);
                        stuInfoMap.put(stu.getId(),stuInfo);
                    }else if(stuInfo.getResult() != values[which])
                    {
                        stuInfo.setResult(values[which]);
                        stuInfoMap.put(stu.getId(),stuInfo);
                    }

                }else
                {
                    if(stuInfo != null)
                    {
                        stuInfoMap.remove(stuInfo);
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

    private int getIndex(int array[],int value)
    {
        int i = 0;
        for(int v:array)
        {
            if(v==value)
                return i;
            i++;
        }
        return 2;
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getStusToShow(( UserManager.getInstance().getUser()).getGradeId());
    }

    @Override
    protected IDailyPresenter createIPresenter() {
        return new IDailyPresenterImpl();
    }

    @Override
    public void showStus(List<GradeStusDto> stus) {
        adapter = new StusAdapter(getActivity(),stus,stuInfoMap,R.layout.item_list_stus_layout);
        lv_stus.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {

        qmuiev_load.show(false, "", error, "重新加载", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 重新获取学生列表
                 */
                iPresenter.getStusToShow(UserManager.getInstance().getUser().getGradeId());
            }
        });
    }

    @Override
    public void showCommitError(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public void showCommitResult(String info) {
        ToastUtil.showShort(info);
        finish();
    }

    @Override
    public void hideLoadStus() {
        qmuiev_load.hide();
    }

    /**
     * @param intent 跳转时所用的
     */
    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        LogUtil.e("我正在初始comp");
        compId = intent.getIntExtra("compId", -1);
    }

    @Override
    protected IBaseView getIView() {
        return new IDefaultView(getActivity(),"正在提交",false);
    }
}
