package com.cxyz.logiccommons.typevalue;

/**
 * 考勤任务完成情况状态
 */
public interface TaskCompletionState {
    /**
     * 待考勤
     */
    public static final int WAIT = 0;
    /**
     * 考勤完成
     */
    public static final int COMPLE = 1;
    /**
     * 未考勤
     */
    public static final int NOTCHECKED = -1;
    /**
     * 特殊情况
     */
    public static final int OTHERSTATE = -2;
}
