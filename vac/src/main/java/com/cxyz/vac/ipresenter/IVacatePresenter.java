package com.cxyz.vac.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.vac.imodel.IVacateModel;
import com.cxyz.vac.iview.IVacateView;

/**
 * Created by Administrator on 2018/12/23.
 */

public abstract class IVacatePresenter extends IBasePresenter<IVacateModel,IVacateView> {

    /**
     * 请假
     * @param start 开始时间
     * @param end 结束时间
     * @param timeLen 请假天数
     * @param type 请假类型
     * @param des 请假原因
     */
    public abstract void vacate(String start,String end,Integer timeLen,Integer type,String des);
}
