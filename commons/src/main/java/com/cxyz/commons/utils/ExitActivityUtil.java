package com.cxyz.commons.utils;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by 夏旭晨 on 2018/9/21.
 */

public class ExitActivityUtil {

    private static long exitTime = 0;

    /*
     * 重写onKeyDown方法
     * 在onkeydown方法中回调此方法即可实现
     * @return 返回是否处理退出程序
     */
    public static boolean exit(Activity activity,int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //2s之内按返回键就会推出
            if((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(activity, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                activity.finish();
                System.exit(0);
            }
            return true;
        }
        return false;
    }

}