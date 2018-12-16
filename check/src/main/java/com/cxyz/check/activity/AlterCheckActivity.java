package com.cxyz.check.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cxyz.check.R;
import com.cxyz.check.adapter.StusAdapter;
import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.ipresenter.IAlterPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IAlterPresenterImpl;
import com.cxyz.check.other.MineMap;
import com.cxyz.check.view.IAlterView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.StringUtil;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2018/12/8.
 * 为了方便，从DailyCheckActivity copy了很多代码，但是因此也有很多资源的浪费
 */

public class AlterCheckActivity extends BaseActivity<IAlterPresenter> implements IAlterView {

    //搜索栏
    private EditText et_find;

    private QMUIBottomSheet sl_current;

    //当前显示
    private TextView tv_current;

    //学生列表
    private ListView lv_stus;

    private StusAdapter adapter;

    private Button btn_commit;

    private MineMap<String,CommitCheckDto.StuInfo> origin;

    private int compId;

    //radiobutton的id
    private int ids[] = {R.id.rb_late,R.id.rb_vacate,R.id.rb_normal,
            R.id.rb_absenteeism,R.id.rb_earlyleave,R.id.rb_wait_dispose};

    private int altedCount = 0;

    /**
     * 设置更改的数量
     * @param add 添加的数量,可以为负
     */
    public void addAltedCount(int add) {
        LogUtil.e(add+"");
        if(add == 0)
            return;
        this.altedCount += add;
        if(altedCount != 0 && !btn_commit.isEnabled())
            btn_commit.setEnabled(true);
        else if (btn_commit.isEnabled())
            btn_commit.setEnabled(false);
    }

    /**
     * 记录考勤不同状态
     */
    private MineMap<String,CommitCheckDto.StuInfo> stuInfoMap;

    //listview的空白填充view
    private QMUIEmptyView qmuiev_load;

    @Override
    public int getContentViewId() {
        return R.layout.activity_alter_layout;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        compId = intent.getIntExtra("compId",0);
    }

    @Override
    public void initView() {
        lv_stus =  findViewById(R.id.lv_stus);
        tv_current = findViewById(R.id.tv_current);
        et_find = findViewById(R.id.et_find);
        qmuiev_load =  findViewById(R.id.qmuiev_load);
        lv_stus.setEmptyView(qmuiev_load);
        btn_commit = findViewById(R.id.btn_commit);
    }

    @Override
    public void initData() {
        stuInfoMap = new MineMap<>();
        origin = new MineMap<>();
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
            builder.setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                adapter.setCurrent(position - 1);
                tv_current.setText(tag);
                dialog.dismiss();
            });
            sl_current = builder.build();
            sl_current.show();
        });

        lv_stus.setOnItemClickListener((adapterView, view, i, l) -> showAlterDialog(view,i));

        btn_commit.setOnClickListener(view -> {
            List<AlterRecordDto> alted = new ArrayList<>();
            AlterRecordDto dto;
            for(String key:stuInfoMap.keySet())
            {
                CommitCheckDto.StuInfo info = stuInfoMap.get(key);
                if(equal(info,key))
                    continue;

                dto = new AlterRecordDto();
                dto.setStu(new GradeStusDto(info.getId()));
                dto.setDes(info.getDes());
                dto.setResult(info.getResult());
                alted.add(dto);
            }
            for(String key:origin.keySet())
            {
                if(stuInfoMap.get(key) != null)
                    continue;

                dto = new AlterRecordDto();
                dto.setStu(new GradeStusDto(key));
                dto.setResult(CheckRecordResult.NORMAL);
                alted.add(dto);
            }
            iPresenter.commitAlted(compId,alted);
        });
    }

    /**
     * 显示修改的对话框
     * @param view
     * @param position
     */
    private void showAlterDialog(final View view,final int position)
    {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("选择考勤状态:");

        int result = 2;
        final GradeStusDto stu = adapter.getItem(position);//获取当前item的数据

        final boolean oldAltered = equal(stuInfoMap.get(stu.getId()),stu.getId());

        final CommitCheckDto.StuInfo stuInfo = stuInfoMap.get(stu.getId());//若非已到达，获取异常记录中的数据

        //创建dialog
        View v = View.inflate(getActivity(), R.layout.dialog_item_layout, null);
        RadioGroup rg_state = v.findViewById(R.id.rg_state);
        RadioButton rb_wait_dispose = v.findViewById(R.id.rb_wait_dispose);
        rb_wait_dispose.setVisibility(View.GONE);
        EditText et_des = v.findViewById(R.id.et_des);
        //还原状态
        if (stuInfo != null)
        {
            result = this.getIndex(adapter.values, stuInfo.getResult());
            RadioButton radiobutton = (RadioButton) (rg_state.getChildAt(result));
            radiobutton.setChecked(true);

            if(result != 2 )
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
            } else{
                stuInfo.setResult(adapter.values[which]);
                stuInfo.setDes(et_des.getText().toString().trim());
                stuInfoMap.put(stu.getId(), stuInfo);
            }
        } else {
            if (stuInfo != null) {
                LogUtil.d("before" + stuInfoMap);
                LogUtil.d(stuInfo.toString());
                stuInfoMap.remove(stuInfo.getId());
                LogUtil.e(stuInfoMap.toString());
                LogUtil.d("after" + stuInfoMap);
            } else
                return;
        }
        boolean newAltered = equal(stuInfoMap.get(stu.getId()),stu.getId());
        TextView tv_state = view.findViewById(R.id.tv_state);
        TextView tv_des = view.findViewById(R.id.tv_des);
        if(which!=2)
            tv_des.setText(et_des.getText().toString().trim());
        else
            tv_des.setText("");
        tv_state.setText(adapter.items[which]);
        tv_state.setTextColor(adapter.getStateColor(adapter.values[which]));
        if(newAltered == oldAltered)//保证前后不一致
            return;
        if(newAltered)
            addAltedCount(-1);
        else
            addAltedCount(1);
        });

        builder.setNegativeButton("取消",
                (DialogInterface d, int which) -> {
                    d.cancel();
                }
        );
        dialog = builder.create();
        dialog.show();

        rg_state.setOnCheckedChangeListener((radioGroup, id) -> {
            int which = getIndex(id);
            if(which != 2)
                et_des.setVisibility(View.VISIBLE);
            else
                et_des.setVisibility(View.GONE);
        });

    }

    /**
     * 对比info是否相同
     * @param info
     * @return
     */
    private boolean equal(CommitCheckDto.StuInfo info,String id)
    {
        CommitCheckDto.StuInfo info1 = origin.get(id);

        if(info1 == null)
        {
            if(info == null)
                return true;
            else
                return false;
        }

        if(info == null)
            return false;

        LogUtil.e(info1);

        LogUtil.e(info);


        if(StringUtil.nullStrToEmpty(info1.getDes()).equals
                (StringUtil.nullStrToEmpty(info.getDes()))
                && info1.getResult() == info.getResult())
            return true;
        else return false;
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

    @Override
    protected boolean isStateBar() {
        return false;
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
        iPresenter.getAlterRecords(compId);
    }

    @Override
    protected IAlterPresenter createIPresenter() {
        return new IAlterPresenterImpl();
    }

    @Override
    public void showAlterRecords(List<AlterRecordDto> dtos) {
        List<GradeStusDto> stusDtos = new ArrayList<>();
        for(AlterRecordDto dto:dtos)
        {
            GradeStusDto s = dto.getStu();
            stusDtos.add(s);
            if(dto.getResult() != CheckRecordResult.NORMAL)
            {
                CommitCheckDto.StuInfo info = new CommitCheckDto.StuInfo();
                info.setDes(dto.getDes());
                info.setId(dto.getStu().getId());
                info.setResult(dto.getResult());
                stuInfoMap.put(dto.getStu().getId(),info);
            }
        }
        for(String key:stuInfoMap.keySet())
        {
            try {
                origin.put(key,stuInfoMap.get(key).clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        adapter = new StusAdapter(getActivity(),stusDtos,stuInfoMap,R.layout.item_list_alter_layout);
        lv_stus.setAdapter(adapter);
    }

    @Override
    public void showGetAltersError(String error) {
        qmuiev_load.show(false,error,null,"重新加载",view ->
        {});
    }

    @Override
    public void showLoadAlters() {
        qmuiev_load.show(true,"正在加载中...",null,null,null);
    }

    @Override
    public void changeLoadAlters() {
        qmuiev_load.show(false,"暂无",null,null,null);
    }

    @Override
    public void showError(String error) {
        qmuiev_load.show(false,error,null,"重新加载",view ->
        {});
    }

    @Override
    public void showAlterSuccess(String success) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.SUCCESS_TYPE)
                .showCancelButton(false)
                .setTitleText("提交成功")
                .setContentText(success)
                .setConfirmText("返回历史考勤")
                .setConfirmClickListener(
                        (SweetAlertDialog dialog) ->
                        {
                            dialog.dismissWithAnimation();
                            finish();
                        }
                )
                .show();
        EventBus.getDefault().post(new UpdateHistory());
    }

    public static class UpdateHistory{
    }

    @Override
    public void showAlterFail(String error) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.WARNING_TYPE)
                .showCancelButton(false)
                .setTitleText("提交失败")
                .setContentText(error)
                .setConfirmText("返回历史考勤")
                .setConfirmClickListener(
                        (SweetAlertDialog dialog) ->
                        {
                            dialog.dismissWithAnimation();
                            finish();
                        }
                )
                .show();
    }
}
