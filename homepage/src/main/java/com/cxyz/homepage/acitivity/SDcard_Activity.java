package com.cxyz.homepage.acitivity;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class SDcard_Activity extends BaseActivity{
    @Override
    public int getContentViewId() {
        return R.layout.activity_sdcard;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Date date = new Date();
        date.setYear(2018).setMonth(10).setDay(19);
        RequestCenter.getTaskInfos(122, date, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                List<TaskInfo> recordDetails = GsonUtil.GsonToList(responseObj.toString(), TaskInfo.class);
                LogUtil.e(recordDetails.toString());
            }

            @Override
            public void onFailure(Object error) {
                LogUtil.e(error.toString());
            }
        });
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
