package com.cxyz.mine.imodel;

import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.mine.constant.RequestCenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public class IChangePwdModelmpl implements IChangePwdModel {
    /**
     *
     * @param oldpwd  旧密码
     * @param listener  获取服务器上返回的信息后回调
     */
    public void getLoginInfo(final String oldpwd, final getChangeInfoListener listener)
    {
        /**
         * 装填参数
         */
        Map<String,String> map = new HashMap<>();
        map.put("mothod","testOldpwd");
        map.put("oldpwd",oldpwd);
        RequestParams params = new RequestParams(map);
        /**
         * 发送请求
         */
        RequestCenter.login(params,new DisposeDataListener() {

            @Override
            public void onSuccess(Object responseObj) {
                //成功则把数据传到逻辑层
                if(listener!=null)
                {
                   /* User user = null;
                    try {
                        user = (User) GsonUtil.fromJson(responseObj.toString(), User.class);
                        CheckResult<User> checkResult = (CheckResult<User>) GsonUtil.fromJson(responseObj.toString(),
                                new TypeToken<CheckResult<User>>(){}.getType());
                        if (checkResult.isSuccess())
                        {
                            User u = checkResult.getData();
                            listener.getInfoSuccess(u);

                        }else
                        {
                            listener.getInfoFail(checkResult.getError());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.getInfoFail("服务器异常");
                    }*/
                }

            }

            @Override
            public void onFailure(Object error) {
                //失败则将错误信息传到逻辑层
                if(listener==null)
                    return;
                if(error instanceof String)
                    listener.getInfoFail(error);
                else if(error instanceof OKHttpException)
                    listener.getInfoFail(((OKHttpException) error).getMessage());
                else
                    listener.getInfoFail("未知错误");
            }
        });
    }
}
