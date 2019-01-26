package com.cxyz.mine.constant;

import android.accounts.NetworkErrorException;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by 夏旭晨 on 2018/10/14.
 */

public class RequestCenter {

    /**
     * 修改密码
     * @param id 用户id
     * @param type 用户类型
     * @param originPwd 原密码
     * @param newPwd 新密码
     * @param listener
     * @return
     */
    public static Call alterPwd(String id, Integer type, String originPwd, String newPwd, DisposeDataListener listener)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("type",type+"");
        map.put("originPwd",originPwd);
        map.put("newPwd",newPwd);
        try {
            return CommonOkHttpClient.post(NetWorkConstant.ALTER_PWD,new RequestParams(map),new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }

        return null;
    }
}
