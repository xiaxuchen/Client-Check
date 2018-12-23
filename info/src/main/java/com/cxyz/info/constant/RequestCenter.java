package com.cxyz.info.constant;

import android.accounts.NetworkErrorException;
import android.os.Environment;

import com.cxyz.commons.utils.HttpUtil.CommonOkHttpClient;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataHandler;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDownLoadListener;
import com.cxyz.commons.utils.HttpUtil.request.RequestParams;
import com.cxyz.logiccommons.manager.UserManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2018/12/17.
 */

public class RequestCenter {

    /**
     * 下载导入学生模板
     * @param URL 地址
     * @param map
     * @param listener
     * @return
     */
    public static okhttp3.Call DownloadTemplate(String URL,String fileName,Map<String,String> map, DisposeDownLoadListener listener)
    {
        try {
            return CommonOkHttpClient.getFile(URL,new RequestParams(map)
                    ,new DisposeDataHandler(listener,
                    Environment.getExternalStorageDirectory().getPath()+"/data/"+fileName));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }
        return null;
    }

    /**
     * 上传
     * @param URL 地址
     * @param type 类型
     * @param file 文件
     * @param listener
     * @return
     */
    public static Call Upload(String URL,Integer type,File file, DisposeDataListener listener)
    {
        Map<String,String> map = new HashMap<>();
        map.put("type",type+"");
        map.put("gradeId", UserManager.getInstance().getUser().getGradeId()+"");
        RequestParams params = new RequestParams(map);
        params.put("file",file);
        params.put("type",type);
        params.put("gradeId",UserManager.getInstance().getUser().getGradeId());
        try {
            return CommonOkHttpClient.uploadFile(URL,params,new DisposeDataHandler(listener));
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            listener.onFailure("网络状态异常");
        }

        return null;
    }
}
