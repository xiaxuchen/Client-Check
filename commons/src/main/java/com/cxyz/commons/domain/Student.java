package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 学生实体
 */

public class Student extends User{
	private Grade grade;
	
	private String college_name;
	
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

}
