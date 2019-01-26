package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.homepage.dto.GradeTaskDto;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public interface IExportView extends IBaseView {

    /**
     * 显示班级和任务名
     * @param gradeTaskDtos
     */
    void showSpinner(List<GradeTaskDto> gradeTaskDtos);

    /**
     * 显示错误信息
     * @param error
     */
    void showError(String error);

    /**
     * 下载成功
     * @param file 下载好的文件
     */
    void downloadSuccess(File file);

    /**
     * 下载失败
     * @param error 错误信息
     */
    void downloadFail(String error);

    /**
     * 进度更新
     * @param progress
     */
    void onProgress(int progress);
}
