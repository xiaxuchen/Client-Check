package com.cxyz.logiccommons.domain;

import com.cxyz.commons.date.DateTime;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class UpdateRecord {
    private int _id;//所更改的记录的编号
    private Student student;//所更改的记录的所属学生
    private int result;//历史考勤结果
    private TaskCompletion taskCompletion;//关联的完成情况
    private User updater;//更新者
    private String _describe;//更新时的描述信息
    private DateTime update_time;//更新时间

    @Override
    public String toString() {
        return "UpdateRecord{" +
                "_id=" + _id +
                ", student=" + student +
                ", result=" + result +
                ", taskCompletion=" + taskCompletion +
                ", updater=" + updater +
                ", _describe='" + _describe + '\'' +
                ", update_time=" + update_time +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
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

    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public String get_describe() {
        return _describe;
    }

    public void set_describe(String _describe) {
        this._describe = _describe;
    }

    public DateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(DateTime update_time) {
        this.update_time = update_time;
    }
}
