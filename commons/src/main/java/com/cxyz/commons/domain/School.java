package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class School {
	
	private String _name;
    private Teacher manager;
    private Integer _id;
    
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
	
}
