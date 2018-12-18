package com.cxyz.homepage.dto;

public class GradeDto {
    //班级id
    private Integer id;

    //班级名称
    private String name;

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
        return "GradeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
