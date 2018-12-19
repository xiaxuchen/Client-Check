package com.cxyz.commons.IModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 所有IModel的祖宗接口，所有的IModel都要继承此接口
 */
public abstract class IBaseModel {

    private List<Call> calls = new ArrayList<>();

    /**
     * 添加call
     * @param call
     */
    public void addCall(Call call)
    {
        calls.add(call);
    }

    /**
     * 移除call
     * @param call
     */
    public void removeCall(Call call)
    {
        calls.remove(call);
    }


    /**
     * 清空所有的call，并取消请求
     */
    public void ClearCalls()
    {
        for(Call call:calls)
        {
            if (!call.isCanceled())
                call.cancel();
            calls.remove(call);
        }
    }


}
