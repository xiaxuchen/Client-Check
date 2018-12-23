package com.cxyz.info.imodel;

import com.cxyz.commons.IModel.IBaseModel;

import java.io.File;

/**
 * Created by Administrator on 2018/12/17.
 */

public abstract class IUploadLessonModel extends IBaseModel {

    /**
     * 导入学生
     */
    public abstract void upload(File file, UploadListener listener);

    /**
     * 下载模板
     */
    public abstract void download(DownloadListener listener);

    public interface DownloadListener{

        /**
         * 传输成功
         * @param file
         */
        void onSuccess(File file);

        /**
         * 更新进度
         * @param progress
         */
        void updateProgress(int progress);

        /**
         * 传输失败
         * @param error
         */
        void onFail(String error);
    }

    public interface UploadListener{

        /**
         * 传输成功
         * @param info
         */
        void onSuccess(String info);

        /**
         * 传输失败
         * @param error
         */
        void onFail(String error);
    }
}
