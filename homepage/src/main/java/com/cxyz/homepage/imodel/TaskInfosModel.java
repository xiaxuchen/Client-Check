package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public interface TaskInfosModel extends IBaseModel{
    public void getTaskInfo(String _id, String url, final getTaskInfoListener listener);
    public interface getTaskInfoListener{
        public void getInfoSuccess(List<TaskInfo> info);
        public void getInfFail(Object error);
    }
}
