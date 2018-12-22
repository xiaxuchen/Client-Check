package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.AlterRecordDto;
import com.cxyz.check.model.IAlterModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */

public class IAlterModelImpl extends IAlterModel {
    @Override
    public void getAlterRecords(int compId, int gradeId,getAlterRecordsListener listener) {
        RequestCenter.getAlterRecords(compId, gradeId, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<List<AlterRecordDto>> checkResult = (CheckResult<List<AlterRecordDto>>)
                            GsonUtil.fromJson(responseObj.toString(), new TypeToken<CheckResult<List
                                    <AlterRecordDto>>>() {}.getType());
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

    @Override
    public void updateRecords(int compId, List<AlterRecordDto> dtos, updateRecordsListener listener) {
        User u = UserManager.getInstance().getUser();
        RequestCenter.updateAlteds(compId, dtos, u.getId(),u.getType(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> checkResult = (CheckResult<String>) GsonUtil.
                            fromJson(responseObj.toString(), new TypeToken<CheckResult<String>>() {
                    }.getType());
                    if(checkResult.isSuccess())
                        listener.onSuccess(checkResult.getData());
                    else
                        listener.onFail(checkResult.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("数据异常");
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
