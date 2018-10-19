package com.cxyz.homepage.imodel;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.logiccommons.domain.RecordDetail;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public class MassageListModelImpl implements MassageListModel {
    /**
     * 获取该学生的个人的一个又一个的考勤情况
     * @return
     */
    @Override
    public void getMassageInfo(String id, Integer type, final getMassageInfoListener listener) {
        RequestCenter.getRecords(id, type, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                listener.getInfoSuccess(GsonUtil.GsonToList(responseObj.toString(), RecordDetail.class));
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