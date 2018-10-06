package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.domain.TaskInfo;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public interface TaskInfosPagerView extends IBaseView{
    void setTaskInfosData(List<TaskInfo> taskInfosData);
}
