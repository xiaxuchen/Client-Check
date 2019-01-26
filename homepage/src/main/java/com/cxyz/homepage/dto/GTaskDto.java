package com.cxyz.homepage.dto;

/**
 * GradeTaskDto中的考勤任务
 */
public class GTaskDto {

    private Integer id;//任务id

    private String name;//课程名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GTaskDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
