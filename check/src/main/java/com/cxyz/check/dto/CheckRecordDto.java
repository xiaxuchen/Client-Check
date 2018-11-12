package com.cxyz.check.dto;

import com.cxyz.logiccommons.domain.CheckRecord;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11.
 */

public class CheckRecordDto {

    //总考勤次数
    private int all;

    //请假
    private int vacate;

    //迟到
    private int late;

    //缺勤
    private int absenteeism;

    //早退
    private int earlyeave;

    //考勤详情
    private List<RecordInfo> recordInfos;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getVacate() {
        return vacate;
    }

    public int getLate() {
        return late;
    }

    public int getAbsenteeism() {
        return absenteeism;
    }

    public int getEarlyeave() {
        return earlyeave;
    }

    /**
     * 获取不良记录条数
     * @return
     */
    public int getBadCount()
    {
        return vacate+absenteeism+late+earlyeave;
    }

    /**
     * 获取出勤率
     * @return
     */
    public int getProgress()
    {
        int progress;
        try {
            progress = (all-getBadCount())*100/all;
        }catch (Exception e)
        {
            return 0;
        }

        return progress;
    }

    public List<RecordInfo> getRecordInfos() {
        return recordInfos;
    }

    public void setRecordInfos(List<RecordInfo> recordInfos) {
        this.recordInfos = recordInfos;
        reInit();
        for(RecordInfo ri:recordInfos)
        {
            switch (ri.getResult()) {
                case CheckRecord.ABSENTEEISM: {
                    absenteeism++;
                }
                break;
                case CheckRecord.EARLYLEAVE: {
                    earlyeave++;
                }
                break;
                case CheckRecord.LATE: {
                    late++;
                }
                break;
                case CheckRecord.VACATE: {
                    vacate++;
                }
                break;
            }
        }
    }

    //重置
    private void reInit()
    {
        late = 0;
        vacate = 0;
        absenteeism = 0;
        earlyeave = 0;
    }

    //考勤详细信息
    public static class RecordInfo{
        //课程名
        private String name;
        //描述信息
        private String des;
        //考勤结果
        private int result;
        //考勤时间
        private Timestamp date;
        //完成情况id
        private int compId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Timestamp date) {
            this.date = date;
        }

        public int getCompId() {
            return compId;
        }

        public void setCompId(int compId) {
            this.compId = compId;
        }

        @Override
        public String toString() {
            return "RecordInfo{" +
                    "des='" + des + '\'' +
                    ", result=" + result +
                    ", date=" + date +
                    ", compId=" + compId +
                    '}';
        }
    }
}
