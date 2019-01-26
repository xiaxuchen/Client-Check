package com.cxyz.homepage.dto;

import java.util.List;

public class GradeTaskDto {

    private String gradeName;

    private Integer gradeId;

    private List<GTaskDto> tasks;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public List<GTaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<GTaskDto> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "GradeTaskDto{" +
                "gradeName='" + gradeName + '\'' +
                ", gradeId=" + gradeId +
                ", tasks=" + tasks +
                '}';
    }
}
