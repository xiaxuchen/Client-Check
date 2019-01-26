package com.cxyz.vac.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.vac.dto.VacateDto;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public interface IAuditView extends IBaseView {

    /**
     * 获取请假成功
     * @param vacateDtos
     */
    void getVacatesSuccess(List<VacateDto> vacateDtos);

    /**
     * 获取请假信息失败
     * @param error 错误信息
     */
    void getVacatesError(String error);

    /**
     * 审核成功
     */
    void auditSuccess(int position,int state);

    /**
     * 审核失败
     * @param error 错误信息
     */
    void auditFail(String error);
}
