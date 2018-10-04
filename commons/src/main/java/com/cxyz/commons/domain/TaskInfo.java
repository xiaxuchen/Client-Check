package com.cxyz.commons.domain;

import java.util.Date;

/**
 * Created by 夏旭晨 on 2018/9/23.
 * 考勤任务基本信息
 */

public class TaskInfo {
<<<<<<< HEAD
    public int _id;
    public String _name;
    public User sponser;
    public Student checker;
    public Date startTime;
    public Date time_len;
    public ClassRoom classRoom;
    public String describe;
=======
    private String _id;//考勤任务编号
	private String _name;//考勤任务名称
    private User sponser = new User();//考勤任务发起人
    private User checker = new User();//考勤任务考勤人
    private DateTime start;//考勤开始时间
    private DateTime len;//考勤时限
    private Date end;//最后一次的考勤日期(预留)
    private ClassRoom classRoom = new ClassRoom();//考勤所在地
    private Integer type;//考勤任务类型，临时任务或者课程
    private Grade grade = new Grade();//考勤班级
    /*
     * 所有的考勤日期
     */
    private Date[] dates;
    public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

    
    public TaskInfo(){}
    
    public TaskInfo(String _id)
    {
    	this.set_id(_id);
    }
    
    public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public User getSponser() {
		return sponser;
	}
	public void setSponser(User sponser) {
		this.sponser = sponser;
	}
	public User getChecker() {
		return checker;
	}
	public void setChecker(User checker) {
		this.checker = checker;
	}
	public ClassRoom getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}


	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public DateTime getLen() {
		return len;
	}

	public void setLen(DateTime len) {
		this.len = len;
	}

	@Override
	public String toString() {
		return "TaskInfo [_id=" + _id + ", _name=" + _name + ", sponser="
				+ sponser + ", checker=" + checker + ", start=" + start
				+ ", len=" + len + ", end=" + end + ", classRoom=" + classRoom
				+ ", type=" + type + ", grade=" + grade + ", dates="
				+ Arrays.toString(dates) + "]";
	}


	
>>>>>>> 65f779f9dc574c11711dafe67911761f9fe2410c
}
