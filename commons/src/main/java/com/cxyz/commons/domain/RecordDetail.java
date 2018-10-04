package com.cxyz.commons.domain;

/**
 * 考勤详细信息<br/>
 * @author 夏旭晨
 *
 */
public class RecordDetail {
	/**
	 * 考勤记录信息
	 */
	private CheckRecord cr;
	/**
	 * 考勤任务信息
	 */
	private TaskInfo ti;
	public CheckRecord getCr() {
		return cr;
	}
	public void setCr(CheckRecord cr) {
		this.cr = cr;
	}
	public TaskInfo getTi() {
		return ti;
	}
	public void setTi(TaskInfo ti) {
		this.ti = ti;
	}
	@Override
	public String toString() {
		return "RecordDetail [cr=" + cr + ", ti=" + ti + "]";
	}
}
