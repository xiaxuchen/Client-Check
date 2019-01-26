package com.cxyz.logiccommons.domain;


import java.sql.Timestamp;

public class Audit {
    //记录编号
    private Integer id;
    //审核人
    private User checker;
    //审核状态
    private Integer state;
    //上次更新时间
    private Timestamp updateTime;
    //请假信息
    private Vacate vac;

    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Vacate getVac() {
        return vac;
    }

    public void setVac(Vacate vac) {
        this.vac = vac;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", checker=" + checker +
                ", state=" + state +
                ", updateTime=" + updateTime +
                ", vac=" + vac +
                ", info='" + info + '\'' +
                '}';
    }
}
