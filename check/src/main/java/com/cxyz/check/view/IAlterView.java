package com.cxyz.check.view;

import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.commons.IView.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/12/9.
 */

public interface IAlterView extends IBaseView {

    /**
     * 显示历史记录详细信息
     * @param dtos 历史详情
     */
    void showAlterRecords(List<AlterRecordDto> dtos);

    /**
     * 显示获取错误
     * @param error 错误信息
     */
    void showGetAltersError(String error);

    /**
     * 显示加载历史详细信息
     */
    void showLoadAlters();

    /**
     * 更改emptyview为暂无
     */
    void changeLoadAlters();

    /**
     * 显示错误信息
     */
    void showError(String error);

    /**
     * 显示修改成功
     * @param success
     */
    void showAlterSuccess(String success);

    /**
     * 显示修改失败
     * @param error
     */
    void showAlterFail(String error);
}
