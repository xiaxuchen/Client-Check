package com.cxyz.homepage.ipresenter;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.date.Date;
import com.cxyz.homepage.imodel.MessagesModel;
import com.cxyz.homepage.iview.MessagesView;

/**
 * Created by 鱼塘主 on 2018/11/19.
 */

public abstract class MessagesPresenter extends IBasePresenter<MessagesModel,MessagesView>{
    /**
     *
     * @param _id
     * @param time
     */
    public abstract void setMessagesCall(String _id, Date time);
}
