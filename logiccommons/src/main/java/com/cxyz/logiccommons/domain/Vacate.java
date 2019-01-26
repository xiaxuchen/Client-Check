package com.cxyz.logiccommons.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * 请假表
 */
public class Vacate {

    private Integer id;//请假id

    private Timestamp start;//开始时间

    private Timestamp end;//结束时间

    private String des;//请假事由

    private User sponsor;//申请人

    private Integer len;//请假天数

    private Integer type;//请假类型

    private Timestamp sponsorTime;//发起时间

    private List<Audit> audits;//审核信息

    public Vacate(){}

    public Vacate(Integer id){
        setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getSponsorTime() {
        return sponsorTime;
    }

    public void setSponsorTime(Timestamp sponsorTime) {
        this.sponsorTime = sponsorTime;
    }

    public List<Audit> getAudits() {
        return audits;
    }

    public void setAudits(List<Audit> audits) {
        this.audits = audits;
    }

    @Override
    public String toString() {
        return "Vacate{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", des='" + des + '\'' +
                ", sponsor=" + sponsor +
                ", len=" + len +
                ", type=" + type +
                ", sponsorTime=" + sponsorTime +
                ", audits=" + audits +
                '}';
    }
}
