package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.domain.RecordDetail;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.constant.NetWorkHomeUrl;
import com.cxyz.homepage.imodel.MassageList_Model;
import com.cxyz.homepage.imodel.MassageList_ModelImpl;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public class MassageList_PresenterImpl extends MassageList_Presenter{

    @Override
    public void getMassageData(String User_id) {
        mIView.showLoadingView();
        mIModle.getMassageInfo(User_id,NetWorkHomeUrl.RDS_URL,new MassageList_Model.getMassageInfoListener() {
            @Override
            public void getInfoSuccess(List<RecordDetail> info) {
                if (info==null){
                    return;
                }else{
                    mIView.setListItem(info);
                }
            }
            @Override
            public void getInfFail(Object error) {
                ToastUtil.showShort("请求失败");
            }
        });
        mIView.hideLoadingView();
    }
    @Override
    public MassageList_Model createModel() {
        return new MassageList_ModelImpl();
    }
}
