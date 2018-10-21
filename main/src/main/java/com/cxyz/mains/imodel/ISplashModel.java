package com.cxyz.mains.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.autoupdate.UpdateEntity;

import java.io.File;

/**
 * Created by 夏旭晨 on 2018/10/2.
 */

public interface ISplashModel extends IBaseModel {

    /**
     * 确认是否可更新
     * @return
     */
    public void confirmUpdate(ConfirmListener listener);


    /**
     * 确认更新的回调
     */
    interface ConfirmListener{
        /**
         * 成功时的回调
         * @param updateEntity 更新信息
         */
        void onUpdate(UpdateEntity updateEntity);

        /**
         * 失败时的回调
         * @param error
         */
        void onFail(String error);
    }

    /**
     * 下载的回调监听
     */
    interface DownloadListener{
        /**
         * 下载完成
         * @param f 下载的文件
         */
        void onComlete(File f);

        /**
         * 下载失败
         * @param error 错误信息
         */
        void onFail(Object error);

        /**
         * 更新下载进度
         * @param progress 当前进度
         * @param max 最大进度
         */
        void onProgress(int progress,int max);
    };
}
