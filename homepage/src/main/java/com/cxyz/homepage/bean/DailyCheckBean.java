package com.cxyz.homepage.bean;

/**
 * Created by Administrator on 2018/10/29.
 */

public class DailyCheckBean {
    private int image;
    private  String name;
    private String code;
    private  String time ;
    private  String state;

    public DailyCheckBean(String name , String code, String time, /*String state,*/ int image){
        this.name=name;
        this.code=code;
        this.time=time;
        this.state=state;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
