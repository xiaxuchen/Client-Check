package com.cxyz.check.dto;

import java.util.List;

/**
 * Created by Administrator on 2018/11/10.
 * 提交考勤信息的Dto
 */

public class CommitCheckDto {

    private List<StuInfo> stuInfos;//违规学生信息

    private int taskId;//任务id

    private int state;//任务完成情况

    private String des;//特殊情况的描述

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<StuInfo> getStuInfos() {
        return stuInfos;
    }

    public void setStuInfos(List<StuInfo> stuInfos) {
        this.stuInfos = stuInfos;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "CommitCheckDto{" +
                "stuInfos=" + stuInfos +
                ", taskId=" + taskId +
                ", state=" + state +
                '}';
    }

    public static class StuInfo implements Cloneable{
        private String id;//学号
        private int result;//考勤结果
        private String des;//描述

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        @Override
        public String toString() {
            return "StuInfo{" +
                    "id='" + id + '\'' +
                    ", result=" + result +
                    ", des='" + des + '\'' +
                    '}';
        }

        @Override
        public StuInfo clone() throws CloneNotSupportedException {
            return (StuInfo)super.clone();
        }
    }

}