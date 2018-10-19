package com.cxyz.check.model.imodelimpl;

import android.accounts.NetworkErrorException;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.TaskInfo;

/**
 * Created by 夏旭晨 on 2018/10/18.
 */

public class ICheckModelImpl implements ICheckModel {
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
                        listener.onSuccess(GsonUtil.GsonToBean(responseObj.toString(), TaskInfo.class));
                    }
                }

                @Override
                public void onFailure(Object error) {
                    if(listener!=null)
                    {
                        if(error instanceof String)
                            listener.onFail(error.toString());
                        else if(error instanceof OKHttpException)
                            listener.onFail(((OKHttpException) error).getMessage());
                        else
                            listener.onFail("当前暂无考勤任务");
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
