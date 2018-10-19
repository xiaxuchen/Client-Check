package com.cxyz.homepage.feature_z_domain;

/**
 * Created by 鱼塘主 on 2018/10/16.
 */

public class TestTask {
    private String name;
    private String reson;
    private String time;
    private int type;
    private int stu_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    /**
     *
     * @param name
     * @param reson
     * @param time
     * @param type
     */
    public TestTask(String name, String reson, String time, int type) {
        this.name = name;
        this.reson = reson;
        this.time = time;
        this.type = type;
    }

    public TestTask(){

    }
}
