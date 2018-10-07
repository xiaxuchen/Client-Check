package com.cxyz.homepage.excelutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cxyz.homepage.feature_z_domain.Clazz;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 鱼塘主 on 2018/9/27.
 */

public class ClazzDatabase   {
    private Context mContext;
    private SQLiteDatabase cSqliteDatabase;
    private ClazzDatabase mClazzDatabase;
    private static Connection con;
    private static ClazzSQLOpenHelper clazzSQLOpenHelper;
    private ClazzDatabase(){
        this.mContext = mContext;
    }
    //单例模式
    public ClazzDatabase getInstance(Context mContext){
        if(mClazzDatabase==null){
            mClazzDatabase = new ClazzDatabase();
        }
        return mClazzDatabase;
    }


    /**
     * 将课表存到数据库中
     * /**
     * _id integer primary key autoincrement," +
     "Clazz_name char(30)," +
     "Clazz_teacher char(30)," +
     "Clazz_room char(30)," +
     "Clazz_class char(30)"+
     "Clazz_week char(8)"+
     "Clazz_startTime char(4)," +
     "Clazz_endTime char(4)," +
     "Clazz_startWeek char(8)," +
     "Clazz_endWeek char(8)"+
     "Clazz_status char(20)"+
     ")");
     *
     * @param clazz_table  HashMap<String,List<Clazz>>课表
     * @param context 上下文的对象
     */
    public  void saveClazzInfo(HashMap<String,List<Clazz>> clazz_table, Context context){
        clazzSQLOpenHelper =  ClazzSQLOpenHelper.getInstance(context);
        cSqliteDatabase = clazzSQLOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String[] week = {"周一","周二","周三","周四","周五"};

        for (int j = 0 ; j < clazz_table.size();j++){
            List<Clazz> clazzs = clazz_table.get(week[j]);
             for (int k = 0 ; k < clazzs.size();k++) {
                 Clazz clazz = clazzs.get(k);

                     values.put("_id",clazz.getId());
                     values.put("Clazz_name",clazz.getName());
                     values.put("Clazz_teacher",clazz.getTeacher());
                     values.put("Clazz_room",clazz.getRoom());
                     values.put("Clazz_class","");
                     values.put("Clazz_week",clazz.getWeek());
                     values.put("Clazz_startTime",clazz.getStarttime());
                     values.put("Clazz_endTime",clazz.getEndtime());
                     values.put("Clazz_startWeek",clazz.getStartWeek());
                     values.put("Clazz_endWeek",clazz.getEndWeek());
                     values.put("Clazz_status",clazz.getStatus());
                  cSqliteDatabase.insert("clazzTable",null,values);//向数据库表中插入一行
                  values = new ContentValues();
             }
        }
        cSqliteDatabase.close();

    }
    private void SetConnection(){
        con =  clazzSQLOpenHelper.getConnection();
    }

}
