package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.homepage.dto.GradeTaskDto;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public abstract class IExportModel extends IBaseModel {

    /**
     * 获取班级的课程信息
     * @param listener
     */
    public abstract void getGradeTasks(ModelListener<List<GradeTaskDto>,String> listener);


    /**
     * 获取统计Excel
     * @param gradeId 班级id
     * @param taskName 课程名
     * @param listener
     */
    public abstract void getStatisticExcel(Integer gradeId, String taskName,getExcelListener listener);


    public interface getExcelListener{

        /**
         * 进度
         * @param progress
         */
        void onProgress(int progress);

        /**
         * 下载成功
         * @param file
         */
        void onSuccess(File file);

        /**
         * 下载失败
         * @param error
         */
        void onFail(String error);
    }
}
