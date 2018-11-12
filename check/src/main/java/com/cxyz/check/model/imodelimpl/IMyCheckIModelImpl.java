package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.check.model.IMyCheckModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by 夏旭晨 on 2018/10/20.
 */

public class IMyCheckIModelImpl implements IMyCheckModel {

    @Override
    public void getCheckRecords(String id, int type, int grade, final getCheckRecordsListener listener) {
        RequestCenter.getGradeCheck(id, type, grade, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<CheckRecordDto> checkResult = (CheckResult<CheckRecordDto>) GsonUtil.fromJson(responseObj.toString(), new TypeToken<CheckResult<CheckRecordDto>>() {
                    }.getType());
                    if(checkResult.isSuccess())
                        listener.onSuccess(checkResult.getData());
                    else
                        listener.onFail(checkResult.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof OKHttpException)
                    listener.onFail(((OKHttpException) error).getMessage());
                else
                    listener.onFail(error.toString());
            }
        });
    }
}
