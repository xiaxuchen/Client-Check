package com.cxyz.check.dto;

import java.sql.Timestamp;
import java.util.Date;

//考勤详细信息
public class RecordInfoDto {
    //课程名
    private String name;
    //描述信息
    private String des;
    //考勤结果
    private int result;
    //考勤时间
    private Timestamp date;
    //完成情况id
    private int compId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    @Override
    public String toString() {
        return "RecordInfoDto{" +
                "des='" + des + '\'' +
                ", result=" + result +
                ", date=" + date +
                ", compId=" + compId +
                '}';
    }
}