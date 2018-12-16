package com.cxyz.check.ipresenter;

import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.model.IOtherstateModel;
import com.cxyz.check.view.IOtherstateView;
import com.cxyz.commons.IPresenter.IBasePresenter;

/**
 * Created by Administrator on 2018/12/15.
 */

public abstract class IOtherstatePresenter extends IBasePresenter<IOtherstateModel,IOtherstateView> {

    /**
     * 提交特殊情况
     * @param dto
     */
    public abstract void commitOtherstate(CommitCheckDto dto);
}
