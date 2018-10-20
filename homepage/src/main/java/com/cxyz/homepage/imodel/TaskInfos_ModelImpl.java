package com.cxyz.homepage.imodel;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.logiccommons.domain.TaskInfo;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TaskInfos_ModelImpl implements TaskInfos_Model{
    @Override
    public void getTaskInfo(int grede_id, Date date, final getTaskInfoListener listener) {
        RequestCenter.getTaskInfos(grede_id, date, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                if(listener!=null)
                    listener.getInfoSuccess(GsonUtil.GsonToList(responseObj.toString(), TaskInfo.class));
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.getInfFail(error);
                else if(error instanceof OKHttpException)
                    listener.getInfFail(((OKHttpException) error).getMessage());
                else
                    listener.getInfFail("未知错误");
            }
        });
    }
}
