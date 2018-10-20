package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.imodel.MassageListModel;
import com.cxyz.homepage.imodel.MassageListModelImpl;
import com.cxyz.homepage.ipresenter.MassageListPresenter;
import com.cxyz.logiccommons.domain.RecordDetail;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public class MassageListPresenterImpl extends MassageListPresenter {

    @Override
    public void getMassageData(String User_id,Integer type) {
        mIView.showLoadingView();
        mIModle.getMassageInfo(User_id,1,new MassageListModel.getMassageInfoListener() {
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
    public MassageListModel createModel() {
        return new MassageListModelImpl();
    }
}
