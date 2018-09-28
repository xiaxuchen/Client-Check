package com.cxyz.check.CheckUtil;
/**
 * Created by 28058 on 2018/9/26.
 */

public class MySwitch  {

    private static String []name;

    public  static String[] switch_userPower(int userPower){

        switch (userPower){

            case 0:
                setName(new String[]{"开始考勤"})
                ;break;
            case 1:
                //用户为1级权限
                setName(new String[]{"开始考勤","临时考勤","添加学生","删除学生"})
                ;break;
            case 2:;break;
            case 3:;break;
            case 4:;break;
            case 5:;break;
            case 6:;break;
            case 7:;break;
            case 8:;break;
            default:setName(new String[]{"什么鬼"});break;
        }
            return name;
    }

    public static void setName(String[] name) {
        MySwitch.name = name;
    }
}
