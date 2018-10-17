package com.cxyz.logiccommons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 老师实体
 */

public class Teacher extends User{

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


	
    
}
