package com.cxyz.commons.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonUtil {
    //不用创建对象,直接使用Gson.就可以调用方法
    private static Gson gson = null;
    //判断gson对象是否存在了,不存在则创建对象
    static {
        if (gson == null) {
            //gson = new Gson();
            //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
            gson = new GsonBuilder().registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter()).setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }
    //无参的私有构造方法
    private GsonUtil() {
    }

    /**
     * 将对象转成json格式
     *
     * @param object
     * @return String
     */
    public static String toJson(Object object) throws JSONException {
        String gsonString = null;
        try{
            if (gson != null) {
                gsonString = gson.toJson(object);
            }
        }catch (Exception e)
        {
            LogUtil.e(e.getMessage());
            throw new JSONException(e.getMessage());
        }
        return gsonString;
    }


    /**
     * 将json转成特定的的对象
     *
     * @param gsonString
     * <T> 希望获得的类型
     * @return
     * @throws JSONException
     */
    public static Object fromJson(String gsonString, Type type) throws JSONException {
        Object obj = null;
        try {
            if (gson != null) {
                //传入json对象和对象类型,将json转成对象
                obj = gson.fromJson(gsonString, type);
            }
        }catch (Exception e)
        {
            LogUtil.e(e.getMessage());
            throw new JSONException(e.getMessage());
        }
        return obj;
    }

    static class TimestampTypeAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
        private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public JsonElement serialize(Timestamp ts, Type t, JsonSerializationContext jsc) {
            String dfString = format.format(new Date(ts.getTime()));
            return new JsonPrimitive(dfString);
        }
        public Timestamp deserialize(JsonElement json, Type t, JsonDeserializationContext jsc) throws JsonParseException {
            if (!(json instanceof JsonPrimitive)) {
                throw new JsonParseException("The date should be a string value");
            }

            try {
                Date date = format.parse(json.getAsString());
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }

} 