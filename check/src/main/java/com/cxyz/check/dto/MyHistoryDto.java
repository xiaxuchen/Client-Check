package com.cxyz.check.dto;

import java.sql.Timestamp;

public class MyHistoryDto {

    //考勤课程
    private String subject;

    //考勤结果
    private Integer result;

    //描述信息
    private String des;

    //考勤时间
    private Timestamp time;

    //考勤完成情况id
    private Integer compId;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    @Override
    public String toString() {
        return "MyHistoryDto{" +
                "subject='" + subject + '\'' +
                ", result=" + result +
                ", des='" + des + '\'' +
                ", time=" + time +
                ", compId=" + compId +
                '}';
    }
}
