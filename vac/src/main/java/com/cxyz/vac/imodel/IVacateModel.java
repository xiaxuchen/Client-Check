package com.cxyz.vac.imodel;

import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by Administrator on 2018/12/23.
 */

public abstract class IVacateModel extends IBaseModel {

    /**
     * 上传请假信息
     * @param start 开始时间
     * @param end 结束时间
     * @param des 事由
     * @param listener 回调
     */
    public abstract void vacate(String start,String end,String des,ModelListener<String,String> listener);
}
