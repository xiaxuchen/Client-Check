package com.cxyz.check.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.ArrayMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.cxyz.logiccommons.manager.UserManager;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = "/check/DailyCheckActivity")
public class DailyCheckActivity extends BaseActivity<IDailyPresenter> implements IDailyView {

    private ListView lv_stus;

    private QMUIEmptyView qmuiev_load;

    private Button btn_commit;

    private StusAdapter adapter;

    //是否显示已到达的cb
    private CheckBox cb_shownormal;

    //查找学生
    private AutoCompleteTextView act_find;

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
        lv_stus = (ListView) findViewById(R.id.lv_stus);
        qmuiev_load = (QMUIEmptyView) findViewById(R.id.qmuiev_load);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        cb_shownormal = findViewById(R.id.cb_shownormal);
        act_find = findViewById(R.id.act_find);

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

        lv_stus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showStateDialog(position,view);
            }
        });

        cb_shownormal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.isShowNormal(isChecked);
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
     * 点击list的item时显示选择状态的对话框
     * @param position
     * @param view
     */
    private void showStateDialog(final int position,final View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");

        int result = 2;
        final GradeStusDto stu = adapter.getItem(position);
        final CommitCheckDto.StuInfo stuInfo = stuInfoMap.get(stu.getId());
        if(stuInfo!=null)
            result = this.getIndex(adapter.values,stuInfo.getResult());
        builder.setSingleChoiceItems(adapter.items,result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which != 2)
                {
                    if(stuInfo==null)
                    {
                        CommitCheckDto.StuInfo stuInfo = new CommitCheckDto.StuInfo();
                        stuInfo.setId(stu.getId());
                        stuInfo.setResult(adapter.values[which]);
                        stuInfoMap.put(stu.getId(),stuInfo);
                    }else if(stuInfo.getResult() != adapter.values[which])
                    {
                        stuInfo.setResult(adapter.values[which]);
                        stuInfoMap.put(stu.getId(),stuInfo);
                    }

                }else
                {
                    if(stuInfo != null)
                    {
                        LogUtil.d("before"+stuInfoMap);
                        LogUtil.d(stuInfo.toString());
                        stuInfoMap.remove(stuInfo.getId());
                        LogUtil.d("after"+stuInfoMap);
                        dialog.dismiss();
                    }
                    else
                        return;
                }
                TextView tv_state = view.findViewById(R.id.tv_state);
                TextView tv_states = view.findViewById(R.id.tv_states);
                tv_state.setText(adapter.items[which]);
                LogUtil.d("color"+adapter.getStateColor(adapter.values[which]));
                tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
                tv_states.setTextColor(adapter.getStateColor(adapter.values[which]));
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
        ArrayAdapter<String> completionAdapter = new ArrayAdapter<String>
                (getApplicationContext(), android.R.layout.simple_dropdown_item_1line, adapter.getCompletion());
        act_find.setAdapter(completionAdapter);
        act_find.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                {
                }
                //让mPasswordEdit获取输入焦点
                    return true;
            }
        });
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

    @Override
    protected boolean isStateBar() {
        return false;
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
