package com.cxyz.untilchecked.ipresenter.ipresenterimpl;

import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;
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

    /**
     * 登录
     * @param id
     * @param pwd
     * @param type
     */
    @Override
    public void login(String id, String pwd,int type) {
        mIModle.getLoginInfo(id, pwd, type, new ILoginModel.getLoginInfoListener() {
            @Override
            public void getInfo(JSONObject info) {
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public ILoginModel createModel() {
        return new ILoginModelImpl();
    }
}
