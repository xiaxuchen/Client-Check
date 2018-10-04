package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 老师实体
 */

public class Teacher extends User{
<<<<<<< HEAD
    public College college;
=======

	//更多信息看User
	
	private College college;//所属学院
	
	public Teacher(){}
	
	public Teacher(String _id)
	{
		this.set_id(_id);
	}
	
	public Teacher(String id,String pwd)
	{
		this.set_id(id);
		this.setPwd(pwd);
		this.setType(User.TEACHER);
	}
	
    public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}


	
    
>>>>>>> 65f779f9dc574c11711dafe67911761f9fe2410c
}
