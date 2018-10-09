package com.cxyz.homepage.imodel;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.JsonUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.NetWorkHomeUrl;
import com.cxyz.logiccommons.domain.RecordDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public class MassageList_ModelImpl implements MassageList_Model {
    /**
     * 获取该学生的个人的一个又一个的考勤情况
     * @return
     */
    @Override
    public void getMassageInfo(String id,String url,final getMassageInfoListener listener) {
        Map<String,String> map = new HashMap<>();
        map.put("method","getRecordDetails");
        map.put("id",id);
        RequestParams params = new RequestParams(map);
        LogUtil.e(NetWorkHomeUrl.RDS_URL);
        try {
            CommonOkHttpClient.post(NetWorkHomeUrl.RDS_URL,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    List<RecordDetail> recordDetails = JsonUtil.jsonToListObject(responseObj.toString(), RecordDetail.class);
                    listener.getInfoSuccess(recordDetails);
                }
                @Override
                public void onFailure(Object error) {
                    if (listener!=null){
                        listener.getInfFail("请求是煞笔煞笔煞笔煞笔");
                    }
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
            if (listener!=null){
                listener.getInfFail("你的网有问题!!!");
            }
        }


    }


}
