package com.cxyz.commons.domain;

import com.cxyz.commons.DateTime;

/**
 * 考勤详细信息<br/>
 * @author 夏旭晨
 *
 */
public class RecordDetail {
	//完成情况id
	private int _id;
	//考勤发起人，一般为课任老师
	private User sponsor; 
	//考勤人，一般为副班长
	private User checker;
	//考勤时间
	private DateTime checkTime;
	//课程名
	private String _name;
	/**
	 *
	 */
	//考勤结果
	private int result;
	//情况描述
	private String des;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public User getSponsor() {
		return sponsor;
	}
	public void setSponsor(User sponsor) {
		this.sponsor = sponsor;
	}
	public User getChecker() {
		return checker;
	}
	public void setChecker(User checker) {
		this.checker = checker;
	}
	public DateTime getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(DateTime checkTime) {
		this.checkTime = checkTime;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "RecordDetail [_id=" + _id + ", sponsor=" + sponsor
				+ ", checker=" + checker + ", checkTime=" + checkTime
				+ ", _name=" + _name + ", result=" + result + ", des=" + des
				+ "]";
	}
	
}
