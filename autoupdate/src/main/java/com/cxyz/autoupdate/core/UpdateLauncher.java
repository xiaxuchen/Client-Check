package com.cxyz.autoupdate.core;

import android.app.Activity;
import android.content.Intent;

import com.cxyz.autoupdate.UpdateService;

/**
 * Created by xiaxuchen on 18-10-27.
 * 开始更新
 *
 */

public class UpdateLauncher {

    /**
     * 开始下载更新
     * @param activity
     * @param startInfo 更新所需的信息
     */
    public static void start(Activity activity,UpdateInfo startInfo)
    {
        Intent intent = new Intent(activity,UpdateService.class);
        intent.putExtra("info",startInfo);
        activity.startService(intent);
    }
}
