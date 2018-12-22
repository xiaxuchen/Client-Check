package com.cxyz.homepage.view;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.homepage.dto.CheckTaskDto;
import com.cxyz.homepage.dto.GradeDto;
import com.cxyz.homepage.dto.StatisticDto;
import com.cxyz.homepage.dto.StatisticRecordDto;

/**
 * Created by ${喻济生} on 2018/12/19.
 */

public interface CheckRecordView extends IBaseView {
    public  void setCheckTaskData(CheckTaskDto CheckTaskData);
    public  void setGradeData(GradeDto gradeData);
    public  void setStatisticData(StatisticDto statisticData);
    public  void setStatisticRecordData(StatisticRecordDto statisticRecordData);
    public  void showFail();


}
