package com.cxyz.vac.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.date.DateTime;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.typevalue.VacType;
import com.cxyz.vac.R;
import com.cxyz.vac.icon.IconfontModule;
import com.cxyz.vac.ipresenter.IVacatePresenter;
import com.cxyz.vac.ipresenter.impl.IVacatePresenterImpl;
import com.cxyz.vac.iview.IVacateView;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.widget.IconTextView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.util.Calendar;

import cn.addapp.pickers.picker.DateTimePicker;
import cn.pedant.SweetAlert.SweetAlertDialog;

@Route(path = "/vac/VacateActivity")
public class VacateActivity extends BaseActivity<IVacatePresenter> implements IVacateView {

    private TitleView tv_title;

    private TextView tv_vac_type;//考勤类型

    private TextView tv_vac_start;//开始时间

    private TextView tv_vac_end;//结束时间

    private EditText et_des;//请假事由

    private EditText et_time_len;//请假时长

    private Button btn_commit;//提交按钮

    private IconTextView tv_type_icon;//类型图标

    private IconTextView tv_start_icon;//开始图标

    private IconTextView tv_end_icon;//结束图标

    private DateTime start;//开始时间

    private DateTime end;//结束时间

    private int year;//开始的年


    @Override
    public int getContentViewId() {
        Iconify.with(new IconfontModule());
        return R.layout.activity_vacate_layout;
    }

    @Override
    public void initView() {
        QMUIStatusBarHelper.translucent(getActivity(),getResources().getColor(R.color.common_primary_color));
        tv_vac_type = findViewById(R.id.tv_vac_type);
        tv_vac_start = findViewById(R.id.tv_vac_start);
        tv_vac_end = findViewById(R.id.tv_vac_end);
        tv_type_icon = findViewById(R.id.tv_type_icon);
        tv_start_icon = findViewById(R.id.tv_start_icon);
        tv_end_icon = findViewById(R.id.tv_end_icon);
        et_des = findViewById(R.id.et_des);
        et_time_len = findViewById(R.id.et_time_len);
        btn_commit = findViewById(R.id.btn_commit);
        tv_title = findViewById(R.id.tv_title);

        toggleEnable();
    }

    /**
     * 切换时间的颜色
     */
    private void toggleEnable()
    {
        RelativeLayout rl_start = getParent(tv_vac_start);
        RelativeLayout rl_end = getParent(tv_vac_end);
        RelativeLayout rl_len = getParent(et_time_len);

        rl_start.setEnabled(!rl_start.isEnabled());
        rl_end.setEnabled(!rl_end.isEnabled());
        rl_len.setEnabled(!rl_len.isEnabled());

        for(int i = 0;i<rl_start.getChildCount();i++)
        {
            View v =  rl_start.getChildAt(i);
            v.setEnabled(!v.isEnabled());
        }

        for(int i = 0;i<rl_end.getChildCount();i++)
        {
            View v = rl_end.getChildAt(i);
            v.setEnabled(!v.isEnabled());
        }

        for(int i = 0;i<rl_len.getChildCount();i++)
        {
            View v = rl_len.getChildAt(i);
            v.setEnabled(!v.isEnabled());
        }

    }

    @Override
    public void initData() {
    }

    @Override
    public void setEvent() {
        tv_title.setBackClickListener(v -> onBackPressed());
        getParent(tv_vac_type).setOnClickListener(view -> {
            createTypeDialog();
        });

        getParent(tv_vac_start).setOnClickListener(view -> {
            showDatePicker(tv_vac_start,tv_start_icon);
        });

        getParent(tv_vac_end).setOnClickListener(view -> {
            showDatePicker(tv_vac_end,tv_end_icon);
        });

        tv_type_icon.setOnClickListener(view -> {
            if(tv_vac_type.getText().toString().equals("请选择"))
            {
                createTypeDialog();
            }
            else
            {
                tv_vac_type.setText("请选择");
                tv_vac_start.setText("请选择");
                tv_vac_end.setText("请选择");
                tv_type_icon.setText("{next}");
                tv_start_icon.setText("{next}");
                tv_end_icon.setText("{next}");
                et_time_len.setText("");
                start = null;
                end = null;
                toggleEnable();
            }
        });

        tv_start_icon.setOnClickListener(view -> onIconClick(tv_vac_start, (IconTextView) view));
        tv_end_icon.setOnClickListener(view -> onIconClick(tv_vac_end, (IconTextView) view));

        btn_commit.setOnClickListener(view -> {
            if(tv_vac_type.getText().toString().equals("请选择"))
            {
                ToastUtil.showShort("请选择请假类型");
                return;
            }
            if(start == null || end == null)
            {
                ToastUtil.showShort("请填写请假时间");
                return;
            }

            if(et_time_len.getText().toString().trim().isEmpty())
            {
                ToastUtil.showShort("请填写请假时长");
                return;
            }

            if(et_des.getText().toString().trim().isEmpty())
            {
                ToastUtil.showShort("请填写请假事由");
                return;
            }

            Integer type = tv_vac_type.getText().toString().equals("事假")? VacType.VAC_THING:VacType.VAC_ILLNESS;
            String s = new StringBuilder(start.getDate()).append(" ").append(start.getTime()).append(":").append(start.getSecond()).toString();
            String e = new StringBuilder(end.getDate()).append(" ").append(end.getTime()).append(":").append(end.getSecond()).toString();
            iPresenter.vacate(s,e,Integer.parseInt(et_time_len.getText().toString()),
                    type,et_des.getText().toString());
        });
    }

    /**
     * 当start或end的图标点击时触发
     */
    private void onIconClick(TextView v,IconTextView icon)
    {
        if(v.getText().equals("{next}"))
            showDatePicker(v,icon);
        else
        {
            v.setText("请选择");
            icon.setText("{next}");
            if(v == tv_vac_start)
                start = null;
            else
                end = null;
        }

    }

    /**
     * 显示时间选择器
     */
    private void showDatePicker(TextView tv,IconTextView icon)
    {

        DateTimePicker picker = new DateTimePicker(getActivity(),DateTimePicker.HOUR_24);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        picker.setDateRangeStart(year,1,1);
        c.add(Calendar.DAY_OF_MONTH,10);
        picker.setDateRangeEnd(c.get(Calendar.YEAR),12,31);
        picker.setOnDateTimePickListener((DateTimePicker.OnYearMonthDayTimePickListener) (s, s1, s2, s3, s4) -> {
            DateTime oldTime;
            icon.setText("{clear}");
            DateTime dateTime = new DateTime(s,s1,s2,s3,s4,"0");
            if(tv.equals(tv_vac_start))
            {
                oldTime = start;
                start = dateTime;
            }
            else
            {
                oldTime = end;
                end = dateTime;
            }
            if(start == null || end == null)
            {
                StringBuilder builder = new StringBuilder();
                builder.append(s).append("-").append(s1).
                        append("-").append(s2).append(" ").append(s3).append(":").append(s4);
                tv.setText(builder.toString());
                return;
            }
            if(start.compareTo(end) != -1)
            {
                if(tv.equals(tv_vac_start))
                {
                    start = oldTime;
                    if(oldTime == null)
                        tv_start_icon.setText("{next}");
                }
                else
                {
                    end = oldTime;
                    if(oldTime == null)
                        tv_start_icon.setText("{next}");
                }
                ToastUtil.showShort("开始时间必须早于结束时间");
                return;
            }

            StringBuilder builder = new StringBuilder();
            builder.append(s).append("-").append(s1).
                    append("-").append(s2).append(" ").append(s3).append(":").append(s4);
            tv.setText(builder.toString());
        });
        picker.show();
    }


    private void createTypeDialog()
    {
        String[] items = {"事假","病假"};
        QMUIDialog.MenuDialogBuilder builder = new QMUIDialog.MenuDialogBuilder(getActivity());
        builder.addItems(items,(dialogInterface, i) ->{
            if(tv_vac_type.getText().toString().equals("请选择"))
                toggleEnable();
            tv_vac_type.setText(items[i]);
            dialogInterface.cancel();
            tv_type_icon.setText("{clear}");
        });
        builder.show();
    }

    @Override
    protected IVacatePresenter createIPresenter() {
        return new IVacatePresenterImpl();
    }

    @Override
    public void showSuccess(String info) {
        SweetAlertDialog dialog1 = new SweetAlertDialog(getActivity(),
                SweetAlertDialog.SUCCESS_TYPE)
                .showCancelButton(false)
                .setTitleText("提交成功!")
                .setContentText(info)
                .setConfirmText("返回主页")
                .setConfirmClickListener(
                        (SweetAlertDialog dialog) ->
                        {
                            dialog.dismissWithAnimation();
                            finish();
                        }
                );
                dialog1.setCancelable(false);
                dialog1.show();

    }

    @Override
    public void showFail(String error) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.WARNING_TYPE)
                .showCancelButton(false)
                .setTitleText("提交失败!")
                .setContentText(error)
                .setConfirmText("返回主页")
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
