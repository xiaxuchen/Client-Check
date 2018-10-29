package com.cxyz.logiccommons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class Grade {
	private Integer _id;//班级编号
	private String _name;//班级名称
	private College college;//所属学院
	private Teacher headTeacher;//班主任
	private ClassRoom classRoom;//晚自习教室
	
	public Grade(){}
	
	public Grade(Integer id) {
		set_id(id);
	}
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Teacher getHeadTeacher() {
		return headTeacher;
	}
	public void setHeadTeacher(Teacher headTeacher) {
		this.headTeacher = headTeacher;
	}
	public ClassRoom getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	@Override
	public String toString() {
		return "Grade [_name=" + _name + ", college=" + college
				+ ", headTeacher=" + headTeacher + ", classRoom=" + classRoom
				+ ", _id=" + _id + "]";
	}
}
