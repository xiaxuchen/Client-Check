package com.cxyz.mains.imodel.imodelimpl;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.autoupdate.UpdateEntity;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.mains.constant.NetWorkConstant;
import com.cxyz.mains.imodel.ISplashModel;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public class ISplashModelImpl extends ISplashModel {
    @Override
    public void confirmUpdate(final ConfirmListener listener) {
        try {
            CommonOkHttpClient.get(NetWorkConstant.UPDATE_URL,null,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    if(listener != null)
                        try {
                            listener.onUpdate((UpdateEntity) GsonUtil.fromJson(responseObj.toString(), new TypeToken<UpdateEntity>(){}.getType()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onFail("服务器异常");
                        }
                }

                @Override
                public void onFailure(Object error) {
                    listener.onUpdate(null);
                }
            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFail(null);
        }
    }

}
