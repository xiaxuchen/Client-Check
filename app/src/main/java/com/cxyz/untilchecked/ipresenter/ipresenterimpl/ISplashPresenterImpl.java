package com.cxyz.untilchecked.ipresenter.ipresenterimpl;

import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.untilchecked.imodel.ISplashModel;
import com.cxyz.untilchecked.imodel.imodelimpl.ISplashModelImpl;
import com.cxyz.untilchecked.ipresenter.ISplashPresenter;

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
                    mIView.finishSplash();
                    return;
                }
                try {
                    //从json中获取版本信息，如果与当前版本相同则跳转到登录界面
                    String version = info.getString("version");
                    if(AppUtil.getVersionName(MyApp.getApplication()).equals(version))
                    {
                        mIView.hideLoadingView();
                        mIView.finishSplash();
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
    public ISplashModel createModel() {
        return new ISplashModelImpl();
    }
}
