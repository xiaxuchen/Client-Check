package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.RecordDetail;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public interface MassageList_Model extends IBaseModel{
    public void getMassageInfo(String id,String url,final getMassageInfoListener listener);
    public interface getMassageInfoListener{
        public void getInfoSuccess(List<RecordDetail> info);
        public void getInfFail(Object error);
    }

}
