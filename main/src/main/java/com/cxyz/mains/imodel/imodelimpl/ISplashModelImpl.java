package com.cxyz.mains.imodel.imodelimpl;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.mains.constant.NetWorkConstant;
import com.cxyz.mains.imodel.ISplashModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public class ISplashModelImpl implements ISplashModel {
    @Override
    public void confirmUpdate(final ConfirmListener listener) {
        try {
            CommonOkHttpClient.get(NetWorkConstant.UPDATE_URL,null,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    try {
                        JSONObject json = new JSONObject((String)responseObj);
                        listener.onUpdate(json);
                    }catch (JSONException e)
                    {
                        listener.onUpdate(null);
                    }
                }

                @Override
                public void onFailure(Object error) {
                    listener.onUpdate(null);
                }
            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onUpdate(null);
        }
    }

    @Override
    public void downloadNew(String url) {

    }
}
