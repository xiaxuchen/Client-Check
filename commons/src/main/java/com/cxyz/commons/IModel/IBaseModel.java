package com.cxyz.commons.IModel;

import java.util.ArrayList;
import java.util.Iterator;
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
        if(call != null)
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
        Iterator<Call> iterator = calls.iterator();
        while(iterator.hasNext())synchronized (calls){
            Call call = iterator.next();
            if (!call.isCanceled())
                call.cancel();
            iterator.remove();   //注意这个地方
        }
    }

    /**
     * 通用的请求回调
     * @param <D>
     * @param <E>
     */
    public interface ModelListener<D,E>{

        /**
         * 请求成功时的回调
         * @param data 数据
         */
        void onSuccess(D data);

        /**
         * 请求失败时的回调
         * @param e 错误
         */
        void onFail(E e);
    }


}
