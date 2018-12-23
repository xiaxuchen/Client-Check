package com.cxyz.info.iview;

import com.cxyz.commons.IView.IBaseView;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public interface IUploadLessonView extends IBaseView {

    /**
     * 显示下载成功
     */
    void showSuccess(File file);

    /**
     * 更新进度
     * @param progress
     */
    void updateProgress(int progress);

    /**
     * 显示下载失败
     * @param error
     */
    void showFail(String error);

    /**
     * 上传成功
     * @param info
     */
    void UploadSuccess(Object info);

}
