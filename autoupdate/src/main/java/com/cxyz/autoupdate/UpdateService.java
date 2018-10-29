package com.cxyz.autoupdate;

/**
 * Created by Administrator on 2018/10/10.
 */

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.cxyz.autoupdate.core.UpdateInfo;
import com.cxyz.log.core.LogManager;

import java.io.File;

/**
 * Description:更新下载后台进程
 * User: chenzheng
 * Date: 2016/12/14 0014
 * Time: 16:24
 */
public class UpdateService extends Service{
    private UpdateInfo info;
    private String filePath;
    private NotificationManager notificationManager;
    private Notification notification;
    private Activity activity;



    @Override
    public void onCreate() {
        LogManager.e("UpdateService onCreate()");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        filePath = Environment.getExternalStorageDirectory()+"/AppUpdate/untilchecked.apk";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogManager.d( "UpdateService onStartCommand()");
        if(intent==null){
            LogManager.e("无法获取intent");
            notifyUser(getString(R.string.auto_update_update_download_failed), getString(R.string.auto_update_update_download_failed), 0);
            stopSelf();
        }
        info = (UpdateInfo) intent.getSerializableExtra("info");
        if(info==null)
            LogManager.d("请使用StartUpdate类的start方法开始更新!");
        notifyUser(getString(R.string.auto_update_update_download_start), getString(R.string.auto_update_update_download_start), 0);
        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        //开始下载更新,如果设置了回调则使用设置的回调,否则使用默认回调
        UpdateManager.getInstance().startDownloads(info.url, filePath,info.listener==null?new UpdateDownloadListener() {
            @Override
            public void onStarted() {
                LogManager.d( "onStarted()");

            }

            @Override
            public void onProgressChanged(int progress, String downloadUrl) {
                LogManager.d(progress+"");
                //如果未设置相应属性则使用默认值
                notifyUser(getValue(info.progressing,R.string.auto_update_update_download_progressing),
                        getValue(info.progressing,R.string.auto_update_update_download_progressing), progress);
            }

            @Override
            public void onFinished(float completeSize, String downloadUrl) {
                LogManager.d("onFinished()");
                notifyUser(getValue(info.success,R.string.auto_update_update_download_finish),
                        getValue(info.success,R.string.auto_update_update_download_finish), 100);
                stopSelf();
            }

            @Override
            public void onFailure() {
                LogManager.d("onFailure()");
                notifyUser(getValue(info.fail,R.string.auto_update_update_download_failed),
                        getValue(info.fail,R.string.auto_update_update_download_failed), 0);
                stopSelf();
            }
        }:info.listener);
    }

    /**
     * 更新notification
     * @param result
     * @param msg
     * @param progress
     */
    private void notifyUser(String result, String msg, int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        if(info.title != null)
            builder.setContentTitle(info.title);
        if(info.largeIcon != null)
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),info.largeIcon));
        if(info.smallIcon != null)
            builder.setSmallIcon(info.smallIcon);
        if(progress>0 && progress<=100){

            builder.setProgress(100,progress,false);

        }else{
            builder.setProgress(0, 0, false);
        }
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker(result);
        builder.setContentIntent(progress>=100 ? getContentIntent() :
                PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
        notification = builder.build();
        notificationManager.notify(0, notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 进入apk安装程序
     * @return
     */
    private PendingIntent getContentIntent() {
        LogManager.d( "getContentIntent()");
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://"+apkFile.getAbsolutePath()),
                "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        startActivity(intent);
        return pendingIntent;

    }

    //获取设置值,如果未设置则返回默认
    private String getValue(String set,int defaultId)
    {
        if(set != null)
            return set;
        else
            return getString(defaultId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
