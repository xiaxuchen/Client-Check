package com.cxyz.vac.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by Administrator on 2018/12/23.
 */

public interface NetWorkConstant {

    /**
     * 提交请假信息链接
     */
    String COMMIT_VAC_URL = Constant.ROOT_URL+"/vacate/vacate";

    /**
     * 获取请假信息审核
     */
    String GET_VACATES_TO_AUDIT = Constant.ROOT_URL+"/vacate/getVacatesToAudit";

    /**
     * 审核请假
     */
    String AUDIT_VACATE = Constant.ROOT_URL+"/vacate/auditVac";

    /**
     * 获取请假信息
     */
    String GET_VACATES = Constant.ROOT_URL+"/vacate/getVacates";
}
