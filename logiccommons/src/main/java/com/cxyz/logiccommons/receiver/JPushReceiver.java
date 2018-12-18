package com.cxyz.logiccommons.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2018/12/12.
 */

public class JPushReceiver extends BroadcastReceiver
{
    private final String TAG ="jpush";


    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        ToastUtil.showShort("caosandiasd");
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收 Registration Id : " + regId);
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
//            Intent i = new Intent(context, .class);  //自定义打开的界面
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void notifyInfo(Context context,String extras)
    {
        HashMap<String,String> map;
        try {
            map = (HashMap<String, String>)
                    GsonUtil.fromJson(extras,new TypeToken<HashMap<String,String>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(BitmapFactory.decodeResource
                (context.getResources(), 0))
        .setContentTitle(map.get("title"))
        .setContentText(map.get("content"))
        .setWhen(System.currentTimeMillis())
        .setTicker(map.get("ticker"))
        .setDefaults(NotificationCompat.DEFAULT_SOUND);

        manager.notify();


    }
}