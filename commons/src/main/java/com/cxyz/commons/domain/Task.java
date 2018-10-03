package com.cxyz.commons.domain;

import java.util.List;

/**
 * 在服务器进行传输的中间Task对象
 * @author 夏旭晨
 *
 */
public class Task {
	private TaskInfo info;
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
