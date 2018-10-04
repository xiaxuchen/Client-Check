package com.cxyz.commons.domain;

import java.util.Date;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 一个考勤完成情况就是一次考勤
 */
<<<<<<< HEAD
=======
public class TaskCompletion{
	/**
	 * 正常考勤
	 */
	public static final int NORMAL = 0;
	/**
	 * 特殊情况
	 */
	public static final int OTHER = -1;
    private Integer _id;//考勤完成情况id
    private TaskInfo taskInfo;//所属任务id
    private Date date;//考勤日期
    private Integer state;//完成情况
    private DateTime updatetime;//更新时间
    
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
>>>>>>> 65f779f9dc574c11711dafe67911761f9fe2410c

public class TaskCompletion {
    public int _id;
    public TaskInfo taskInfo;
    public Date _date;
    public int state;
    public Date update_time;
}
