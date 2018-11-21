package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.date.Date;
import com.cxyz.homepage.imodel.impl.MySubjectsModelImpl;
import com.cxyz.homepage.iview.SubjectsView;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public abstract class MySubjectsPresenter extends IBasePresenter<MySubjectsModelImpl,SubjectsView>{
    /**
     *
     * @param _id
     * @param date
     */
    public abstract void setMySubjects(String _id, Date date);
}
