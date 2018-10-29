package com.cxyz.logiccommons.manager.userManager;

/**
 * Created by xiaxuchen on 18-10-27.
 */

public class User {

    private String name;//姓名
    private String sex;//性别
    private String phone;//电话号码
    private String photo;//照片的url

    /**
     * power属性用来区分权限
     * 0为普通学生权限
     * 5为考勤人权限
     * 30为普通任课老师权限
     * 35为班主任权限
     * 45为系部管理员权限
     * 55为校级管理员权限
     * 100为超级管理员权限
     */
    private Integer power;

    /**
     * 该dto的类型
     */
    private Integer type;

    /**
     * dto为STUDENT和TEACHER时候拥有的学院名称
     */
    private String collegeName;
    /**
     * dto为TEACHER时候拥有的学院id
     */
    private Integer collegeId;

    /**
     * dto为Student时候拥有的班级id
     */
    private Integer gradeId;

    /**
     * dto为STUDENT是拥有的班级名称
     */
    private String gradeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", power=" + power +
                ", type=" + type +
                ", CollegeName='" + collegeName + '\'' +
                ", collegeId=" + collegeId +
                ", gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
