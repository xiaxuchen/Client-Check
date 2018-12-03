package com.cxyz.check.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class NetWorkConstant {

    public static final String ROOT_URL = "http:/119.29.101.171:8080/Server_Check/check";

    //获取班级学生的url
    public static final String GET_STUS = Constant.ROOT_URL+"/gradeStus";
    //提交的url
    public static  final String COMMIT_URL = Constant.ROOT_URL+"/commitCheck";
    //查看考勤记录的url
    public static final String GRADE_CHECK_URL = Constant.ROOT_URL+"/getGradeCheck";
    //获取统计结果
    public static final String STATISTIC_URL = Constant.ROOT_URL+"/servlet/StatisticServlet";

    /**
     * 检查是否有考勤任务
     */
    public static final String CHECKCOMP_URL = Constant.ROOT_URL+"/checkTask";
}
