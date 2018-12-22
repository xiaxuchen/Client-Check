package com.cxyz.check.ipresenter;

import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.check.model.IAlterModel;
import com.cxyz.check.view.IAlterView;
import com.cxyz.commons.IPresenter.IBasePresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/12/9.
 */

public abstract class IAlterPresenter extends IBasePresenter<IAlterModel,IAlterView> {

    /**
     * 获取考勤记录
     * @param compId 考勤id
     */
    public abstract void getAlterRecords(Integer compId);

    /**
     * 提交考勤修改
     * @param alted 修改记录
     * @param compId 考勤id
     */
    public abstract void commitAlted(Integer compId,List<AlterRecordDto> alted);
}
