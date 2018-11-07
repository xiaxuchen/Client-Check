package com.cxyz.homepage.imodel.impl;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.imodel.IHomeModel;
import com.cxyz.logiccommons.domain.TaskInfo;

import org.json.JSONException;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class IHomeModelImpl implements IHomeModel {
    @Override
    public void checkComp(String id, int type, final CheckListener listener){

        /**
         * 请求网络获取数据，处理数据后给逻辑层
         */
        try {
            RequestCenter.checkComp(id, type, new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    if(listener!=null)
                    {
                        LogUtil.e(responseObj.toString());
                        try {
                            listener.onSuccess((TaskInfo) GsonUtil.fromJson(responseObj.toString(), TaskInfo.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail("服务器异常");
                        }
                    }
                }

                @Override
                public void onFailure(Object error) {
                    if(listener!=null)
                    {
                        if(error instanceof String)
                            listener.onFail(error.toString());
                        else if(error instanceof OKHttpException)
                        {
                            if(((OKHttpException) error).getCode() == OKHttpException.EMPTY)
                                listener.onFail("当前暂无考勤任务");
                            else
                                listener.onFail(((OKHttpException) error).getMessage());
                        }
                    }
                }
            });
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            if(listener!=null)
                listener.onFail("网络状态异常");
        }
    }
}
