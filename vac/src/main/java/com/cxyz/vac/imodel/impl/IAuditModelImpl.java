package com.cxyz.vac.imodel.impl;

import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.vac.constant.RequestCenter;
import com.cxyz.vac.dto.VacateDto;
import com.cxyz.vac.imodel.IAuditModel;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2018/12/31.
 */

public class IAuditModelImpl extends IAuditModel {
    @Override
    public void getVacatesToAudit(ModelListener<List<VacateDto>, String> listener) {
        addCall(RequestCenter.getVacatesToAudit(UserManager.getInstance().getUser().getId(),null,new DisposeDataListener(){
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<List<VacateDto>> cr = (CheckResult<List<VacateDto>>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<List<VacateDto>>>(){}.getType());
                    if(cr.isSuccess())
                        listener.onSuccess(cr.getData());
                    else
                        listener.onFail(cr.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof Exception )
                    listener.onFail(((Exception) error).getMessage());
                else
                    listener.onFail(error.toString());
            }
        }));
    }

    @Override
    public void auditVacate(Integer id,Integer state,String info,Integer power,ModelListener<String,String> listener)
    {
        addCall(RequestCenter.auditVac(id, state, info, power, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<String> cr = (CheckResult<String>) GsonUtil.fromJson(responseObj.toString(),new TypeToken<CheckResult<String>>(){}.getType());
                    if(cr.isSuccess())
                        listener.onSuccess(cr.getData());
                    else
                        listener.onFail(cr.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof Exception )
                    listener.onFail(((Exception) error).getMessage());
                else
                    listener.onFail(error.toString());
            }
        }));
    }
}
