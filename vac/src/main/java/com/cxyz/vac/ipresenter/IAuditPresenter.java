package com.cxyz.vac.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.vac.imodel.IAuditModel;
import com.cxyz.vac.iview.IAuditView;

/**
 * Created by Administrator on 2018/12/31.
 */

public abstract class IAuditPresenter extends IBasePresenter<IAuditModel,IAuditView> {

    /**
     * 获取请假信息
     */
    public abstract void getVacates();

    /**
     * 审核请假
     * @param id 请假id
     * @param state 状态
     * @param info 信息
     */
    public abstract void auditVac(int position,Integer id,Integer state,String info);
}
