package com.cxyz.logiccommons.domain;



import com.cxyz.commons.date.Date;
import com.cxyz.commons.date.DateTime;

import java.util.Arrays;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class TaskInfo{
    private String _id;
	private String _name;
    private User sponser = new User();
    private User checker = new User();
    private DateTime start;
    private DateTime end;
    private ClassRoom classRoom = new ClassRoom();
    private Integer type;
    private Grade grade = new Grade();
    /*
     * 所有的考勤日期
     */
    private Date[] dates;
    public DateTime getEnd() {
		return end;
	}

	public void setEnd(DateTime end) {
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


	@Override
	public String toString() {
		return "TaskInfo [_id=" + _id + ", _name=" + _name + ", sponser="
				+ sponser + ", checker=" + checker + ", start=" + start
				+ ",  end=" + end + ", classRoom=" + classRoom
				+ ", type=" + type + ", grade=" + grade + ", dates="
				+ Arrays.toString(dates) + "]";
	}


	
}
