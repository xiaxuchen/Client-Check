package com.cxyz.check.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.adapter.StusAdapter;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.ipresenter.IDailyPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IDailyPresenterImpl;
import com.cxyz.check.other.Comparator;
import com.cxyz.check.other.MineMap;
import com.cxyz.check.view.IDailyView;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.IView.IDefaultView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.sideview.SideBar;
import com.cxyz.logiccommons.manager.UserManager;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

@Route(path = "/check/DailyCheckActivity")
public class DailyCheckActivity extends BaseActivity<IDailyPresenter> implements IDailyView {

    private ListView lv_stus;

    private QMUIEmptyView qmuiev_load;

    private Button btn_commit;

    /**
     * 字母导航
     */
    private SideBar sb_bar;

    private StusAdapter adapter;

    //是否显示已到达的cb,确认考勤结果复选框
    private CheckBox cb_shownormal,cb_check;

    //记录不良情况的map
    private MineMap<String, CommitCheckDto.StuInfo> stuInfoMap;

    //查找输入框
    private EditText et_find;

    //当前考勤任务
    private int compId;

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_check_layout;
    }

    @Override
    public void initView() {
        lv_stus =  findViewById(R.id.lv_stus);
        qmuiev_load =  findViewById(R.id.qmuiev_load);
        btn_commit =  findViewById(R.id.btn_commit);
        cb_shownormal = findViewById(R.id.cb_shownormal);
        et_find = findViewById(R.id.et_find);
        cb_check = findViewById(R.id.cb_check);
        sb_bar = findViewById(R.id.sb_bar);

        qmuiev_load.setDetailText("正在加载中...");
    }


    @Override
    public void initData() {
      stuInfoMap = new MineMap<>();
    }

    @Override
    public void setEvent() {

        //设置学生被点击后弹出对话框
        lv_stus.setOnItemClickListener(
            (AdapterView<?> parent, View view, int position, long id) -> {
                showStateDialog(position, view);
            }
        );

        //设置选择复选按钮后显示隐藏已到达学生
        cb_shownormal.setOnCheckedChangeListener(
            (CompoundButton buttonView, boolean isChecked) -> adapter.isShowNormal(isChecked)
        );

        //向服务器提交数据
        btn_commit.setOnClickListener(
            (View v) ->
            {
                if(cb_check.isChecked())
                {
                    iPresenter.commit(stuInfoMap, compId);
                }else
                {
                    confirmCommit();
                }
            }
        );

        //当在输入框中输入时触发
        et_find.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtil.e(s.toString());
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //设置右侧触摸监听
        sb_bar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    lv_stus.setSelection(position);
                }

            }
        });

    }

    /**
     * 点击list的item时显示选择状态的对话框
     *
     * @param position
     * @param view
     */
    private void showStateDialog(final int position, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");

        int result = 2;
        final GradeStusDto stu = adapter.getItem(position);
        final CommitCheckDto.StuInfo stuInfo = stuInfoMap.get(stu.getId());
        if (stuInfo != null)
            result = this.getIndex(adapter.values, stuInfo.getResult());
        builder.setSingleChoiceItems(adapter.items, result,(DialogInterface dialog, int which) ->{
                if (which != 2) {
                    if (stuInfo == null) {
                        CommitCheckDto.StuInfo info = new CommitCheckDto.StuInfo();
                        info.setId(stu.getId());
                        info.setResult(adapter.values[which]);
                        stuInfoMap.put(stu.getId(), info);
                    } else if (stuInfo.getResult() != adapter.values[which]) {
                        stuInfo.setResult(adapter.values[which]);
                        stuInfoMap.put(stu.getId(), stuInfo);
                    }

                } else {
                    if (stuInfo != null) {
                        LogUtil.d("before" + stuInfoMap);
                        LogUtil.d(stuInfo.toString());
                        stuInfoMap.remove(stuInfo.getId());
                        LogUtil.e(stuInfoMap.toString());
                        LogUtil.d("after" + stuInfoMap);
                        dialog.dismiss();
                    } else
                        return;
                }
                TextView tv_state = view.findViewById(R.id.tv_state);
                TextView tv_states = view.findViewById(R.id.tv_states);
                tv_state.setText(adapter.items[which]);
                LogUtil.d("color" + adapter.getStateColor(adapter.values[which]));
                tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
                tv_states.setTextColor(adapter.getStateColor(adapter.values[which]));
                dialog.dismiss();
        });
        builder.setNegativeButton("取消",
            (DialogInterface dialog, int which) -> {
                dialog.dismiss();
            }
        );
        builder.create().show();
    }

    /**
     * 提示用户确认考勤结果
     */
    private void confirmCommit()
    {
          new SweetAlertDialog(getActivity(),
                    SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("确认提交")
                    .setContentText("请检查记录的正确性,并勾选左下角的复选框\n我们将自动隐藏已到达,以方便检查")
                    .setConfirmText("知道了")
                    .setConfirmClickListener(
                            (SweetAlertDialog sDialog) ->
                            {
                                sDialog.dismissWithAnimation();
                                //直接隐藏已到达人员方便查看
                                if(!cb_shownormal.isChecked())
                                    cb_shownormal.setChecked(true);
                            }
                    )
                    .show();
    }


    /**
     * 传入一个数组和值，获取值所在的index
     * @param array 数组
     * @param value 值
     * @return 位置
     */
    private int getIndex(int array[], int value) {
        int i = 0;
        for (int v : array) {
            if (v == value)
                return i;
            i++;
        }
        return 2;
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getStusToShow((UserManager.getInstance().getUser()).getGradeId());
    }

    @Override
    protected IDailyPresenter createIPresenter() {
        return new IDailyPresenterImpl();
    }

    @Override
    public void showStus(List<GradeStusDto> stus) {
        //为list排序
        Collections.sort(stus, new Comparator());
        adapter = new StusAdapter(getActivity(), stus, stuInfoMap, R.layout.item_list_stus_layout);
        lv_stus.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {

        qmuiev_load.show(false, "", error, "重新加载", view ->
        {
            LogUtil.e("caoonima");
            ToastUtil.showShort("caonima");
            iPresenter.getStusToShow(UserManager.getInstance().getUser().getGradeId());
        }  );
    }

    @Override
    public void showCommitError(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public void showCommitResult(String info) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.SUCCESS_TYPE)
                .showCancelButton(false)
                .setTitleText(info)
                .setContentText("考勤记录提交完毕!")
                .setConfirmText("返回主菜单")
                .setConfirmClickListener(
                        (SweetAlertDialog dialog) ->
                        {
                            dialog.dismissWithAnimation();
                            finish();
                        }
                )
                .show();
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
        return new IDefaultView(getActivity(), "正在提交", false);
    }

    @Override
    public void showLoadingView() {
        qmuiev_load.show(true,"正在加载...",null,null,null);
    }
}
