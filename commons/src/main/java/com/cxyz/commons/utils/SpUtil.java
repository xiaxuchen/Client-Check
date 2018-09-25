package com.cxyz.commons.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import java.util.Map;
import java.util.Set;

/**
 * Created by 夏旭晨 on 2018/9/20.
 *首次使用需用init方法进行初始化，一般在application中完成初始化工作
 * 可以通过getInstance()方法直接获取实例
 *
 * 复写了SharedPreferences和SharedPreferences.Editor的
 * 所有方法，直接通过SpUtils对象进行调用即可，putxxx()
 * 方法无需手动提交，并且可以进行链式调用
 *
 *
 */

public class SpUtil {

    private static final String FILENAME = "config";

    private SharedPreferences sp;

    private SharedPreferences.Editor edit;

    private static SpUtil su;

    private static Context context;

    private SpUtil(Context context, String name, int mode)
    {
        sp = context.getSharedPreferences(name, mode);
        edit = edit();
    }

    public static void init(Context context)
    {
        SpUtil.context = context;
    }

    public synchronized static SpUtil getInstance() {
        if(context==null)
            return null;
        if (su == null) {
            su = new SpUtil(context, FILENAME, context.MODE_PRIVATE);
        }
        return su;
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValues) {
        return sp.getStringSet(key, defValues);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public Editor edit() {
        return sp.edit();
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sp.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sp.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public Editor putString(String key, String value) {
        edit.putString(key, value);
        edit.commit();
        return edit;
    }

    public Editor putStringSet(String key, Set<String> values) {
        edit.putStringSet(key, values);
        edit.commit();
        return edit;
    }

    public Editor putInt(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
        return edit;
    }

    public Editor putLong(String key, long value) {
        edit.putLong(key, value);
        edit.commit();
        return edit;
    }

    public Editor putFloat(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
        return edit;
    }

    public Editor putBoolean(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
        return edit;
    }

    public Editor remove(String key) {
        edit.remove(key);
        edit.commit();
        return edit;
    }

    public Editor clear() {
        edit.clear();
        edit.commit();
        return edit;
    }

    public boolean commit() {
        return edit.commit();
    }

    public void apply() {
        edit.apply();
    }

}

