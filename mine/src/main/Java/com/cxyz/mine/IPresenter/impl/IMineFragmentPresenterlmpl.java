package com.cxyz.mine.IPresenter.impl;

import com.cxyz.commons.autoupdate.UpdateEntity;
import com.cxyz.commons.context.ContextManager;
import com.cxyz.commons.utils.AppUtil;
import com.cxyz.mine.IPresenter.IMineFragmentPresenter;
import com.cxyz.mine.constant.NetWorkConstant;
import com.cxyz.mine.imodel.IMineFragmentModel;
import com.cxyz.mine.imodel.impl.IMineFragmentModelmpl;

/**
 * Created by Administrator on 2018/10/22.
 */

public  class IMineFragmentPresenterlmpl extends IMineFragmentPresenter {

    @Override
    public void Update() {
        mIModle.confirmUpdate(new IMineFragmentModel.ConfirmListener() {
            @Override
            public void onUpdate(UpdateEntity info) {
                if(info == null){
                    mIView.noUpdate();
                    return;
                }

                //从json中获取版本信息，如果与当前版本相同则toast
                if(AppUtil.getVersionCode(ContextManager.getContext())== info.getVersionCode())
                {
                    mIView.noUpdate();
                    return ;
                }
                StringBuilder builder = new StringBuilder();
                for(String des:info.getDes())
                {
                    builder.append(des+"\n");
                }
                //版本号不一致则获取app的url和最新版本的描述信息
                mIView.showUpdateView(info.getVersionCode(),builder.toString(), NetWorkConstant.GETAPP);

            }

            @Override
            public void onFail(String error) {
                mIView.noUpdate();
            }
        });
    }

    @Override
    public IMineFragmentModel createModel() {
        return new IMineFragmentModelmpl();
    }
}


