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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

@Route(path = "/check/DailyCheckActivity")
public class DailyCheckActivity extends BaseActivity<IDailyPresenter> implements IDailyView {

    private ListView lv_stus;

    private QMUIEmptyView qmuiev_load;

    private Button btn_commit;

    private QMUIBottomSheet sl_current;

    //当前显示类型
    private TextView tv_current;

    /**
     * 字母导航
     */
    private SideBar sb_bar;

    private StusAdapter adapter;

    //是否显示已到达的cb,确认考勤结果复选框
    private CheckBox cb_check;

    //记录不良情况的map
    private MineMap<String, CommitCheckDto.StuInfo> stuInfoMap;

    //查找输入框
    private EditText et_find;

    //当前考勤任务
    private int compId;

    /**
     * radiobutton的id
     */
    private int ids[] = {R.id.rb_late,R.id.rb_vacate,R.id.rb_normal,
            R.id.rb_absenteeism,R.id.rb_earlyleave,R.id.rb_wait_dispose};

    /**
     * 待处理数量
     */
    private int waitCount = 0;


    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_check_layout;
    }

    @Override
    public void initView() {
        lv_stus =  findViewById(R.id.lv_stus);
        tv_current = findViewById(R.id.tv_current);
        et_find = findViewById(R.id.et_find);
        qmuiev_load =  findViewById(R.id.qmuiev_load);
        btn_commit =  findViewById(R.id.btn_commit);
        cb_check = findViewById(R.id.cb_check);
        sb_bar = findViewById(R.id.sb_bar);
        lv_stus.setEmptyView(qmuiev_load);
    }


    @Override
    public void initData() {
      stuInfoMap = new MineMap<>();
    }

    @Override
    public void setEvent() {

        tv_current.setOnClickListener(view -> {
            QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(getActivity());
            builder.addItem("显示全部");
            builder.addItem("显示迟到");
            builder.addItem("显示请假");
            builder.addItem("显示未到达");
            builder.addItem("显示缺勤");
            builder.addItem("显示早退");
            builder.addItem("显示待处理");
            builder.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                adapter.setCurrent(position - 1);
                tv_current.setText(tag);
                dialog.dismiss();
            });
            sl_current = builder.build();
            sl_current.show();
        });

        //设置学生被点击后弹出对话框
        lv_stus.setOnItemClickListener(
            (AdapterView<?> parent, View view, int position, long id) -> {
                showStateDialog(position, view);
            }
        );


        //向服务器提交数据
        btn_commit.setOnClickListener(
            (View v) ->
            {
                if(waitCount > 0)
                {
                    new SweetAlertDialog(getActivity(),
                            SweetAlertDialog.WARNING_TYPE)
                            .showCancelButton(false)
                            .setTitleText("提交异常")
                            .setContentText("有异常记录未处理!")
                            .setConfirmText("去处理")
                            .setConfirmClickListener(
                                    (SweetAlertDialog dialog) ->
                                    {
                                        dialog.dismissWithAnimation();
                                        tv_current.setText("显示待处理");
                                        adapter.setCurrent(5);
                                    }
                            )
                            .show();
                    return;
                }
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
        sb_bar.setOnTouchingLetterChangedListener((String s) -> {
                //如果没有加载则do nothing
                if(adapter == null || adapter.getList() == null || adapter.getList().size() == 0)
                    return;
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    lv_stus.setSelection(position);
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
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");

        int result = 2;
        final GradeStusDto stu = adapter.getItem(position);
        final CommitCheckDto.StuInfo stuInfo = stuInfoMap.get(stu.getId());
//        builder.setSingleChoiceItems(adapter.items, result,(DialogInterface dialog, int which) ->{
//                if (which != 2) {
//                    if (stuInfo == null) {
//                        CommitCheckDto.StuInfo info = new CommitCheckDto.StuInfo();
//                        info.setId(stu.getId());
//                        info.setResult(adapter.values[which]);
//                        stuInfoMap.put(stu.getId(), info);
//                    } else if (stuInfo.getResult() != adapter.values[which]) {
//                        stuInfo.setResult(adapter.values[which]);
//                        stuInfoMap.put(stu.getId(), stuInfo);
//                    }
//
//                } else {
//                    if (stuInfo != null) {
//                        LogUtil.d("before" + stuInfoMap);
//                        LogUtil.d(stuInfo.toString());
//                        stuInfoMap.remove(stuInfo.getId());
//                        LogUtil.e(stuInfoMap.toString());
//                        LogUtil.d("after" + stuInfoMap);
//                        dialog.dismiss();
//                    } else
//                        return;
//                }
//                TextView tv_state = view.findViewById(R.id.tv_state);
//                TextView tv_states = view.findViewById(R.id.tv_states);
//                tv_state.setText(adapter.items[which]);
//                LogUtil.d("color" + adapter.getStateColor(adapter.values[which]));
//                tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
//                tv_states.setTextColor(adapter.getStateColor(adapter.values[which]));
//        });
//        builder.create().show();
        View v = View.inflate(getActivity(), R.layout.dialog_item_layout, null);
        RadioGroup rg_state = v.findViewById(R.id.rg_state);
        EditText et_des = v.findViewById(R.id.et_des);
        //还原状态
        if (stuInfo != null)
        {
            result = this.getIndex(adapter.values, stuInfo.getResult());
            RadioButton radiobutton = (RadioButton) (rg_state.getChildAt(result));
            radiobutton.setChecked(true);
            if(result != 2 && result != 5)
                et_des.setVisibility(View.VISIBLE);
            if(stuInfo.getDes() != null)
                et_des.setText(stuInfo.getDes());
        }
        builder.setView(v);

        builder.setPositiveButton("确定",(dialogInterface, i) -> {
            int which = getIndex(rg_state.getCheckedRadioButtonId());
            LogUtil.e(which+"");
            //如果不是已到达则加入stuInfoMap
            if (which != 2) {
                if (stuInfo == null) {
                    CommitCheckDto.StuInfo info = new CommitCheckDto.StuInfo();
                    info.setId(stu.getId());
                    info.setDes(et_des.getText().toString().trim());
                    info.setResult(adapter.values[which]);
                    stuInfoMap.put(stu.getId(), info);
                } else {
                    if(stuInfo.getResult() == adapter.values[5])
                    {
                        waitCount--;
                    }else
                    {
                        stuInfo.setDes(et_des.getText().toString().trim());
                    }
                    stuInfo.setResult(adapter.values[which]);
                    stuInfoMap.put(stu.getId(), stuInfo);
                }
            } else {
                if (stuInfo != null) {
                    if(stuInfo.getResult() == adapter.values[5])
                    {
                        waitCount--;
                    }
                    LogUtil.d("before" + stuInfoMap);
                    LogUtil.d(stuInfo.toString());
                    stuInfoMap.remove(stuInfo.getId());
                    LogUtil.e(stuInfoMap.toString());
                    LogUtil.d("after" + stuInfoMap);
                } else
                    return;
            }
            TextView tv_state = view.findViewById(R.id.tv_state);
            TextView tv_des = view.findViewById(R.id.tv_des);
            if(which!=2&&which!=5)
                tv_des.setText(et_des.getText().toString().trim());
            else
                tv_des.setText("");
            tv_state.setText(adapter.items[which]);
            LogUtil.d("color" + adapter.getStateColor(adapter.values[which]));
            tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
        });

        builder.setNegativeButton("取消",
                (DialogInterface d, int which) -> {
                    d.cancel();
                }
        );
        dialog = builder.create();
        dialog.show();
        AlertDialog finalDialog = dialog;
        rg_state.setOnCheckedChangeListener((radioGroup, id) -> {
            LogUtil.e(stuInfoMap.toString());
            LogUtil.e(waitCount+"");
            int which = getIndex(id);
            if(which != 5 && which != 2)
                et_des.setVisibility(View.VISIBLE);
            else
                et_des.setVisibility(View.GONE);

            //如果是已到达或待处理则直接设置
            if (which == 5) {
                if (stuInfo == null) {
                    CommitCheckDto.StuInfo info = new CommitCheckDto.StuInfo();
                    info.setId(stu.getId());
                    info.setResult(adapter.values[which]);
                    stuInfoMap.put(stu.getId(), info);
                } else if (stuInfo.getResult() != adapter.values[which]) {
                    stuInfo.setResult(adapter.values[which]);
                    stuInfoMap.put(stu.getId(), stuInfo);
                }
                waitCount++;
            } else if(which == 2){
                if (stuInfo != null) {
                    if(stuInfo.getResult() == adapter.values[5])
                    {
                        waitCount--;
                    }
                    LogUtil.d("before" + stuInfoMap);
                    LogUtil.d(stuInfo.toString());
                    stuInfoMap.remove(stuInfo.getId());
                    LogUtil.e(stuInfoMap.toString());
                    LogUtil.d("after" + stuInfoMap);
                } else
                    return;
            }else
            {
                return;
            }
            TextView tv_state = view.findViewById(R.id.tv_state);
            TextView tv_des = view.findViewById(R.id.tv_des);
            if(which!=2&&which!=5)
                tv_des.setText(et_des.getText().toString().trim());
            else
                tv_des.setText("");
            tv_state.setText(adapter.items[which]);
            tv_state.setText(adapter.items[which]);
            LogUtil.d("color" + adapter.getStateColor(adapter.values[which]));
            tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
            finalDialog.cancel();
        });

    }

    /**
     * TODO 可能存在问题
     * 获取单选按钮下标
     * @param rbId 单选按钮id
     * @return
     */
    private int getIndex(int rbId){
        for(int i = 0;i<ids.length;i++)
        {
            if(ids[i] == rbId)
                return i;
        }
        return 2;
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
                                adapter.setCurrent(2);
//                                if(!cb_shownormal.isChecked())
//                                    cb_shownormal.setChecked(true);
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
        qmuiev_load.show(false,error,null,"重新加载",view ->
                iPresenter.getStusToShow(UserManager.getInstance().getUser().getGradeId()));
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
                .setConfirmText("返回考勤主页")
                .setConfirmClickListener(
                        (SweetAlertDialog dialog) ->
                        {
                            dialog.dismissWithAnimation();
                            finish();
                        }
                )
                .show();
        //通知checkActivity更新任务
        EventBus.getDefault().post(new CheckActivity.CheckTask());
    }

    @Override
    public void showLoadStus() {
        qmuiev_load.show(true,"正在加载中...",null,null,null);
    }

    @Override
    public void changeLoadStus() {
        qmuiev_load.show(false,"暂无",null,null,null);
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

}
