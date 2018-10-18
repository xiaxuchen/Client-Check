package com.cxyz.homepage.imodel;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.JsonUtil;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.NetWorkHomeUrl;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TaskInfos_ModelImpl implements TaskInfos_Model{
    @Override
    public void getTaskInfo(String grede_id, String url, final getTaskInfoListener listener) {
        Map<String,String> map = new HashMap<>();
        map.put("method","getTaskInfos");
        map.put("grade",grede_id);
        RequestParams params = new RequestParams(map);
        LogUtil.e("goudongxi"+NetWorkHomeUrl.TASKINFOS_URL);
        try {
            CommonOkHttpClient.post(NetWorkHomeUrl.TASKINFOS_URL,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {

                    LogUtil.e(responseObj.toString());//测试

                    List<TaskInfo> taskInfoList = JsonUtil.jsonToListObject(responseObj.toString(),TaskInfo.class);
                    LogUtil.e("xiachedan"+taskInfoList.toString());
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
