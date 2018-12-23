package com.cxyz.check.model;

import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by Administrator on 2018/12/15.
 */

public abstract class IOtherstateModel extends IBaseModel {

    /**
     * 提交特殊情况
     * @param dto
     * @param listener
     */
    public abstract void commitOtherstate(CommitCheckDto dto, CommitListener listener);

    /**
     * 提交监听
     */
    public interface CommitListener{

        /**
         * 提交成功
         * @param info
         */
        void onSuccess(String info);

        /**
         * 提交失败
         * @param error
         */
        void onFail(String error);
    }
}
