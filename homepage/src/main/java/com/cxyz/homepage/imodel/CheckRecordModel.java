package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.homepage.dto.StatisticDto;
import com.cxyz.homepage.dto.StatisticRecordDto;

import java.util.List;

/**
 * Created by ${喻济生} on 2018/12/19.
 */

public abstract class CheckRecordModel extends IBaseModel {
    /**
     *
     * @param start 开始时间
     * @param end   结束时间
     * @param gradeID  班级id
     */
    public abstract void getRecord(String start, String end, Integer gradeID, getRecordListener listener) ;
    public abstract void getStasticRecord(String start, String end, Integer gradeID, Integer typeResult, getStasticRecordListener listener) ;

    public interface getRecordListener{

        /**
         * 请求成功
         */
        void onSuccess(StatisticDto statisticDto);

        /**
         * 请求失败
         */
        void onFail(String error);
    }
    public interface getStasticRecordListener{

        /**
         * 请求成功
         */
        void onSuccess(List<StatisticRecordDto> statisticRecordDto);

        /**
         * 请求失败
         */
        void onFail(String error);
    }
}
