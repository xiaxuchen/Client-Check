package com.cxyz.commons.application;

import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.commons.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/9/20.
 */

public class MyApp extends BaseApplication{


    private HashMap<String,Object> attributes;

    private static User user;

    /**
     *
     * 获取当前用户信息
     * @return
     */
    public static User getUser() {
        return MyApp.user;
    }

    /**
     * 登录成功后设置当前用户信息
     * @param user
     */
    public static void setUser(User user) {
        MyApp.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance().init(this));
        attributes = new HashMap<>();
        //初始化ToastUtils
        ToastUtil.init(getApplicationContext());
        //初始化SpUtils
        SpUtil.init(getApplicationContext());
        //初始化CommonOkHttp
        CommonOkHttpClient.init(getApplicationContext());
        Student stu = new Student("17478093");
        stu.setGrade(new Grade(1));
        stu.setCollege_name("信计学院");
        stu.set_name("夏旭晨");
        stu.setPower(5);
        stu.setPwd("12345678");
        stu.setSex("男");
        stu.setTel("17779911413");
        setUser(stu);
    }

    /***
     * 添加或者覆盖一个key所对应的全局map中的值
     * @param key 所设置属性的键
     * @param value 所设置属性的值
     */
    public void setAttribute(String key,Object value)
    {
        attributes.put(key,value);
    }

    /***
     *获取给出的key在全局map中所对应的value
     * @param key 要获取的属性的键
     * @return 返回key所对应的对象，若没有则返回null
     */
    public Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    /***
     * 获取全局map给出的key所对应的value
     * @param key 要移除的属性的键
     */
    public void removeAttribute(String key)
    {
        attributes.remove(key);
    }

    /***
     * 清空所有全局属性（不常用，使用的时候请与他人讨论）
     */
    public void clearAttribute() {
        attributes.clear();
    }

    /***
     * 获取map中是否包含相应的key
     */
    public boolean containsKey(Object key) {
        return attributes.containsKey(key);
    }

    /***
     * 获取map中是否包含相应的值
     */
    public boolean containsValue(Object value) {
        return attributes.containsValue(value);
    }

    /***
     * 可以将一个map中的所有键值对都添加进全局属性
     * @param map 想添加多个属性所存放的map
     */
    public void putAll(Map map) {
        attributes.putAll(map);
    }

    /***
     * 获取全局属性的大小
     * @return 全局属性的大小
     */
    public int size() {
        return attributes.size();
    }

}
