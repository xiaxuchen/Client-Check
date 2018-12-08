package com.cxyz.check.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class CheckHistoryDto {

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 考勤结果的状态
     */
    private Integer state;
    /**
     * 考勤结果
     */
    private List<RecordResultCustom> results;

    /**
     * 提交时间
     */
    private Timestamp commitTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }

    public List<RecordResultCustom> getResults() {
        return results;
    }

    public void setResults(List<RecordResultCustom> results) {
        this.results = results;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CheckHistoryDto{" +
                "taskName='" + taskName + '\'' +
                ", state=" + state +
                ", results=" + results +
                ", commitTime=" + commitTime +
                '}';
    }

    /**
     * 存储结果的数量
     */
    public static class RecordResultCustom{
        private Integer resultType;

        private Integer count;

        public Integer getResultType() {
            return resultType;
        }

        public void setResultType(Integer resultType) {
            this.resultType = resultType;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "resultType=" + resultType +
                    ", count=" + count +
                    '}';
        }
    }
}
