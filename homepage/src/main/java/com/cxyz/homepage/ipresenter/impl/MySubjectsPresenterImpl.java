package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.feature_z_domain.MySubject;
import com.cxyz.homepage.imodel.MySubjectsModel;
import com.cxyz.homepage.imodel.impl.MySubjectsModelImpl;
import com.cxyz.homepage.ipresenter.MySubjectsPresenter;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public class MySubjectsPresenterImpl extends MySubjectsPresenter {

    @Override
    public void setMySubjects(String _id, Date date) {
        mIModle.getMySubjects(_id, date, new MySubjectsModel.getMySubjectInfo() {
            @Override
            public void getMySubjectsSuccess(List<MySubject> mySubjects) {
                mIView.setSubjects(mySubjects);
            }

            @Override
            public void getMySubjectsFail(Object error) {
                ToastUtil.showShort(error.toString());
            }
        });
    }

    @Override
    public MySubjectsModelImpl createModel() {
        return new MySubjectsModelImpl();
    }
}
