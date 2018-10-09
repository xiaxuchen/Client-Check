package com.cxyz.logiccommons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class User {
	public static final int STUDNET = 0;
	public static final int TEACHER = 1;
	public static final int ERROR = 3;
	
	private String _id;//编号
    private String _name;//姓名
    private String sex;//性别
    private String pwd;//密码
    private String tel;//电话号码
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
     * 默认为普通学生权限
     */
    private Integer power = 0;
    /**
     * type属性用来区分学生和老师！
     * 0为学生
     * 1为老师
     * 默认是学生
     */
    private Integer type = 0;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	@Override
	public String toString() {
		return "User [id=" + _id + ", _name=" + _name + ", sex=" + sex
				+ ", pwd=" + pwd + ", tel=" + tel + ", photo=" + photo
				+ ", power=" + power
				+ ", type=" + type + "]";
	}
    
	
}


