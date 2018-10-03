package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class Teacher extends User{
	
	private College college;
	
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
