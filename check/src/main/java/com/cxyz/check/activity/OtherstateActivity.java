package com.cxyz.check.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.check.R;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.ipresenter.IOtherstatePresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IOtherstatePresenterImpl;
import com.cxyz.check.view.IOtherstateView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.logiccommons.typevalue.TaskCompletionState;

import org.greenrobot.eventbus.EventBus;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2018/12/15.
 */

public class OtherstateActivity extends BaseActivity<IOtherstatePresenter> implements IOtherstateView{

    private Integer compId;

    //描述信息
    private EditText et_des;

    //提交
    private Button btn_commit;

    private TitleView tv_title;

    @Override
    public int getContentViewId() {
        return R.layout.activity_otherstate_layout;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        compId = intent.getIntExtra("compId",-1);
    }

    @Override
    public void initView() {
        et_des = findViewById(R.id.et_des);
        btn_commit = findViewById(R.id.btn_commit);
        tv_title = findViewById(R.id.tv_title);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        btn_commit.setOnClickListener(view -> {
            if(et_des.getText().toString().isEmpty())
            {
                ToastUtil.showShort("描述信息不能为空!");
                return;
            }
            CommitCheckDto dto = new CommitCheckDto();
            dto.setTaskId(compId);
            dto.setState(TaskCompletionState.OTHERSTATE);
            dto.setDes(et_des.getText().toString().trim());
            iPresenter.commitOtherstate(dto);
        });

        tv_title.setBackClickListener(v -> onBackPressed());

    }

    @Override
    protected IOtherstatePresenter createIPresenter() {
        return new IOtherstatePresenterImpl();
    }

    @Override
    public void showCommitSuccess(String info) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.SUCCESS_TYPE)
                .showCancelButton(false)
                .setTitleText(info)
                .setContentText("考勤描述提交完毕!")
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
    public void showError(String error) {
        new SweetAlertDialog(getActivity(),
                SweetAlertDialog.WARNING_TYPE)
                .showCancelButton(false)
                .setTitleText(error)
                .setContentText("考勤描述提交失败!")
                .setConfirmText("返回考勤主页")
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
