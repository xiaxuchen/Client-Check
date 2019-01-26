package com.cxyz.homepage.dto;

import java.util.Date;

/**
 * Created by ${喻济生} on 2018/12/8.
 */

public class TaskRecordDto {

    private Date starttime;//开始时间
    private Date endtime;//结束时间
    private String classname;//班级名称
    private int normal=40;//正常人数
    private int absent=2;//缺勤人数
    private int apply=3;//请假人数
    private int late=4;//迟到人数
    private String absentname="张三 李四  王二麻子哦";//缺勤人
    private String applyname="嘿嘿额计划  哦哦哦";//请假人
    private String latename="谭张梅陈";//迟到人

/*    public TaskRecordDto(Date starttime, Date endtime, String classname, int normal, int absent, int apply, int late, String absentname, String applyname, String latename) {
        this.starttime = starttime;
        this.endtime = endtime;
        this.classname = classname;
        this.normal = normal;
        this.absent = absent;
        this.apply = apply;
        this.late = late;
        this.absentname = absentname;
        this.applyname = applyname;
        this.latename = latename;
    }*/

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
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

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public String getAbsentname() {
        return absentname;
    }

    public void setAbsentname(String absentname) {
        this.absentname = absentname;
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname;
    }

    public String getLatename() {
        return latename;
    }

    public void setLatename(String latename) {
        this.latename = latename;
    }

    public String toString(){
        return "TaskRecordDto{" +
                "starttime=" + starttime +
                ", endtime=" + endtime +
                ", classname=" + classname +
                ", normal=" + normal +
                ", absent=" + absent +
                ", apply='" + apply +
                ",late=" + late +
                ", absentname=" + absentname +
                ", applyname=" + applyname +
                ", latename=" + latename +
                '}';
    }
}
