package com.cxyz.logiccommons.domain;


/**
 * 特殊情况信息
 * Created by 夏旭晨 on 2018/9/23.
 */

public class OtherState {
	/**
	 * 对特殊情况的描述
	 */
    private String des;
    /**
     * 对应的完成情况，一般只需要id
     */
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
