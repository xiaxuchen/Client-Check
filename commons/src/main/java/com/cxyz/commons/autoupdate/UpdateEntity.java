package com.cxyz.commons.autoupdate;

/**
 * Created by 夏旭晨 on 2018/10/21.
 */

public class UpdateEntity {
    //更新包的连接
    private String url;
    //更新包的版本号
    private int versionCode;
    //描述信息
    private String[] des;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String[] getDes() {
        return des;
    }

    public void setDes(String[] des) {
        this.des = des;
    }


}
