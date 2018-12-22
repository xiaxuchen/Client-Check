package com.cxyz.check.dto;

public class AlterRecordDto {

    /**
     * 学生信息
     */
    private GradeStusDto stu;

    /**
     * 考勤结果
     */
    private Integer result;

    /**
     * 描述信息
     */
    private String des;

    public GradeStusDto getStu() {
        return stu;
    }

    public void setStu(GradeStusDto stu) {
        this.stu = stu;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String
    toString() {
        return "AlterRecordDto{" +
                "stu=" + stu +
                ", result=" + result +
                ", des=" + des +
                '}';
    }
}
