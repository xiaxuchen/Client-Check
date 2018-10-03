package com.cxyz.homepage.feature_z_domain;

/**
 * Created by 鱼塘主 on 2018/9/27.
 * 存放excel的课程类
 */

public class Clazz {
    private String name ; //课程名
    private String type;//课程类型int( 公选课,必修课)
    private String id; //课程id
    private String teacher;//任课老师
    private String room;//上课教室
    private String week;//周几?
    private String starttime;//开始节数
    private String endtime;//结束节数
    private String startWeek;//第几周开始
    private String endWeek;//第几周结束
    private String status;//课程状态信息

    public Clazz(String name, String type, String id, String teacher, String room, String week, String starttime, String endtime, String startWeek, String endWeek, String status) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.teacher = teacher;
        this.room = room;
        this.week = week;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.status = status;
    }

    public String getRoom() {

        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
