package com.cxyz.homepage.acitivity;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.logiccommons.domain.CheckRecord;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class SDcardActivity extends BaseActivity{
    @Override
    public int getContentViewId() {
        return R.layout.activity_sdcard;
    }

    @Override
    public void initView() {
        RequestCenter.getRecords("17478093", CheckRecord.ALL, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                ToastUtil.showShort(responseObj.toString());
            }

            @Override
            public void onFailure(Object error) {
                ToastUtil.showShort(error.toString());
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
