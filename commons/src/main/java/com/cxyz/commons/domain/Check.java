package com.cxyz.commons.domain;

import java.util.List;

/**
 * 用于装载考勤信息的bean<br/>
 * 考勤信息包括：<br/>
 * 1.考勤完成信息<br/>
 * 2.考勤记录信息<br/>
 * 3.特殊情况信息<br/>
 * 当考勤完成情况的状态为NORMAL时有考勤记录，为OTHER时有特殊情况信息<br/>
 * NORMAL和OTHER定义在TaskCompletion中<br/>
 * @author 夏旭晨
 *
 */
public class Check {
	/**
	 * 考勤完成情况信息
	 */
	private TaskCompletion completion;
	/**
	 * 考勤记录信息，可能有多条信息，所以用list
	 */
	private List<CheckRecord> records;
	/**
	 * 特殊情况信息，只有一条，当放假或者一些特殊情况时可以上报不上课原因
	 */
	private OtherState state;
	
	public TaskCompletion getCompletion() {
		return completion;
	}
	public void setCompletion(TaskCompletion completion) {
		this.completion = completion;
	}
	public List<CheckRecord> getRecords() {
		return records;
	}
	public void setRecords(List<CheckRecord> records) {
		this.records = records;
	}
	public OtherState getState() {
		return state;
	}
	public void setState(OtherState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "Check [completion=" + completion + ", record=" + records
				+ ", state=" + state+"]";
	}
	
}
