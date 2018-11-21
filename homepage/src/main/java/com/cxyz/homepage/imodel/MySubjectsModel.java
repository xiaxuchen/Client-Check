package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.date.Date;
import com.cxyz.homepage.feature_z_domain.MySubject;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public interface MySubjectsModel extends IBaseModel{
    public void getMySubjects(String _id, Date date,final getMySubjectInfo listener);
    public interface getMySubjectInfo{
        public void getMySubjectsSuccess(List<MySubject> mySubjects);
        public void getMySubjectsFail(Object error);
    }
}
