package com.cxyz.commons.domain;

import java.util.List;

/**
 * 在服务器进行传输的中间任务对象<br/>
 * 任务包括：<br/>
 * 1.任务基本信息<br/>
 * 2.任务完成情况<br/>
 * @author 夏旭晨
 *
 */
public class Task {
	/**
	 * 任务基本信息
	 */
	private TaskInfo info;
	/**
	 * 任务完成情况
	 */
	private List<TaskCompletion> tcs;
	public TaskInfo getInfo() {
		return info;
	}
	public void setInfo(TaskInfo info) {
		this.info = info;
	}
	public List<TaskCompletion> getTcs() {
		return tcs;
	}
	public void setTcs(List<TaskCompletion> tcs) {
		this.tcs = tcs;
	}
	
	@Override
	public String toString() {
		return info.toString()+tcs.toString();
	}
}
