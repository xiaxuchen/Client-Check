package com.cxyz.info.imodel;

import com.cxyz.commons.IModel.IBaseModel;

/**
 * Created by Administrator on 2019/1/2.
 */

public abstract class IUploadModel extends IBaseModel {

    /**
     * 是否可以导入用户信息
     * @param listener
     */
    public abstract void isUserImportEnable(ModelListener<Boolean,String> listener);


    /**
     * 是否可以导入课程信息
     * @param listener
     */
    public abstract void isLessonImportEnable(ModelListener<Boolean,String> listener);
}
