package com.cxyz.commons.utils.z_ExelRead;

/**
 * Created by 鱼塘主 on 2018/9/27.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 37266 on 2017/4/20.
 */

public class ClazzSQLOpenHelper extends SQLiteOpenHelper {
    private static ClazzSQLOpenHelper clazzSQLOpenHelper =null;
    //构造方法（实例化一个类）
    public ClazzSQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    //单例模式
    public static ClazzSQLOpenHelper getInstance(Context context){
        if(clazzSQLOpenHelper ==null){//如果数据库打开助手为空，那么我们实例化数据库打开助手。
            clazzSQLOpenHelper =new ClazzSQLOpenHelper(context,"Clazz.db",null,1);
        }
        return clazzSQLOpenHelper;
    }
    //获取数据库的连接
    private Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stat = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //创建数据库保存我们的表EXCEL中的数据
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据表，保存我们的excel数据。
        sqLiteDatabase.execSQL("create table clazzTable(_id integer primary key autoincrement," +
                "Clazz_name char(30)," +
                "Clazz_teacher char(30)," +
                "Clazz_room char(30)," +
                "Clazz_class char(30)"+
                "Clazz_week char(8)"+
                "Clazz_startTime char(4)," +
                "Clazz_endTime char(4)," +
                "Clazz_startWeek char(8)," +
                "Clazz_endWeek char(8)"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}