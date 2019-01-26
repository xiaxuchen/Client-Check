package com.cxyz.vac.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.vac.dto.VacateDto;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public abstract class IMineVacateModel extends IBaseModel{

    /**
     * 获取请假信息
     * @param listener
     */
    public abstract void getVacates(ModelListener<List<VacateDto>,String> listener);
}
