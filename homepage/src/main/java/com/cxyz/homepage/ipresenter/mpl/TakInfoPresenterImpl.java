package com.cxyz.homepage.ipresenter.mpl;

import com.cxyz.homepage.imodel.TaskInfosModel;
import com.cxyz.homepage.imodel.TaskInfosModelImpl;
import com.cxyz.homepage.ipresenter.TaskInfoPresenter;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TakInfoPresenterImpl extends TaskInfoPresenter {

    @Override
    public void getTaskInfoData(String grede_id,String time) {
    }

    @Override
    public TaskInfosModel createModel() {
        return new TaskInfosModelImpl();
    }
}
