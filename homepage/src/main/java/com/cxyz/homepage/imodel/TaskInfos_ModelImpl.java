package com.cxyz.homepage.imodel;

import com.cxyz.commons.domain.TaskInfo;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.JsonUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.NetWorkHomeUrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TaskInfos_ModelImpl implements TaskInfos_Model{
    @Override
    public void getTaskInfo(String id, String url, final getTaskInfoListener listener) {
        Map<String,String> map = new HashMap<>();
        map.put("method","");
        map.put("id",id);
        RequestParams params = new RequestParams(map);
        LogUtil.e(NetWorkHomeUrl.TASK_URL);
        try {
            CommonOkHttpClient.post(NetWorkHomeUrl.TASK_URL,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    List<TaskInfo> taskInfoList = JsonUtil.jsonToListObject(responseObj.toString(),TaskInfo.class);
                    listener.getInfoSuccess(taskInfoList);
                }
                @Override
                public void onFailure(Object error) {
                    if (listener!=null){
                        listener.getInfFail("请求shibei");
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
