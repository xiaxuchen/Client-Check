package com.cxyz.homepage.bean;

/**
 * Created by ${喻济生} on 2018/12/29.
 */

public class AppointListBean {
    private String name;
    private String id;
    private String create;
    private String check;
    private String time;
    private String reason;
    private String manager;
    private String rank;
    private String advice;
    private String state;
    private int image;

    public AppointListBean(String name, String id, String create, String check, String time,
                           String reason, String manager, String rank, String advice, String state, int image) {
        this.name = name;
        this.id = id;
        this.create = create;
        this.check = check;
        this.time = time;
        this.reason = reason;
        this.manager = manager;
        this.rank = rank;
        this.advice = advice;
        this.state = state;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
