package com.cxyz.vac.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.vac.dto.VacateDto;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public abstract class IAuditModel extends IBaseModel {

    /**
     * 获取请假信息审核
     * @param listener
     */
    public abstract void getVacatesToAudit(ModelListener<List<VacateDto>,String> listener);

    /**
     * 审核请假
     * @param id 请假id
     * @param state 状态
     * @param info 回复
     * @param power 审核人的power
     * @param listener
     */
    public abstract void auditVacate(Integer id,Integer state,String info,Integer power,ModelListener<String,String> listener);
}
