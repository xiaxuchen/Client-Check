package com.cxyz.homepage.doMain;

/**
 * Created by 鱼塘主 on 2018/9/25.
 */

public class Stu {
    private String id;
    private String name;
    private String password;
    private String sex;
    private String email;
    private String head_img_url;
    private String clazz;
    private String teacher;
    private String gg;//本学期缺勤(gg)次数

    public Stu(String id,String name, String password, String sex, String email, String head_img_url, String clazz, String teacher, String gg) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.head_img_url = head_img_url;
        this.clazz = clazz;
        this.teacher = teacher;
        this.gg = gg;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
