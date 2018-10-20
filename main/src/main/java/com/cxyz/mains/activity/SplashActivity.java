package com.cxyz.mains.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mains.R;
import com.cxyz.mains.ipresenter.ISplashPresenter;
import com.cxyz.mains.ipresenter.ipresenterimpl.ISplashPresenterImpl;
import com.cxyz.mains.iview.ISplashView;

import java.io.File;

public class SplashActivity extends BaseActivity<ISplashPresenter> implements ISplashView {

    /**
     * 至少让用户看三秒钟
     */
    private static final long TARGETTIME = 3000;

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
        //iPresenter.Update();
        iPresenter.autoLogin();
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
        builder.setIcon(R.mipmap.common_logo);
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
                exitSplash();
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
    public void exitSplash() {
        lengthen(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class,null);
                finish();
            }
        });

    }

    @Override
    public void autoLoginSuccess() {
        lengthen(new Runnable() {
            @Override
            public void run() {
                LogUtil.e(System.currentTimeMillis()+"");
                ToastUtil.showShort("自动登录成功");
                startActivity(HomeActivity.class);
                finish();
            }
        });
    }

    @Override
    public void autoLoginFail(final String info) {
        lengthen(new Runnable() {
            @Override
            public void run() {
                if(!info.isEmpty())
                    ToastUtil.showShort(info);
                startActivity(LoginActivity.class);
                finish();
            }
        });
    }

    /**
     * 延时操作，让用户至少看三秒我们的闪屏页面
     */
    private void lengthen(final Runnable runnable)
    {
        final long len;
        if((len = System.currentTimeMillis()-start_time)<TARGETTIME)
        {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(TARGETTIME-len);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    runnable.run();
                                }
                            });
                        }
                    }
            ).start();
        }
    }
}
