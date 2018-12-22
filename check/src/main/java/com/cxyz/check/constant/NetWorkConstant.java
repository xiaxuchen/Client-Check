package com.cxyz.check.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class NetWorkConstant {


    //获取班级学生的url
    public static final String GET_STUS = Constant.ROOT_URL+"/user/gradeStus";
    //提交的url
    public static  final String COMMIT_URL = Constant.ROOT_URL+"/record/commitCheck";
    //查看考勤记录的url
    public static final String GRADE_CHECK_URL = Constant.ROOT_URL+"/record/getRecords";

    /**
     * 检查是否有考勤任务
     */
    public static final String CHECKCOMP_URL = Constant.ROOT_URL+"/task/checkTask";

    /**
     * 获取考勤历史
     */
    public static final String HISTORY = Constant.ROOT_URL+"/record/getHistory";

    /**
     * 加载更多考勤历史
     */
    public static final String LOAD_MORE = Constant.ROOT_URL+"/record/loadMore";

    /**
     * 获取考勤详细记录
     */
    public static final String ALTER_RECORDS = Constant.ROOT_URL+"/record/getAlterRecords";

    /**
     * 修改考勤记录
     */
    public static final String UPDATE_RECORDS = Constant.ROOT_URL+"/record/updateAlteds";

}
