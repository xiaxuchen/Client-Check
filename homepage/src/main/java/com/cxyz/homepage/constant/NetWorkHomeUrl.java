package com.cxyz.homepage.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class NetWorkHomeUrl {
    public final static String ROOT_URL = "http:/localhost:8080/";

    public static final String RDS_URL = ROOT_URL+"/servlet/CheckServlet";

    /**
     * 请求获取所有的任务信息
     */
    public static final String TASKINFOS_URL = Constant.ROOT_URL+"/user/gradeStus";

    /**
     *
     */
    public static final String CLAZZS_URL = Constant.ROOT_URL+"/task/getSubjects";

    /**
     * 获取考勤统计
     */
    public static final String GET_STATISTIC = Constant.ROOT_URL+"/record/getStatistic";
    /**
     * 获取考勤异常类型人数统计
     */
    public static final String GET_STATISTICRECORD = Constant.ROOT_URL+"/record/getStatisticRecords";
    /**
     * 获取学院id
     */
    public static final String GET_COLLEGEID = Constant.ROOT_URL+"/envir/getCollegeGrades";
    
}
