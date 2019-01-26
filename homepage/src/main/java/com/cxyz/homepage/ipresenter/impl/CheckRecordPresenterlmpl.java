package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.homepage.dto.StatisticDto;
import com.cxyz.homepage.dto.StatisticRecordDto;
import com.cxyz.homepage.imodel.CheckRecordModel;
import com.cxyz.homepage.imodel.impl.CheckRecordModellmpl;
import com.cxyz.homepage.ipresenter.CheckRecordPresenter;

import java.util.List;

/**
 * Created by ${喻济生} on 2018/12/19.
 */

public class CheckRecordPresenterlmpl extends CheckRecordPresenter {

    @Override
    public CheckRecordModel createModel() {
        return new CheckRecordModellmpl();
    }


    @Override
    public void getRecord(String start, String end, Integer gradeID) {
        mIModle.getRecord(start, end, gradeID, new CheckRecordModel.getRecordListener() {
            @Override
            public void onSuccess(StatisticDto statisticDto) {
                mIView.setStatisticData(statisticDto);
            }
            @Override
            public void onFail(String error) {
                mIView.showFail();
            }
        });


    }

    @Override
    public void getStasticRecord(String start, String end, Integer gradeID, Integer typeResult) {
        mIModle.getStasticRecord(start, end, gradeID, typeResult,new CheckRecordModel.getStasticRecordListener() {
            @Override
            public void onSuccess(List<StatisticRecordDto> statisticRecordDto) {
                mIView.setStatisticRecordData( statisticRecordDto);
            }
            @Override
            public void onFail(String error) {
                mIView.showFail();
            }
        });
    }
}
