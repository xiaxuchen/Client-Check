package com.cxyz.logiccommons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class Teacher extends User{
	
	
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
	
}
