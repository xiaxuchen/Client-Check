package com.cxyz.commons.autoupdate;

/**
 * Created by Administrator on 2018/10/10.
 */

public interface UpdateDownloadListener {
    public void onStarted();
    public void onProgressChanged(int progress, String downloadUrl);
    public void onFinished(float completeSize, String downloadUrl);
    public void onFailure();
}
