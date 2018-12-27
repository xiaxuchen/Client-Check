package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.check.model.IMyHistoryModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class IMyHistoryModelImpl extends IMyHistoryModel {
    @Override
    public void getHistory(Integer result,ModelListener<List<MyHistoryDto>,String> listener) {
       loadMoreHistory(result,0,listener);
    }

    @Override
    public void loadMoreHistory(Integer result,Integer start,ModelListener<List<MyHistoryDto>,String> listener) {
        User u = UserManager.getInstance().getUser();
        RequestCenter.getMyHistory(u.getId(),result,start,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                LogUtil.e(responseObj.toString());
                try {
                    CheckResult<List<MyHistoryDto>> checkResult =
                            (CheckResult<List<MyHistoryDto>>) GsonUtil.
                                    fromJson(responseObj.toString(),new TypeToken
                                            <CheckResult<List<MyHistoryDto>>>(){}.getType());
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
                if(error instanceof String)
                    listener.onFail(error.toString());
                else if(error instanceof OKHttpException)
                    listener.onFail(((OKHttpException) error).getMessage());
                else
                    listener.onFail("未知错误");
            }
        });
    }
}
