package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 学生实体
 */

<<<<<<< HEAD
public class Student extends User {
    public Grade grade;
=======
public class Student extends User{

	//更多信息在User中

	private Grade grade;//所属班级
	
	private String college_name;//学院名称
	
	public Student(){}
	
	public Student(String id){
		this.set_id(id);
	}
	
	public Student(String id,String pwd)
	{
		this.set_id(id);
		this.setPwd(pwd);
		this.setType(User.STUDNET);
	}
	
    public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

>>>>>>> 65f779f9dc574c11711dafe67911761f9fe2410c
}
