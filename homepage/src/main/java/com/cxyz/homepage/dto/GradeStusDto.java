package com.cxyz.homepage.dto;

/**
 * Created by Administrator on 2018/11/10.
 * 获取学生信息列表
 */

//学生信息

public class GradeStusDto{
    private String name;//学生姓名
    private String id;//学生学号
    private String photo;//学生照片路径

    public GradeStusDto(){};

    public GradeStusDto(String id){this.id = id;}

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "GradeStusDto{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}