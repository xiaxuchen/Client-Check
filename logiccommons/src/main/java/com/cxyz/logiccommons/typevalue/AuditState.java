package com.cxyz.logiccommons.typevalue;

public interface AuditState {

    /**
     * 待审核
     */
    int WAIT_AUDIT = 0;

    /**
     * 审核成功
     */
    int SUCCESS = 1;

    /**
     * 审核失败
     */
    int FAIL = -1;
}
