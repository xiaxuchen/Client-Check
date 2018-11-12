package com.cxyz.logiccommons.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cxyz.logiccommons.domain.ClassRoom;
import com.cxyz.logiccommons.domain.Grade;
import com.cxyz.logiccommons.domain.Info;
import com.cxyz.logiccommons.domain.SQLiteUtil;
import com.cxyz.logiccommons.domain.TaskInfo;
import com.cxyz.logiccommons.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28058 on 2018/10/14.
 */

public class DBManager {

    private SQLiteUtil sqLiteUtil;
    private SQLiteDatabase database;
    public static DBManager instance;

    public DBManager(Context context){
        this.sqLiteUtil=new SQLiteUtil(context);
        this.database=sqLiteUtil.getWritableDatabase();
    }


    //初始化
    public static final DBManager getInstance(Context context){

        if(instance==null){
            instance=new DBManager(context);
        }
        return instance;
    }

    public void close(){
        if(database.isOpen()){
            database.close();
        }
        if(sqLiteUtil!=null){
            sqLiteUtil.close();
        }
        if(instance!=null){
           instance=null;
        }
    }


    //查询本地数据库内的学生信息
//    public List<Student> queryStu(){
//        Cursor cursor = database.rawQuery("select * from"+sqLiteUtil.TB_NAME_StuInfo,null);
//        List<Student> list = new ArrayList<Student>();
//
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            Student student = new Student();
//            student.set_id(cursor.getString(cursor.getColumnIndex("_id")));
//            student.set_name(cursor.getString(cursor.getColumnIndex("_name")));
//            list.add(student);
//        }
//        close();
//        return list;
//    }

    //查询本地数据库内的考勤任务信息s
//    public List<TaskInfo> queryTaskInfo(){
//
//        User sponser=new User();
//        ClassRoom classRoom=new ClassRoom();
//        Grade grade=new Grade();
//
//        Cursor cursor = database.rawQuery("select * from "+sqLiteUtil.TB_NAME_TaskInfo,null);
//        List<TaskInfo> list = new ArrayList<TaskInfo>();
//
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            TaskInfo taskInfo = new TaskInfo();
//            taskInfo.set_id(cursor.getString(cursor.getColumnIndex("_id")));
//            taskInfo.set_name(cursor.getString(cursor.getColumnIndex("_name")));
//
//            sponser.set_name(cursor.getString(cursor.getColumnIndex("Sponser")));
//            taskInfo.setSponser(sponser);
//
//            classRoom.set_name(cursor.getString(cursor.getColumnIndex("classroomn")));
//            taskInfo.setClassRoom(classRoom);
//
//            grade.set_name(cursor.getString(cursor.getColumnIndex("grade")));
//            taskInfo.setGrade(grade);
//
//            list.add(taskInfo);
//        }
//        close();
//        return list;
//    }

    //查询本地数据库内的聊天消息(暂不实现)
//    public List<Info> queryInfo(){
//
//        User sender=new User();
//        User receiver=new User();
//
//        Cursor cursor = database.rawQuery("select * from "+sqLiteUtil.TB_NAME_Info,null);
//        List<Info> list = new ArrayList<Info>();
//
//        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//            Info info = new Info();
//            info.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
//
//            sender.set_name(cursor.getString(cursor.getColumnIndex("sender")));
//            info.setSender(sender);
//
//            receiver.set_name(cursor.getString(cursor.getColumnIndex("receiver")));
//            info.setReceiver(receiver);
//
//            info.setContent(cursor.getString(cursor.getColumnIndex("content")));
//
//            info.setState(cursor.getInt(cursor.getColumnIndex("state")));
//
//            list.add(info);
//        }
//        close();
//        return list;
//    }

    public long insertInto(String TableName, ContentValues contentValues){
        //insert into 表名(字段列表) values(值列表)
        return database.insert(TableName,null,contentValues);
    }

    public int  Updata(String TableName,ContentValues contentValues,String whereClause,String[] whereArgs){

        return database.update("person_inf",contentValues,whereClause,whereArgs);
    }

    public int delete(String TableName,String whereClause,String[] whereArgs){
        return database.delete(TableName,whereClause,whereArgs);
    }

    public List query(boolean distinct,String TableName,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit){
        Cursor cursor=database.query(TableName,columns,selection,selectionArgs,null,null,orderBy,limit);

        List list=new ArrayList();

        switch (TableName){
            case SQLiteUtil.TB_NAME_Info:for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

                //这里需要从别的地方获取
                User sender=new User();
                User receiver=new User();

                Info info = new Info();
                info.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                info.setSender(sender);
                info.setReceiver(receiver);
                info.setContent(cursor.getString(cursor.getColumnIndex("content")));
                info.setState(cursor.getInt(cursor.getColumnIndex("state")));
                list.add(info);
            }
                close();
            break;

            case SQLiteUtil.TB_NAME_TaskInfo: User sponser=new User();

                //这里需要从别处获取
                ClassRoom classRoom=new ClassRoom();
                Grade grade=new Grade();


                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    TaskInfo taskInfo = new TaskInfo();
                    taskInfo.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                    taskInfo.set_name(cursor.getString(cursor.getColumnIndex("_name")));

                    sponser.setName(cursor.getString(cursor.getColumnIndex("Sponser")));
                    taskInfo.setSponser(sponser);

                    classRoom.set_name(cursor.getString(cursor.getColumnIndex("classroomn")));
                    taskInfo.setClassRoom(classRoom);

                    grade.set_name(cursor.getString(cursor.getColumnIndex("grade")));
                    taskInfo.setGrade(grade);

                    list.add(taskInfo);
                };
                break;
            case SQLiteUtil.TB_NAME_StuInfo:
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    User student = new User();
                    student.setId(cursor.getString(cursor.getColumnIndex("_id")));
                    student.setName(cursor.getString(cursor.getColumnIndex("_name")));
                    list.add(student);
                }break;
        }
        close();
        return list;
    }

}
/*
*使用query方法查询记录
SQLiteDatabase的query方法签名为Cursor query(boolean distinct,String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy,String limit)，这个query方法的参数说明如下。
distinct：指定是否去除重复记录。
table：执行查询数据的表名。
columns：要查询出来的列名。
selection：查询条件子句。
selectionArgs：用于为selection子句中占位符传入参数值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。
groupBy：用于控制分组。
having：用于对分组进行过滤。
orderBy：用于对记录进行排序。
limit：用于进行分页。
例如查询出person_inf表中人名以孙开头的数据
Cursor cursor=db.query("person_inf",new String[]{"_id,name,age"},"name like ?",new String []{"孙%"},null,null,"personid desc","5,10");
cursor.close();
*
* */