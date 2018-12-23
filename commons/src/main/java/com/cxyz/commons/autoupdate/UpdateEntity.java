package com.cxyz.commons.autoupdate;

import java.util.Arrays;

/**
 * Created by 夏旭晨 on 2018/10/21.
 */

public class UpdateEntity {
    //更新包的版本号
    private int versionCode;
    //描述信息
    private String[] des;

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

    @Override
    public String toString() {
        return "UpdateEntity{" +
                "versionCode=" + versionCode +
                ", des=" + Arrays.toString(des) +
                '}';
    }
}
