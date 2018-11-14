package com.cxyz.homepage.dto;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/11/10.
 * 检查当前是否有任务的dto
 */
public class CheckTaskDto {

    private int id;//任务id

    private String name;//任务名称

    private String sponsorName;//发起者名称

    private Timestamp start;//开始时间

    private Timestamp end;//结束时间

    private String spot;//上课地点

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CheckTaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsorName='" + sponsorName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", spot='" + spot + '\'' +
                '}';
    }
}
