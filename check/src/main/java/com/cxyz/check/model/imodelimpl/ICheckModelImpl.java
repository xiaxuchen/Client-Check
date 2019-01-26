package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.CheckTaskDto;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/12/3.
 */

public class ICheckModelImpl extends ICheckModel {

    @Override
    public void checkComp(final String checkerId, int checkerType, int type, final CheckListener listener) {

        /**
         * 请求网络获取数据，处理数据后给逻辑层
         */
        addCall(RequestCenter.checkComp(checkerId, checkerType,type, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                if(listener!=null)
                {
                    LogUtil.e(responseObj.toString());
                    try {
                        CheckResult<CheckTaskDto> checkResult = (CheckResult<CheckTaskDto>) GsonUtil.
                                fromJson(responseObj.toString(),
                                        new TypeToken<CheckResult<CheckTaskDto>>() {
                                        }.getType());
                        if(checkResult.isSuccess())
                        {
                            listener.onSuccess(checkResult.getData());
                        }else {
                            listener.onFail(checkResult.getError());
                        }
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
        }));
    }
}
