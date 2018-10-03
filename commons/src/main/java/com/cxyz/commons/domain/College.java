package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class College {
	
	private Integer _id;
    private String _name;
    private School school;
    private Teacher manager;
    
    public College(){}
    
    public College(Integer id){
    	_id = id;
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
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public Teacher getManager() {
		return manager;
	}
	public void setManager(Teacher manager) {
		this.manager = manager;
	}
	@Override
	public String toString() {
		return "College [_id=" + _id + ", _name=" + _name + ", school="
				+ school + ", manager=" + manager + "]";
	}
}
