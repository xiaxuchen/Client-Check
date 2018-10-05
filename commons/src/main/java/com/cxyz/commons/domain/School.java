package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 学校实体
 */

public class School {
<<<<<<< HEAD
    public int _id;
    public String _name;
    public Teacher manager;
=======
	
	private String _name;//学校姓名
    private Teacher manager;//校部管理员
    private Integer _id;//学校编号
    
    public School(){}
    
    public School(Integer id) {
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
	public Teacher getManager() {
		return manager;
	}
	public void setManager(Teacher manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "School [_name=" + _name + ", manager=" + manager + ", _id="
				+ _id + "]";
	}
	
>>>>>>> 65f779f9dc574c11711dafe67911761f9fe2410c
}
