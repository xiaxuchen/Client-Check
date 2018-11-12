package com.cxyz.check.view;

import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.commons.IView.IBaseView;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public interface IMyCheckView extends IBaseView {

    /**
     * 显示考勤记录
     * @param checkRecordDto 考勤信息
     */
    void showRecords(CheckRecordDto checkRecordDto);

    /**
     * 显示错误信息
     * @param error 错误信息
     */
    void showError(String error);
}
