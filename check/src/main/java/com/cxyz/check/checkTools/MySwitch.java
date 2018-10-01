package com.cxyz.check.checkTools;
/**
 * Created by 28058 on 2018/9/26.
 */

public class MySwitch  {

    private static String []name;

    public  static String[] switch_userPower(int userPower){

        switch (userPower){
            /**
             * power属性用来区分权限
             * 0为普通学生权限
             * 5为考勤人权限
             * 30为普通任课老师权限
             * 35为班主任权限
             * 45为系部管理员权限
             * 55为校级管理员权限
             * 100为超级管理员权限
             * 默认为普通学生权限
             */
            case 0:
                setName(new String[]{"你是学生"})
                ;break;
            case 5:
                //用户为1级权限
                setName(new String[]{"开始考勤","临时考勤","添加学生","删除学生"})
                ;break;
            case 30:
                setName(new String[]{"你是老师"})
                ;break;
            case 35:
                setName(new String[]{"你是班主任"})
                ;break;
            case 45:
                setName(new String[]{"你是系部管理员"})
                    ;break;
            case 55:
                setName(new String[]{"你是校级管理员"})
                ;break;
            case 100:
                setName(new String[]{"你可以删库跑路了"})
                ;break;
            default:setName(new String[]{"什么鬼"});break;
        }
            return name;
    }

    public static void setName(String[] name) {
        MySwitch.name = name;
    }
}
