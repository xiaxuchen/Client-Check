package com.cxyz.homepage.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public interface NetWorkHomeUrl {

    /**
     * 请求获取所有的任务信息
     */
    String TASKINFOS_URL = Constant.ROOT_URL+"/user/gradeStus";

    /**
     *
     */
    String CLAZZS_URL = Constant.ROOT_URL+"/task/getSubjects";

    /**
     * 获取考勤统计
     */
    String GET_STATISTIC = Constant.ROOT_URL+"/record/getStatistic";
    /**
     * 获取考勤异常类型人数统计
     */
    String GET_STATISTICRECORD = Constant.ROOT_URL+"/record/getStatisticRecords";
    /**
     * 获取学院id
     */
    String GET_COLLEGEID = Constant.ROOT_URL+"/envir/getCollegeGrades";

    /**
     * 获取班级和所教课程信息
     */
    String GET_GRADE_TASKS = Constant.ROOT_URL+"/task/getGradeTasks";

    /**
     * 获取统计的excel
     */
    String GET_STATISTIC_EXCEL = Constant.ROOT_URL+"/task/getStatisticExcel";
    
}
