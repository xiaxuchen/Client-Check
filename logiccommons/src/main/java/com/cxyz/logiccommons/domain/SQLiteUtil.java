package com.cxyz.logiccommons.domain;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 28058 on 2018/10/14.
 */

public class SQLiteUtil extends SQLiteOpenHelper {

    SQLiteDatabase db;

    //SQLite数据库的名字
    public static final String DB_NAME="user.db";
    public static final String TB_NAME_StuInfo="stuInfo";
    public static final String TB_NAME_TaskInfo="TaskInfo";
    public static final String TB_NAME_Info="Info";
    //版本号
    private static final int VERSION=1;

    public SQLiteUtil(Context context){

        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table TB_NAME_StuInfo(_id integer primary key , _name char(10) )");
        db.execSQL("create table TB_NAME_TaskInfo(_id integer primary key autoincrement , _name char(10),sponser char(10),sponserId integer,classRoom char(10),classRoomId integer,grade char(10),gradeId integer )");
        db.execSQL("create table TB_NAME_Info(_id integer primary key , sender char(20),receiver char(20),content varchar(50),state integer )");

        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //数据库升级
        if(newVersion>oldVersion){

        }

    }

}
