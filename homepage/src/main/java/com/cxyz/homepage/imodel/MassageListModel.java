package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.logiccommons.domain.RecordDetail;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/3.
 */

public abstract class MassageListModel extends IBaseModel{
    /**
     * 获取当天课表信息
     * @param id 班级id
     * @param type 筛选不同类型的违规记录，为null则获取全部类型
     * @param listener
     */
    public abstract void getMassageInfo(String id, Integer type, final getMassageInfoListener listener);

    public interface getMassageInfoListener{
        void getInfoSuccess(List<RecordDetail> info);
        void getInfFail(Object error);
    }

}
