package com.cxyz.mine.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.service.UpdateService;
import com.cxyz.mine.IPresenter.IMineFragmentPresenter;
import com.cxyz.mine.IPresenter.impl.IMineFragmentPresenterlmpl;
import com.cxyz.mine.R;
import com.cxyz.mine.activity.ChangePwdACtivity;
import com.cxyz.mine.activity.MoreSettingActivity;
import com.cxyz.mine.activity.MyinfoActivity;
import com.cxyz.mine.activity.UserResponse;
import com.cxyz.mine.iview.IMineFragementView;

import java.io.File;

/**
 * Created by Administrator on 2018/9/25.
 */
@Route(path = "/mine/MineFragment")
public class MineFragment extends BaseFragment<IMineFragmentPresenter> implements View.OnClickListener ,IMineFragementView {
    private Dialog dialog;
    private ProgressBar pb_pro;
    private TextView tv_mine_myinfo;
    private TextView tv_mine_update;
    private TextView tv_mine_useradvice;
    private TextView tv_mine_setting;
    private TextView tv_mine_vac;
    private  TextView   tv_mine_alterpwd;
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_mine_myinfo = findViewById(R.id.tv_mine_myinfo);
        tv_mine_vac = findViewById(R.id.tv_mine_vac);
        tv_mine_update = findViewById(R.id.tv_mine_update);
        tv_mine_useradvice = findViewById(R.id.tv_mine_useradvice);
        tv_mine_setting = findViewById(R.id.tv_mine_setting);
        tv_mine_alterpwd = findViewById(R.id.tv_mine_alterpwd);
    }

    @Override
    protected IMineFragmentPresenter createIPresenter() {
        return  new IMineFragmentPresenterlmpl();
    }

    @Override
    protected void setListener() {
        tv_mine_myinfo.setOnClickListener(this);
        tv_mine_vac.setOnClickListener(this);
        tv_mine_update.setOnClickListener(this);
        tv_mine_useradvice.setOnClickListener(this);
        tv_mine_setting.setOnClickListener(this);
        tv_mine_alterpwd.setOnClickListener(this);
    }

    //监听事件

    //界面跳转
    //从我的界面跳转到设置界面
    public void tv_mine_setting() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MoreSettingActivity.class);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.enter_next, R.anim.enter_exit);

    }

    //从我的界面跳转到个人信息界面
    public void tv_mine_myinfo() {
        Intent intent = new Intent(getActivity().getApplicationContext(), MyinfoActivity.class);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.enter_next, R.anim.enter_exit);
    }
    //从我的界面跳转到修改密码界面
    public void tv_mine_alterpwd() {
        Intent intent = new Intent(getActivity().getApplicationContext(), ChangePwdACtivity.class);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.enter_next, R.anim.enter_exit);
    }
    //从我的界面跳转到用户反馈界面
    public void tv_mine_useradvice() {
        Intent intent = new Intent(getActivity().getApplicationContext(), UserResponse.class);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.enter_next, R.anim.enter_exit);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.tv_mine_myinfo)
            tv_mine_myinfo();
        else if(viewId == R.id.tv_mine_vac)
            ARouter.getInstance().build("/vac/MineVacateActivity").navigation();
        else if(viewId == R.id.tv_mine_setting)
            tv_mine_setting();
        else if(viewId == R.id.tv_mine_alterpwd)
            tv_mine_alterpwd();
        else if(viewId == R.id.tv_mine_useradvice)
            tv_mine_useradvice();
        else if(viewId == R.id.tv_mine_update){
            tv_mine_update();
        }
    }
    public void tv_mine_update(){
     iPresenter.Update();

    }

    @Override
    public void showUpdateView(int versionCode, String des, final String url) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setIcon(R.mipmap.common_logo);
            builder.setTitle("发现新版本"+versionCode);
            builder.setMessage(des);
            builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getActivity(),UpdateService.class);
                    intent.putExtra("apkUrl",url);
                    getActivity().startService(intent);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showDownload(int progress, int max) {
        if(pb_pro != null)
            pb_pro.setVisibility(View.VISIBLE);
    }


    @Override
    public void installApp(File app) {
        AppUtil.installApk(getActivity(),app.getAbsolutePath());
    }

    @Override
    public void noUpdate() {
        ToastUtil.showShort("已是最新版本");
    }


}