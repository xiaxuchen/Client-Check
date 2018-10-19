package com.cxyz.logiccommons.domain;

import com.cxyz.commons.date.DateTime;

/**
 * 统计信息实体
 * @author 夏旭晨
 *
 */
public class Statistic{
	/**
	 * 学生id
	 */
	private String stu_id;
	/**
	 * 请假次数
	 */
	private Integer _leave;
	/**
	 * 迟到次数
	 */
	private Integer late;
	/**
	 * 早退次数
	 */
	private Integer early_leave;
	/**
	 * 旷课次数
	 */
	private Integer truant;
	/**
	 * 总次数
	 */
	private Integer times;
	/**
	 * 上次更新时间
	 */
	private DateTime update;
	
	public String getStu_id() {
		return stu_id;
	}
	
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	
	public Integer get_leave() {
		return _leave;
	}
	
	public void set_leave(Integer _leave) {
		this._leave = _leave;
	}
	
	public Integer getLate() {
		return late;
	}
	
	public void setLate(Integer late) {
		this.late = late;
	}
	
	public Integer getEarly_leave() {
		return early_leave;
	}
	
	public void setEarly_leave(Integer early_leave) {
		this.early_leave = early_leave;
	}
	
	public Integer getTruant() {
		return truant;
	}
	
	public void setTruant(Integer truant) {
		this.truant = truant;
	}
	
	public DateTime getUpdate() {
		return update;
	}
	
	public void setUpdate(DateTime update) {
		this.update = update;
	}
	
	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "Statistic [stu_id=" + stu_id + ", _leave=" + _leave + ", late="
				+ late + ", early_leave=" + early_leave + ", truant=" + truant
				+ ", update=" + update + "]";
	}
	

}
