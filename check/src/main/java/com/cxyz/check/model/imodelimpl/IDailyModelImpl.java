package com.cxyz.check.model.imodelimpl;

import android.accounts.NetworkErrorException;

import com.cxyz.check.constant.NetWorkConstant;
import com.cxyz.check.model.IDailyModel;
import com.cxyz.commons.domain.Check;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.commons.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/5.
 */

public class IDailyModelImpl implements IDailyModel {
    @Override
   public void getStus(int grade, final GetStusListener listener) {
        Map<String,String> map = new HashMap();
        map.put("method","getGradeStus");
        map.put("grade",grade+"");
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkConstant.GET_STUS,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    List<Student> stus = JsonUtil.jsonToListObject(responseObj.toString(),Student.class);
                    if(listener!=null)
                    {
                        if (stus == null) {
                            listener.onFail("获取资源错误！");
                            return;
                        }
                        else{
                            listener.onSuccess(stus);
                        }
                    }
                }

                @Override
                public void onFailure(Object error) {
                    if(listener!=null)
                    {
                        listener.onFail(error);
                    }
                }
            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFail("网络状态异常");
        }
    }

    @Override
    public void commit(Check check, final CommitListener listener) {
        String json = JsonUtil.objectToJson(check);
        Map<String,String> map = new HashMap<>();
        map.put("method","addRecords");
        map.put("check",json);
        RequestParams params = new RequestParams(map);
        try {
            CommonOkHttpClient.post(NetWorkConstant.COMMIT_URL,params,new DisposeDataHandler(new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    listener.onCompletion("提交成功！");
                }

                @Override
                public void onFailure(Object error) {
                    if(error instanceof String)
                        listener.onCompletion(error.toString());
                    else
                        listener.onCompletion(((OKHttpException)error).getMessage());

                }
            }));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            if(listener!=null)
                listener.onCompletion("网络状态异常");
        }
    }
}
