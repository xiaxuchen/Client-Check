package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.constant.NetWorkHomeUrl;
import com.cxyz.homepage.imodel.TaskInfos_Model;
import com.cxyz.homepage.imodel.TaskInfos_ModelImpl;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TakInfo_PresenterImpl extends TaskInfo_Presenter{

    @Override
    public void getTaskInfoData(String grede_id) {
        mIView.showLoadingView();
        mIModle.getTaskInfo(grede_id, NetWorkHomeUrl.TASK_URL, new TaskInfos_Model.getTaskInfoListener() {
            @Override
            public void getInfoSuccess(List<TaskInfo> info) {
                if (info==null){
                    return;
                }else{
                    mIView.setTaskInfosData(info);
                }

            }
            @Override
            public void getInfFail(Object error) {
                ToastUtil.showShort("TaskInfos请求失败");
            }
        });
        mIView.hideLoadingView();
    }

    @Override
    public TaskInfos_Model createModel() {
        return new TaskInfos_ModelImpl();
    }
}
