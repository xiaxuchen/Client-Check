package com.cxyz.homepage.imodel.impl;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.feature_z_domain.MySubject;
import com.cxyz.homepage.imodel.MySubjectsModel;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.manager.UserManager;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public class MySubjectsModelImpl extends MySubjectsModel{

    @Override
    public void getMySubjects(String _id, Date date, final getMySubjectInfo listener) {
        Map<String,String> map = new HashMap<>();
        map.put("gradeId",UserManager.getInstance().getUser().getGradeId()+"");
        RequestParams params = new RequestParams(map);
        RequestCenter.getMySubjects(params,new DisposeDataListener(){
            @Override
            public void onSuccess(Object responseObj) {
                if (responseObj!=null) {
                    try {
                        CheckResult<List<MySubject>> checkResult = (CheckResult<List<MySubject>>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<List<MySubject>>>(){}.getType());
                        if(checkResult.isSuccess())
                        {
                            LogUtil.e(checkResult.getData());
                            listener.getMySubjectsSuccess(checkResult.getData());
                        }
                        else
                            listener.getMySubjectsFail(checkResult.getError());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.getMySubjectsFail("服务器异常");
                    }
                }else {
                    listener.getMySubjectsFail("服务器异常");
                }

            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.getMySubjectsFail(error);
                else if(error instanceof Exception)
                    listener.getMySubjectsFail(((Exception) error).getMessage());
                else listener.getMySubjectsFail(error.toString());
            }
        });
    }
}
