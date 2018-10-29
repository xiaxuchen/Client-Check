package com.cxyz.commons.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 夏旭晨 on 2018/9/20.
 * 首次使用需要先调用init(Context)方法进行初始化
 * 一般会在Application进行初始化操作
 */

public class ToastUtil {

    private static Context context;

    private ToastUtil(){};
    /***
     * 对ToastUtils进行一个初始化，让其获取一个上下文
     * @param context 传入的上下文
     */
    public static void init(Context context)
    {
        ToastUtil.context = context;
    }

    /***
     * 显示一个短时间的吐司
     * @param text 吐司的文本
     */
    public static void showShort(String text)
    {
        if(context!=null)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    /***
     * 显示一个长时间的吐司
     * @param text 吐司的文本
     */
    public static void showLong(String text)
    {
        if(context!=null)
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
