package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.model.IOtherstateModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/12/15.
 */

public class IOtherstateModelImpl extends IOtherstateModel {
    @Override
    public void commitOtherstate(CommitCheckDto dto, CommitListener listener) {
        String json;
        try {
            json = GsonUtil.toJson(dto);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFail("未知错误");
            return;
        }

        RequestCenter.commitCheck(json, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> checkResult = (CheckResult<String>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<String>>(){}.getType());
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
                if(error instanceof Exception)
                    listener.onFail(((Exception) error).getMessage());
                else
                    listener.onFail(error.toString());
            }
        });
    }
}
