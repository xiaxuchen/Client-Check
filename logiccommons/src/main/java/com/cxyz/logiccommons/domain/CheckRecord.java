package com.cxyz.logiccommons.domain;

/**
 * 考勤记录信息<br/>
 * 
 * Created by 夏旭晨 on 2018/9/23.
 */
public class CheckRecord{
	/**
	 * 请假
	 */
	public static final int VACATE = -1;
	/**
	 * 早退
	 */
	public static final int EARLYLEAVE = -2;
	/**
	 * 迟到
	 */
	public static final int LATE = -3;
	/**
	 * 缺勤
	 */
	public static final int ABSENTEEISM = -4;
	
	/**
	 * 撤销
	 */
	public static final int CANCLE = -5;

	/**
	 * 所有违规记录
	 */
	public static final int ALL = 0;
	/**
	 * 考勤记录的id
	 */
    private int _id;
    /**
     * 所属学生
     */
    private User student;
    /**
     * 考勤结果
     */
    private int result;
    /**
     * 完成情况，一般只需要装填id
     */
    private TaskCompletion taskCompletion;
    /**
     * 考勤描述信息
     */
    private String des;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
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
		return "CheckRecord [_id=" + _id + ", student=" + student + ", result="
				+ result + ", taskCompletion=" + taskCompletion + "des="+des+"]";
	}
	
	

}
