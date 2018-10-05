package com.cxyz.homepage.imodel;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
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
    public void getMassageInfo(String id, String url,final getMassageInfoListener listener) {
        String murl = url+id;
        Map<String,String> map = new HashMap<>();
        map.put("method","record");
        map.put("id",id);
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(murl,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    try {
                        if (listener!=null){
                            listener.getInfoSuccess(new JSONObject((String) responseObj));
                        }
                    } catch (JSONException e) {
                        if (listener!=null){
                            listener.getInfFail("狗东西:"+"JSONException");
                        }
                    }
                }

                @Override
                public void onFailure(Object error) {
                    if (listener!=null){
                        listener.getInfFail(error);
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
