package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.date.Date;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public interface TaskInfos_Model extends IBaseModel{
    /**
     * 获取相应日期的考勤任务
     * @param grade_id
     * @param date
     * @param listener
     */
    public void getTaskInfo(int grade_id, Date date, final getTaskInfoListener listener);

    public interface getTaskInfoListener{
        public void getInfoSuccess(List<TaskInfo> info);
        public void getInfFail(Object error);
    }
}
