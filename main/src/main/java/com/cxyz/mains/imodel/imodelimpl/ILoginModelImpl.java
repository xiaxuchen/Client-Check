package com.cxyz.mains.imodel.imodelimpl;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.mains.constant.NetWorkConstant;
import com.cxyz.mains.imodel.ILoginModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class ILoginModelImpl implements ILoginModel{

    /**
     *
     * @param id  用户id
     * @param pwd 用户输入的密码
     * @param type 用户类型
     * @param listener  获取服务器上返回的信息后回调
     */
    public void getLoginInfo(String id, String pwd, int type, final getLoginInfoListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("method","login");
        map.put("id",id);
        map.put("pwd",pwd);
        map.put("type",String.valueOf(type));
        RequestParams params = new RequestParams(map);
        LogUtil.e(NetWorkConstant.LOGIN_URL);
        try {
            CommonOkHttpClient.post(NetWorkConstant.LOGIN_URL,params,new DisposeDataHandler(new DisposeDataListener() {

                @Override
                public void onSuccess(Object responseObj) {
                    try {
                        if(listener!=null)
                            listener.getInfoSuccess(new JSONObject((String)responseObj));
                    }catch (Exception e){
                        if(listener!=null)
                            listener.getInfoFail("JSON转化错误");
                    }
                }

                @Override
                public void onFailure(Object error) {
                    if(listener!=null)
                        listener.getInfoFail(error);
                }
            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            if(listener!=null)
                listener.getInfoFail("网络状态异常");
        }
    }

}
