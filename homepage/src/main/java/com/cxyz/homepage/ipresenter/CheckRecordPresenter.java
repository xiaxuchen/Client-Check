package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.homepage.imodel.CheckRecordModel;
import com.cxyz.homepage.view.CheckRecordView;

/**
 * Created by ${喻济生} on 2018/12/19.
 */

public abstract class CheckRecordPresenter extends IBasePresenter <CheckRecordModel,CheckRecordView>{
 public  abstract  void getRecord(String start,String end,Integer gradeID);
 public  abstract  void getStasticRecord(String start,String end,Integer gradeID,Integer typeResult);
}
