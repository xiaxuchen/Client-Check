package com.cxyz.log.core;


import android.util.Log;

/**
 * TODO 将错误日志输出到文件中进行保存,便于在日后上传至服务器
 * Created by 夏旭晨 on 2018/9/21.
 * Log日志工具类
 */
public class LogManager {

    private static final String TAG = "##Log##";
    //Log开关
    private static final boolean DEBUG = true;

    /**
     * 不会自动加类名和方法名前缀
     * @param message
     */
    public static void noPrefix(String message){
        if (DEBUG)
            Log.i(TAG,message);
    }

    public static void d(String message) {
        if (DEBUG)Log.d(TAG, createMessage(message));
    }

    public static void e(String message) {
        if (DEBUG) Log.e(TAG, createMessage(message));
    }

    public static void i(String message) {
        if (DEBUG) Log.i(TAG, createMessage(message) );
    }

    public static void v(String message) {
        if (DEBUG) Log.v(TAG, createMessage(message));
    }

    public static void w(String message) {
        if (DEBUG)Log.w(TAG, createMessage(message));
    }

    public static void wtf(String message) {
        if (DEBUG) Log.wtf(TAG, createMessage(message));
    }

    public static void println(String message) {
        if (DEBUG) Log.println(Log.INFO, TAG, message);
    }

    /**
     * 获取有类名与方法名的logString
     * @param rawMessage
     * @return
     */
    private static String createMessage(String rawMessage) {
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

