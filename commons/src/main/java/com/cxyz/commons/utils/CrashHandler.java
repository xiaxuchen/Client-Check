package com.cxyz.commons.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 夏旭晨 on 2018/9/28.
 * 捕获全局异常
 */
public class CrashHandler implements UncaughtExceptionHandler {

    //文件夹目录
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/crash_log/";
    //文件名
    private static final String FILE_NAME = "crash";
    //文件名后缀
    private static final String FILE_NAME_SUFFIX = ".trace";

    private String upload_url;
    //上下文
    private Context mContext;

    private File file;

    private UncaughtExceptionHandler defaultCrashHandler;

    //单例模式
    private static CrashHandler sInstance = new CrashHandler();

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }


    /**
     * 初始化方法
     *
     * @param context
     */
    public CrashHandler init(Context context, String upload_url) {
        defaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取Context，方便内部使用
        mContext = context.getApplicationContext();
        this.upload_url = upload_url;
        return this;
    }

    /**
     * 捕获异常回掉
     *
     * @param thread 当前线程
     * @param ex     异常信息
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        try {
            LogUtil.e(ex.getMessage() + thread.getName());
            //导出异常信息到SD卡
            dumpExceptionToSDCard(ex);
            uploadExceptionToServer(thread,ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ex.printStackTrace();
        file.delete();
        if (defaultCrashHandler != null) {
            defaultCrashHandler.uncaughtException(thread, ex);
        } else {
            Process.killProcess(Process.myPid());
        }

    }

    /**
     * 导出异常信息到SD卡
     *
     * @param ex
     */
    private void dumpExceptionToSDCard(Throwable ex) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        //创建文件夹
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //获取当前时间
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        //以当前时间创建log文件
        file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);
        PrintWriter pw = null;
        try {
            //输出流操作
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //导出手机信息和异常信息
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            pw.println("发生异常时间：" + time);
            pw.println("应用版本：" + pi.versionName);
            pw.println("应用版本号：" + pi.versionCode);
            pw.println("android版本号：" + Build.VERSION.RELEASE);
            pw.println("android版本号API：" + Build.VERSION.SDK_INT);
            pw.println("手机制造商:" + Build.MANUFACTURER);
            pw.println("手机型号：" + Build.MODEL);
            ex.printStackTrace(pw);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null)
                //关闭输出流
                pw.close();
        }
        LogUtil.e(file.getAbsolutePath());
    }

    /**
     * 上传异常信息到服务器
     *
     * @param thread
     * @param ex
     */
    private void uploadExceptionToServer(final Thread thread, final Throwable ex) {
        if (upload_url == null || upload_url.isEmpty())
            return;
        if(file == null)
            return;
        RequestParams requestParams = new RequestParams();
        requestParams.put("file", file);
        try {
            CommonOkHttpClient.uploadFile
                    (upload_url, requestParams,
                            new DisposeDataHandler(new DisposeDataListener() {
                                @Override
                                public void onSuccess(Object responseObj) {
                                    LogUtil.e("caomnioma");
                                }

                                @Override
                                public void onFailure(Object error) {
                                    LogUtil.e("caomniomadebi");
                                }
                            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
        }
        SystemClock.sleep(3000);

    }

}

