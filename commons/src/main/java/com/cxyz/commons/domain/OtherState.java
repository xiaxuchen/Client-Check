package com.cxyz.commons.domain;


/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class OtherState {
    private String des;
    private TaskCompletion taskCompletion;
	public TaskCompletion getTaskCompletion() {
		return taskCompletion;
	}
	public void setTaskCompletion(TaskCompletion taskCompletion) {
		this.taskCompletion = taskCompletion;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "OtherState [des=" + des + ", taskCompletion=" + taskCompletion
				+ "]";
	}
}
