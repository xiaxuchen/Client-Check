package com.cxyz.homepage.bean;

/**
 * Created by ${喻济生} on 2018/11/18.
 */

public class CheckRecordBean {
    private String satrtime; //起始时间
    private String endtime;//结束时间
    private int late;//迟到人数
    private int absent;//缺席人数
    private int apply;//请假人数
    private int normal;//正常人数
    private String stuname;//异常情况学生姓名
    private String  stuid;//异常情况学生id
    private String stuclassname;//异常课程
    private String stutime;//异常时间

/*    public CheckRecordBean(String satrtime, String endtime, int late, int absent, int apply, int normal) {
        this.satrtime = satrtime;
        this.endtime = endtime;
        this.late = late;
        this.absent = absent;
        this.apply = apply;
        this.normal = normal;
    }*/

    public CheckRecordBean(String stuname, String stuid, String stuclassname, String stutime) {
        this.stuname = stuname;
        this.stuid = stuid;
        this.stuclassname = stuclassname;
        this.stutime = stutime;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuclassname() {
        return stuclassname;
    }

    public void setStuclassname(String stuclassname) {
        this.stuclassname = stuclassname;
    }

    public String getStutime() {
        return stutime;
    }

    public void setStutime(String stutime) {
        this.stutime = stutime;
    }

    public String getSatrtime() {
        return satrtime;
    }

    public void setSatrtime(String satrtime) {
        this.satrtime = satrtime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getApply() {
        return apply;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }
}
