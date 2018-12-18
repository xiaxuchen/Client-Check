package com.cxyz.mine.constant;

import com.cxyz.logiccommons.constant.Constant;

/**
 * Created by 夏旭晨 on 2018/9/30.
 */

public class NetWorkConstant {

    public static final String ROOT_URL = "http:/119.29.101.171:8080/Service_Check/servlet";

    public static final String LOGIN_URL = ROOT_URL+"/UserServlet";

    //获取app最新版本信息
    public static final String UPDATE_URL = Constant.ROOT_URL+"/resource/updateApp";

    //下载apk文件
    public static final String GETAPP = Constant.ROOT_URL+"/resource/getApp";

}
