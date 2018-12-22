package com.cxyz.commons.utils;


import android.util.Log;

/**
 * Created by 夏旭晨 on 2018/9/21.
 * Log日志工具类
 */
public class LogUtil {

    private static final String TAG = "##LogUtil##";
    //Log开关
    private static final boolean DEBUG = true;

    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void i_withoutPre(Object message){
        if (DEBUG)
            Log.i(TAG,message.toString());
    }
    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void d_withoutPre(Object message){
        if (DEBUG)
            Log.d(TAG,message.toString());
    }
    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void v_withoutPre(Object message)
    {
        if (DEBUG)
            Log.w(TAG,message.toString());
    }
    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void e_withoutPre(Object message)
    {
        if (DEBUG)
            Log.e(TAG,message.toString());
    }
    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void wtf_withoutPre(Object message){
        if (DEBUG)
            Log.wtf(TAG,message.toString());
    }
    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void w_withoutPre(Object message){
        if (DEBUG)
            Log.w(TAG,message.toString());
    }

    public static void d(Object message) {
        if (DEBUG)Log.d(TAG, createMessage(message));
    }

    public static void e(Object message) {
        if (DEBUG) Log.e(TAG, createMessage(message));
    }

    public static void i(Object message) {
        if (DEBUG) Log.i(TAG, createMessage(message) );
    }

    public static void v(Object message) {
        if (DEBUG) Log.v(TAG, createMessage(message));
    }

    public static void w(Object message) {
        if (DEBUG)Log.w(TAG, createMessage(message));
    }

    public static void wtf(Object message) {
        if (DEBUG) Log.wtf(TAG, createMessage(message));
    }

    public static void println(Object message) {
        if (DEBUG) Log.println(Log.INFO, TAG, message.toString());
    }

    /**
     * 获取有类名与方法名的logString
     * @param rawMessage
     * @return
     */
    private static String createMessage(Object rawMessage) {
        /**
         * Throwable().getStackTrace()获取的是程序运行的堆栈信息，也就是程序运行到此处的流程，以及所有方法的信息
         * 这里我们为什么取2呢？0是代表createMessage方法信息，1是代表上一层方法的信息，这里我们
         * 取它是上两层方法的信息
         */
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String fullClassName = stackTraceElement.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        return className + "." + stackTraceElement.getMethodName() + "(): " + rawMessage;
    }
}

