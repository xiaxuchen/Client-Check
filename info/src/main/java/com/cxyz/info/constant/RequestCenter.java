package com.cxyz.info.constant;

import android.accounts.NetworkErrorException;
import android.os.Environment;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.logiccommons.typevalue.UserType;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/12/17.
 */

public class RequestCenter {

    /**
     * 下载导入学生模板
     * @param map
     * @param listener
     * @return
     */
    public static okhttp3.Call DownloadTemplate(Map<String,String> map, DisposeDownLoadListener listener)
    {
        try {
            return CommonOkHttpClient.getFile(NetWorkConstant.DOWNLOAD_TEMPLATE,new RequestParams(map)
                    ,new DisposeDataHandler(listener,
                    Environment.getExternalStorageDirectory().getPath()+"/data/template.xlsx"));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    public static Call UploadStus(File file, DisposeDataListener listener)
    {
        RequestParams params = new RequestParams();
        params.put("gradeId", com.cxyz.logiccommons.manager.UserManager.getInstance().getUser().getGradeId());
        params.put("type", UserType.STUDENT);
        params.put("file",file);
        try {
            return CommonOkHttpClient.uploadFile(NetWorkConstant.UPLOAD_STU,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }

        return null;
    }
}
