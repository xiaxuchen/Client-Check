package com.cxyz.homepage.imodel.impl;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.feature_z_domain.MySubject;
import com.cxyz.homepage.feature_z_domain.SubjectRepertory;
import com.cxyz.homepage.imodel.MySubjectsModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public class MySubjectsModelImpl implements MySubjectsModel{

    @Override
    public void getMySubjects(String _id, Date date, final getMySubjectInfo listener) {
        Map map = new HashMap();
        map.put("method","getMySubjects");
        map.put("id",_id+"");
        map.put("date",date+"");
        RequestParams params = new RequestParams(map);
        RequestCenter.getMySubjects(params,new DisposeDataListener(){
            @Override
            public void onSuccess(Object responseObj) {
                if (responseObj!=null) {
                    List<MySubject> mySubjects = SubjectRepertory.parse(responseObj.toString());
                    listener.getMySubjectsSuccess(mySubjects);
                }else {
                    listener.getMySubjectsFail("返回null");
                }

            }

            @Override
            public void onFailure(Object error) {
                listener.getMySubjectsFail(error);
            }
        });
    }
}
