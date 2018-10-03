package com.cxyz.commons.domain;

import com.cxyz.commons.Date;
import com.cxyz.commons.DateTime;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */
public class TaskCompletion{
	/**
	 * 正常考勤
	 */
	public static final int NORMAL = 0;
	/**
	 * 特殊情况
	 */
	public static final int OTHER = -1;
    private Integer _id;
    private TaskInfo taskInfo;
    private Date date;
    private Integer state;
    private DateTime updatetime;
    
    public TaskCompletion(){}
    
    public TaskCompletion(int id){
    	set_id(id);
    }
    
    public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public TaskInfo getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public DateTime getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(DateTime updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "TaskCompletion [_id=" + _id + ", taskInfo=" + taskInfo
				+ ", date=" + date + ", state=" + state + ", updatetime="
				+ updatetime + "]";
	}

	
}
