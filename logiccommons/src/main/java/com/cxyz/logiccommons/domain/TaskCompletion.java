package com.cxyz.logiccommons.domain;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.date.DateTime;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */
public class TaskCompletion{
	
	/**
	 * 待考勤
	 */
	public static final int WAIT_CHECK = 0;
	
	/**
	 * 未考勤
	 */
	public static final int NOT_CHECKED = -2;
	/**
	 * 正常考勤
	 */
	public static final int NORMAL = 1;
	/**
	 * 特殊情况
	 */
	public static final int OTHER = -1;
    private Integer _id;//考勤完成情况id（一次考勤）
    private TaskInfo taskInfo;//关联的基本信息
    private Date date;//考勤日期
    private Integer state;//考勤完成情况
    private DateTime updatetime;//上次更新时间
    
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
