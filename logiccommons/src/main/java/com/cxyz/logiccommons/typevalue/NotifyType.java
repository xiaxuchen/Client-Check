package com.cxyz.logiccommons.typevalue;

/**
 * Created by Administrator on 2018/12/26.
 */

public interface NotifyType {

    int ERROR = -2;

    int CUSTOM = -1;

    int BAD_CHECK_RECORD = 0;//推送考勤异常记录

    int VACATION_AUDIT = 1;//推送请假

    int VACATION = 2;//请假最新信息
}
