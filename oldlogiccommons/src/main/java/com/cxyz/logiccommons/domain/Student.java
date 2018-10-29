package com.cxyz.logiccommons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 学生实体
 */

public class Student extends User{
	
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
	
   

}
