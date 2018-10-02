package com.cxyz.untilchecked.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.untilchecked.R;
import com.cxyz.untilchecked.ipresenter.ISplashPresenter;
import com.cxyz.untilchecked.ipresenter.ipresenterimpl.ISplashPresenterImpl;
import com.cxyz.untilchecked.iview.ISplashView;

import java.io.File;

public class SplashActivity extends BaseActivity<ISplashPresenter> implements ISplashView {

    private ProgressBar pb_pro;

    private AlertDialog dialog;

    private long start_time;

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    public void initView() {
        pb_pro = (ProgressBar) findViewById(R.id.pb_pro);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected void afterInit() {
        super.afterInit();
        start_time = System.currentTimeMillis();
        iPresenter.Update();
    }

    @Override
    protected ISplashPresenter createIPresenter() {
        return new ISplashPresenterImpl();
    }

    @Override
    public void showLoadingView() {
        if(pb_pro != null)
            pb_pro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        if(pb_pro != null)
            pb_pro.setVisibility(View.GONE);
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    public void showUpdateView(String version, String des,final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo1);
        builder.setTitle("发现新版本"+version);
        builder.setMessage(des+"是否更新？");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iPresenter.download(url);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishSplash();
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
    public void finishSplash() {
        final long len;
        if((len = System.currentTimeMillis()-start_time)<3000)
        {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(len);
                            finishSplash();
                        }
                    }
            ).start();
            return;
        }
        startActivity(LoginActivity.class,null);
        this.finish();
    }
}
