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
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.R;
import com.cxyz.logiccommons.typevalue.NotifyType;
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

    private HashMap<Integer,Integer> notifyIds;//缓存notifyid

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收 Registration Id : " + regId);
        }else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            notifyInfo(context,bundle.getString(JPushInterface.EXTRA_EXTRA));
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
        HashMap<String,Object> map;
        try {
            map = (HashMap<String, Object>)
                    GsonUtil.fromJson(extras,new TypeToken<HashMap<String,Object>>(){}.getType());
            for(String s:map.keySet())
                LogUtil.e(s+":"+map.get(s));
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setLargeIcon(BitmapFactory.decodeResource
                (context.getResources(), R.mipmap.common_logo))
                .setSmallIcon(R.mipmap.common_logo)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
        Integer type = (Integer) map.get("type");
        Integer notifyiId = type;
        //如果是自定义的推送则直接获取


        manager.notify(notifyiId,builder.build());

    }

    private int setBuilder(NotificationCompat.Builder builder,HashMap<String,Object> map)
    {
        int type = (Integer) map.get("type");
        String title;
        String content;
        String ticker;
        switch (type)
        {
            case NotifyType.CUSTOM:{
                title = (String) map.get("title");
                content = (String) map.get("content");
                ticker = (String) map.get("ticker");
            }break;
            case NotifyType.BAD_CHECK_RECORD:{
                Integer count = notifyIds.get(type);
                if(count == null)
                    count = 0;
                title = "考勤异常";
                content = "您有"+count+"项考勤异常";
                ticker = "最新考勤信息";
            }break;
            case NotifyType.VACATION:{
                title = "请假信息新动态";
                content = "您的请假有新进展了";
                ticker = "最新请假信息";
            }break;
            case NotifyType.VACATION_AUDIT:{
                title = "请假待审核";
                content = "您有新的请假待审核";
                ticker = "最新请假信息";
            }break;
            default:return NotifyType.ERROR;
        }

        builder.setContentTitle(title)
                .setContentText(content)
                .setTicker(ticker);

        if(map.get(type)!=null)
            builder.setDefaults(0);

        return type;
    }


}