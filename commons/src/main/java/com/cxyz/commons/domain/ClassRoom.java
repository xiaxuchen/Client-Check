package com.cxyz.commons.domain;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class ClassRoom {
    private Integer _id;
    private String _name;
    private College college;
    private Integer state;
    
    public ClassRoom(){}
    
    public ClassRoom(Integer id){
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
	public College getCollege() {
		return college;
	}
	
	public void setCollege(College c)
	{
		college = c;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ClassRoom [_id=" + _id + ", _name=" + _name + ", college="
				+ college + ", state=" + state + "]";
	}
    
}
