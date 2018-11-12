package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.model.IDailyModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 夏旭晨 on 2018/10/5.
 */

public class IDailyModelImpl implements IDailyModel {
    @Override
   public void getStus(int grade, final GetStusListener listener) {

        //请求数据并回调listener
        RequestCenter.getStus(grade,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                if(listener!=null)
                {
                    try {
                        CheckResult<List<GradeStusDto>> checkResult = (CheckResult<List<GradeStusDto>>) GsonUtil.fromJson(responseObj.toString(),
                                new TypeToken<CheckResult<List<GradeStusDto>>>(){}.getType());
                        if(checkResult.isSuccess())
                            listener.onSuccess(checkResult.getData());
                        else
                            listener.onFail(checkResult.getError());
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
                    if(error instanceof OKHttpException)
                        listener.onFail(((OKHttpException) error).getMessage());
                    else if(error instanceof String)
                        listener.onFail(error.toString());
                    else
                        listener.onFail(null);
                }
            }
        });
    }

    @Override
    public void commit(CommitCheckDto commitCheckDto,final CommitListener listener) {
        //装配参数
        String commitCheck = null;
        try {
            commitCheck = GsonUtil.toJson(commitCheckDto);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFail("提交数据异常");
        }
        //发送请求
        RequestCenter.commitCheck(commitCheck,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> checkResult = (CheckResult<String>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<String>>(){}.getType());
                    if(checkResult.isSuccess())
                        listener.onCompletion(checkResult.getData());
                    else
                        listener.onFail(checkResult.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.onFail(error.toString());
                else
                    listener.onFail(((OKHttpException)error).getMessage());

            }
        });
    }
}
