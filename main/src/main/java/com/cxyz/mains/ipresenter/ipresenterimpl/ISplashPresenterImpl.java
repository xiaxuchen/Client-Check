package com.cxyz.mains.ipresenter.ipresenterimpl;

import com.cxyz.commons.utils.AppUtil;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.SpUtil;
import com.cxyz.logiccommons.application.MyApp;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.mains.imodel.ILoginModel;
import com.cxyz.mains.imodel.ISplashModel;
import com.cxyz.mains.imodel.imodelimpl.ILoginModelImpl;
import com.cxyz.mains.imodel.imodelimpl.ISplashModelImpl;
import com.cxyz.mains.ipresenter.ISplashPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public class ISplashPresenterImpl extends ISplashPresenter {
    @Override
    public void Update() {
        mIView.showLoadingView();
        mIModle.confirmUpdate(new ISplashModel.ConfirmListener() {
            @Override
            public void onUpdate(JSONObject info) {
                if(info == null)
                {
                    mIView.hideLoadingView();
                    return;
                }
                try {
                    //从json中获取版本信息，如果与当前版本相同则跳转到登录界面
                    String version = info.getString("version");
                    if(AppUtil.getVersionName(MyApp.getApplication()).equals(version))
                    {
                        mIView.hideLoadingView();
                        return;
                    }
                    //版本号不一致则获取app的url和最新版本的描述信息
                    String url = info.getString("url");
                    JSONArray jsonArray = info.getJSONArray("des");
                    String des = new String();
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        des+=jsonArray.getString(i)+"\n";
                    }
                    mIView.hideLoadingView();
                    mIView.showUpdateView(version,des,url);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mIView.hideLoadingView();
                }
            }
        });
    }

    @Override
    public void download(String url) {

    }

    @Override
    public void autoLogin() {
        //从sp中获取用户信息
        final String username = SpUtil.getInstance().getString("username", "");
        final String pwd = SpUtil.getInstance().getString("pwd", "");
        final int type = SpUtil.getInstance().getInt("type",-2);
        LogUtil.e(username);
        LogUtil.e(pwd);
        LogUtil.e(type+"");
        //判断是否在sp中保存完整
        if(username!=""&&pwd!=""&&type!=-2)
        {
            //如果完整则登录
            new ILoginModelImpl().getLoginInfo(username, pwd, type, new ILoginModel.getLoginInfoListener() {
                @Override
                public void getInfoSuccess(User user) {
                    //把用户数据保存到UserManager
                    UserManager.getInstance().setUser(user);
                    //显示登录成功
                    mIView.autoLoginSuccess();
                    mIView.hideLoadingView();
                }

                @Override
                public void getInfoFail(Object error) {
                    if(error instanceof OKHttpException)
                        mIView.autoLoginFail("自动登录异常:"+((OKHttpException) error).getMessage());
                    else if(error instanceof String)
                        mIView.autoLoginFail("自动登录异常:"+error.toString());
                    else
                        mIView.autoLoginFail("");
                }

            });
        }else{
            mIView.exitSplash();
        }


    }

    @Override
    public ISplashModel createModel() {
        return new ISplashModelImpl();
    }
}
