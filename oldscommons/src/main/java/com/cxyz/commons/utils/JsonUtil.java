package com.cxyz.commons.utils;

import android.content.ContentValues;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 夏旭晨 on 2018/9/21.
 * Json的解析工具类
 */

public class JsonUtil {
    private JsonUtil(){}

    private static Gson gson = new Gson();

    /**
     * 将一个对象转换成一个Json字符串
     * @param t
     * @return
     */
    public static <T> String objectToJson(T t){
        if (t instanceof String) {
            return t.toString();
        } else {
            return gson.toJson(t);
        }
    }

    /**
     * 将Json字符串转换成对应对象
     * @param jsonString    Json字符串
     * @param clazz     对应字节码文件.class
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> T jsonToObject(String jsonString, Class<T> clazz){
        if (clazz == String.class) {
            return (T) jsonString;
        } else {
            return (T)gson.fromJson(jsonString, clazz);
        }
    }

    /**
     * 将List集合转换为json字符串
     * @param list  List集合
     * @return
     */
    public static<T> String listToJson(List<T> list){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        try {
            for (int i = 0; i < list.size(); i++) {
                jsonObject = new JSONObject(objectToJson(list.get(i)));
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (jsonObject != null) {
                jsonObject = null;
            }
        }
        return jsonArray.toString();
    }

    /**
     * 将数组转换成json字符串
     * @param array     数组
     * @return
     */
    public static<T> String arrayToJson(T[] array){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = null;
        try {
            for (int i = 0; i < array.length; i++) {
                jsonObject = new JSONObject(objectToJson(array[i]));
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (jsonObject != null) {
                jsonObject = null;
            }
        }
        return jsonArray.toString();
    }

    /**
     * 获取json字符串中的值
     * @param json  json字符串
     * @param key   键值
     * @param clazz 所取数据类型，例如：Integer.class，String.class，Double.class，JSONObject.class
     * @return  存在则返回正确值，不存在返回null
     */
    public static<T> T getJsonObjectValue(String json, String key, Class<T> clazz){
        try {
            return getJsonObjectValue(new JSONObject(json), key, clazz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取jsonObject对象中的值
     * @param jsonObject    jsonObject对象
     * @param key   键值
     * @param clazz 所取数据类型，例如：Integer.class，String.class，Double.class，JSONObject.class
     * @return  存在则返回正确值，不存在返回null
     */
    @SuppressWarnings("unchecked")
    public static<T> T getJsonObjectValue(JSONObject jsonObject, String key, Class<T> clazz){
        T t = null;
        try {
            if (clazz == Integer.class) {
                t = (T) Integer.valueOf(jsonObject.getInt(key));
            }else if(clazz == Boolean.class){
                t = (T) Boolean.valueOf(jsonObject.getBoolean(key));
            }else if(clazz == String.class){
                t = (T) String.valueOf(jsonObject.getString(key));
            }else if(clazz == Double.class){
                t = (T) Double.valueOf(jsonObject.getDouble(key));
            }else if(clazz == JSONObject.class){
                t = (T) jsonObject.getJSONObject(key);
            }else if(clazz == JSONArray.class){
                t = (T) jsonObject.getJSONArray(key);
            }else if(clazz == Long.class){
                t = (T) Long.valueOf(jsonObject.getLong(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json字符串转换为ContentValues
     * @param json  json字符串
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static ContentValues jsonToContentValues(String json){
        ContentValues contentValues = new ContentValues();
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            String key;
            Object value;
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                value = jsonObject.get(key);
                String valueString = value.toString();
                if (value instanceof String) {
                    contentValues.put(key, valueString);
                }else if(value instanceof Integer){
                    contentValues.put(key, Integer.valueOf(valueString));
                }else if(value instanceof Long){
                    contentValues.put(key, Long.valueOf(valueString));
                }else if(value instanceof Double){
                    contentValues.put(key, Double.valueOf(valueString));
                }else if(value instanceof Float){
                    contentValues.put(key, Float.valueOf(valueString));
                }else if(value instanceof Boolean){
                    contentValues.put(key, Boolean.valueOf(valueString));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Error("Json字符串不合法：" + json);
        }

        return contentValues;
    }

    /**
     * 把json对象转化为对应的List对象，类型用泛型t指定
     * @param json json字符串
     * @param <T> 返回的List的泛型
     * @return
     */
    public static <T> List<T> jsonToListObject(String json,Class<T> clazz)
    {
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(json);
        if(!el.isJsonArray())
            return null;
        else
            jsonArray = el.getAsJsonArray();
        //遍历JsonArray对象
        Iterator it = jsonArray.iterator();
        List<T> lt = new ArrayList<>();
        while(it.hasNext()){
            JsonElement e = (JsonElement)it.next();
            //JsonElement转换为JavaBean对象
            Object o = gson.fromJson(e, clazz);
            lt.add((T)o);
        }
        return lt;
    }
}
