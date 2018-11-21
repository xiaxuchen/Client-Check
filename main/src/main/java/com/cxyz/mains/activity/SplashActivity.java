package com.cxyz.mains.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.service.UpdateService;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash_layout;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("kkkk");
        if(requestCode == UpdateService.REQUEST_INSTALL && resultCode == RESULT_CANCELED)
        {
            noUpdate();
        }
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        start_time = System.currentTimeMillis();
        //初始化完成后根据sp中的update值选择更新或自动登录
        if(getSpUtil().getBoolean("update",true))
            iPresenter.Update();
        else
            iPresenter.autoLogin();
    }

    @Override
    protected ISplashPresenter createIPresenter() {
        return new ISplashPresenterImpl();
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    public void showUpdateView(int versionCode, String des,final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.common_logo);
        builder.setTitle("发现新版本"+versionCode);
        builder.setMessage(des);
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(),UpdateService.class);
                intent.putExtra("apkUrl",url);
               /* ((MyApp)getMyApp()).setAttribute("splashActivity",getActivity());*/
                startService(intent);
                dialog.dismiss();
                noUpdate();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                noUpdate();
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
    public void noUpdate() {
        iPresenter.autoLogin();
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
                            runOnUiThread(runnable);
                        }
                    }
            ).start();
        }else
        {
            runnable.run();
        }
    }

}
