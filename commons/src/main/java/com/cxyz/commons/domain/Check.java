package com.cxyz.commons.domain;

import java.util.List;

public class Check {
	
	private TaskCompletion completion;
	private List<CheckRecord> records;
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
