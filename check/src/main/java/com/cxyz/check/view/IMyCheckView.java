package com.cxyz.check.view;

import com.cxyz.commons.IView.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public interface IMyCheckView extends IBaseView {

    /**
     * 显示考勤记录
     * @param data expandablelistview的数据
     * @param times 考勤次数
     * @param checkerror 异常
     * @param lateAndEarly 迟到或早退次数
     * @param absent 旷课次数
     * @param progress 出勤率
     */
    void showRecords(List<Map<String,Object>> data, int times, int checkerror, int lateAndEarly, int absent, int progress);

    /**
     * 显示错误信息
     * @param error 错误信息
     */
    void showError(String error);
}
