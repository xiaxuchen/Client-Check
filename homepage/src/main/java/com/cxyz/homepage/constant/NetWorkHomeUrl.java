package com.cxyz.homepage.constant;

/**
 * Created by 鱼塘主 on 2018/10/6.
 */

public class NetWorkHomeUrl {
    public final static String ROOT_URL = "http://192.168.43.73:8080/Service_Check";

    public static final String RDS_URL = ROOT_URL+"/servlet/CheckServlet";

    /**
     * 请求获取所有的任务信息
     */
    public static final String TASKINFOS_URL = ROOT_URL+"/servlet/CheckServlet";

    /**
     * 检查是否有考勤任务
     */
    public static final String CHECKCOMP_URL = ROOT_URL+"/servlet/CheckServlet";

}
