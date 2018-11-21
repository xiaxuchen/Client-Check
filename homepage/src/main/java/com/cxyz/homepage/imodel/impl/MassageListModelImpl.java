package com.cxyz.homepage.imodel.impl;

import com.cxyz.homepage.imodel.MassageListModel;

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
//        RequestCenter.getRecords(id, type, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                try {
//                    listener.getInfoSuccess((List<RecordDetail>) GsonUtil.fromJson(responseObj.toString(), RecordDetail.class));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    listener.getInfFail("服务器解析错误");
//                }
//            }
//
//            @Override
//            public void onFailure(Object error) {
//                if(error instanceof String)
//                    listener.getInfFail(error);
//                else if(error instanceof OKHttpException)
//                    listener.getInfFail(((OKHttpException) error).getMessage());
//                else
//                    listener.getInfFail("未知错误");
//            }
//        });
    }


}
