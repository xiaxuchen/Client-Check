package com.cxyz.homepage.imodel;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.date.Date;
import com.cxyz.homepage.myAdapter.cell.MessageCell;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/18.
 */

public interface MessagesModel extends IBaseModel {
    /**
     *
     * @param _id 学号
     * @param time 日期
     * @param listtener
     */
    public  void getMessagesJson(String _id, Date time,final getMessagesInfo listtener);
    public interface getMessagesInfo {
        public void getInfoSuccess(List<MessageCell> info);
        public void getInfFail(Object error);
    }
}
