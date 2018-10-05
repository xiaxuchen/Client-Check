package com.cxyz.untilchecked.ipresenter.ipresenterimpl;

import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.Teacher;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.manager.UserManager;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.JsonUtil;
import com.cxyz.untilchecked.imodel.ILoginModel;
import com.cxyz.untilchecked.imodel.imodelimpl.ILoginModelImpl;
import com.cxyz.untilchecked.ipresenter.ILoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class ILoginPresenterImpl extends ILoginPresenter{

    @Override
    public void login(String id, String pwd,int type) {
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
                    return;
                try {
                    if(info.getInt("type") == User.ERROR)
                    {
                        mIView.loginFail(info.getString("msg"));
                    }else if(info.getInt("type") == User.STUDNET)
                    {
                        Student stu = JsonUtil.jsonToObject(info.toString(), Student.class);
                        mIView.loginSuccess();
                        UserManager.getInstance().setUser(stu);
                    }else if(info.getInt("type") == User.TEACHER)
                    {
                        Teacher tea = JsonUtil.jsonToObject(info.toString(),Teacher.class);
                        mIView.loginSuccess();
                        UserManager.getInstance().setUser(tea);
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
