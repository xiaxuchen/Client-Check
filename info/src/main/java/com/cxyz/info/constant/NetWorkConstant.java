package com.cxyz.info.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by Administrator on 2018/12/17.
 */

public interface NetWorkConstant {

    /**
     * 导入学生
     */
    String UPLOAD_STU = Constant.ROOT_URL+"/user/addStus";

    /**
     * 下载学生模板
     */
    String DOWNLOAD_STU_TEMPLATE = Constant.ROOT_URL+"/resource/stuTemplate";

    /**
     * 导入学生
     */
    String UPLOAD_LESSON = Constant.ROOT_URL+"/task/addTask";

    /**
     * 下载课程模板
     */
    String DOWNLOAD_LESSON_TEMPLATE = Constant.ROOT_URL+"/resource/lessonTemplate";
}
