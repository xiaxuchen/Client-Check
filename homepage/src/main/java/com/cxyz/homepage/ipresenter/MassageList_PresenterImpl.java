package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.imodel.MassageList_Model;
import com.cxyz.homepage.imodel.MassageList_ModelImpl;

import org.json.JSONObject;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public class MassageList_PresenterImpl extends MassageList_Presenter{

    @Override
    public void getMassageData(String User_id,String url) {
        mIView.showLoadingView();
        mIModle.getMassageInfo(User_id, url, new MassageList_Model.getMassageInfoListener() {
            @Override
            public void getInfoSuccess(JSONObject info) {
                if (info==null){
                    return;
                }else{
                    //JsonUtil.getJsonObjectValue(info,"",)
                   // JsonUtil
                }
            }

            @Override
            public void getInfFail(Object error) {
                ToastUtil.showShort("请求失败");
            }
        });

    }

    @Override
    public MassageList_Model createModel() {
        return new MassageList_ModelImpl();
    }
}
