package com.cxyz.check.dto;

import com.cxyz.logiccommons.dto.ResultCustom;

import java.util.List;

/**
 * Created by Administrator on 2018/11/11.
 */

public class CheckRecordDto {

    //总考勤次数
    private int all;

    private List<ResultCustom> results;//考勤结果


    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public List<ResultCustom> getResults() {
        return results;
    }

    public void setResults(List<ResultCustom> results) {
        this.results = results;
    }

    /**
     * 获取type类型的count
     * @param type
     * @return
     */
    public int getTypeCount(int type)
    {
        if(results == null || results.isEmpty())
            return 0;
        for(ResultCustom rc:results)
        {
            if(rc.getResultType() == type)
                return rc.getCount();
        }
        return 0;
    }

    /**
     * 获取不良记录条数
     * @return
     */
    public int getBadCount()
    {
        int count = 0;
        for(ResultCustom rc:results)
        {
           count+=rc.getCount();
        }
        return count;
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

    @Override
    public String toString() {
        return "CheckRecordDto{" +
                "all=" + all +
                ", results=" + results +
                '}';
    }
}
