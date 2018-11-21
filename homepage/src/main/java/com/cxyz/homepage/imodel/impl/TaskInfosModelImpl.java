package com.cxyz.homepage.imodel.impl;

import com.cxyz.homepage.imodel.TaskInfosModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class TaskInfosModelImpl implements TaskInfosModel {
    @Override
    public void getTaskInfo(String grede_id, String time, final getTaskInfoListener listener) {
        Map map = new HashMap();
        map.put("mothod","getTasks");
        map.put("id",grede_id);
        map.put("time",time+"");

    }
}
