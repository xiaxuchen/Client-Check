package com.cxyz.mine.bean;

/**
 * Created by Administrator on 2018/10/28.
 */

public class AppointmentInfoBean {
    private int image;
    private  String name;
    private String code;
    private  String time ;
    private  String reason;
    private  String moreinfo;



    public AppointmentInfoBean(String name , String code, String time, String reason, String moreinfo, int image){
       this.name=name;
       this.code=code;
       this.time=time;
       this.reason=reason;
       this.moreinfo=moreinfo;
       this.image=image;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMoreinfo() {
        return moreinfo;
    }
    public void setMoreinfo(String moreinfo) {
        this.moreinfo = moreinfo;
    }
}
