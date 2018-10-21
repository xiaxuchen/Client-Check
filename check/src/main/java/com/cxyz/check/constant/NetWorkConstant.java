package com.cxyz.check.constant;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class NetWorkConstant {

    public static final String ROOT_URL = "http://192.168.43.73:8080/Service_Check";

    //获取班级学生的url
    public static final String GET_STUS = ROOT_URL+"/servlet/UserServlet";
    //提交的url
    public static  final String COMMIT_URL = ROOT_URL+"/servlet/CheckServlet";
    //查看考勤记录的url
    public static final String RDS_URL = ROOT_URL+"/servlet/CheckServlet";
    //获取统计结果
    public static final String STATISTIC_URL = ROOT_URL+"/servlet/StatisticServlet";
}
