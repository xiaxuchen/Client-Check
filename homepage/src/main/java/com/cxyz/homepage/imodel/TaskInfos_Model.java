package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public interface TaskInfos_Model extends IBaseModel{
    public void getTaskInfo(String id, String url, final TaskInfos_Model.getTaskInfoListener listener);
    public interface getTaskInfoListener{
        public void getInfoSuccess(List<TaskInfo> info);
        public void getInfFail(Object error);
    }
}
