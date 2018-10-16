package com.cxyz.mains.ipresenter.ipresenterimpl;

import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.JsonUtil;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.logiccommons.domain.Student;
import com.cxyz.logiccommons.domain.Teacher;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mains.imodel.ILoginModel;
import com.cxyz.mains.imodel.imodelimpl.ILoginModelImpl;
import com.cxyz.mains.ipresenter.ILoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class ILoginPresenterImpl extends ILoginPresenter{

    @Override
    public void login(final String id, final String pwd, final int type) {
        String msg = this.checkout(id,pwd);
        //判断数据是否合法
        if(msg != null)
        {
            //不合法直接显示错误信息并停止执行
            mIView.loginFail(msg);
            return;
        }
        mIView.showLoadingView();
        //合法则去服务器取数据
        mIModle.getLoginInfo(id, pwd, type, new ILoginModel.getLoginInfoListener() {
            @Override
            public void getInfoSuccess(JSONObject info) {
                //获取数据后进行判断
                if(info == null)
                {
                    mIView.loginFail("服务器异常");
                    return;
                }
                try {
                    if(info.getInt("type") == User.ERROR)
                    {
                        //显示登录失败
                        mIView.loginFail(info.getString("msg"));
                        return;
                    }else
                    {
                        User user = null;
                        if(info.getInt("type") == User.STUDNET)
                        {
                            user = JsonUtil.jsonToObject(info.toString(), Student.class);
                        }else if(info.getInt("type") == User.TEACHER)
                        {
                            user = JsonUtil.jsonToObject(info.toString(),Teacher.class);
                        }

                        //把用户数据保存到UserManager
                        UserManager.getInstance().setUser(user);

                        //把用户的用户名和密码保存到sp中
                        SpUtil.getInstance().putString("username",id)
                                .putInt("type",type);
                        SpUtil.getInstance().putString("pwd",pwd);
                        //显示登录成功
                        mIView.loginSuccess();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mIView.hideLoadingView();
            }

            @Override
            public void getInfoFail(Object error) {
                if(error instanceof OKHttpException)
                    mIView.loginFail(((OKHttpException)error).getMessage());
                else{
                    mIView.loginFail(error.toString());
                }
                mIView.hideLoadingView();
            }

        });

    }

    /**
     * 检查账号密码是否合法
     * @param id 账号
     * @param pwd 密码
     * @return
     */
    private String checkout(String id, String pwd)
    {
        if(id.isEmpty())
            return "账号不能为空！";
        if(id.length()!=8)
            return "账号长度应为8位！";
        if(pwd.isEmpty())
            return "密码不能为空！";
        if(id.length()<6)
            return "密码不能少于6位！";
        return null;
    }

    @Override
    public ILoginModel createModel() {
        return new ILoginModelImpl();
    }
}
