package com.cxyz.check.model.imodelimpl;

import com.cxyz.check.constant.RequestCenter;
import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.check.model.IHistoryModel;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.logiccommons.domain.CheckResult;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class IHistoryModelImpl extends IHistoryModel {
    @Override
    public void getHistory(GetHistoryListener listener) {
        User u = UserManager.getInstance().getUser();
        RequestCenter.getHistory(u.getId(), u.getType(), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<List<CheckHistoryDto>> checkResult =
                            (CheckResult<List<CheckHistoryDto>>) GsonUtil.
                                    fromJson(responseObj.toString(),new TypeToken
                                            <CheckResult<List<CheckHistoryDto>>>(){}.getType());
                    if(checkResult.isSuccess())
                        listener.getHistorySuccess(checkResult.getData());
                    else
                        listener.getHistoryFail(checkResult.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.getHistoryFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.getHistoryFail(error.toString());
                else if(error instanceof OKHttpException)
                    listener.getHistoryFail(((OKHttpException) error).getMessage());
                else
                    listener.getHistoryFail("未知错误");
            }
        });
    }

    @Override
    public void loadMoreHistory(int start,LoadMoreHistoryListener listener) {
        User u = UserManager.getInstance().getUser();
        RequestCenter.loadMore(u.getId(), u.getType(),start,new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                try {
                    CheckResult<List<CheckHistoryDto>> checkResult =
                            (CheckResult<List<CheckHistoryDto>>) GsonUtil.
                                    fromJson(responseObj.toString(),new TypeToken
                                            <CheckResult<List<CheckHistoryDto>>>(){}.getType());
                    if(checkResult.isSuccess())
                        listener.LoadMoreSuccess(checkResult.getData());
                    else
                        listener.LoadMoreFail(checkResult.getError());
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.LoadMoreFail("服务器异常");
                }
            }

            @Override
            public void onFailure(Object error) {
                if(error instanceof String)
                    listener.LoadMoreFail(error.toString());
                else if(error instanceof OKHttpException)
                    listener.LoadMoreFail(((OKHttpException) error).getMessage());
                else
                    listener.LoadMoreFail("未知错误");
            }
        });
    }
}
