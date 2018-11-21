package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.imodel.MessagesModel;
import com.cxyz.homepage.imodel.impl.MessagesModelImpl;
import com.cxyz.homepage.ipresenter.MessagesPresenter;
import com.cxyz.homepage.myAdapter.cell.MessageCell;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/20.
 */

public class MeassgesPresenterImpl extends MessagesPresenter{
    /**
     *
     * @param _id
     * @param time
     */
    @Override
    public void setMessagesCall(String _id, Date time) {
        this.mIModle.getMessagesJson(_id, time, new MessagesModel.getMessagesInfo() {
            @Override
            public void getInfoSuccess(List<MessageCell> info) {
                if (info==null){
                    ToastUtil.showShort(info.toString());
                }else{
                    mIView.setMessages(info);
                }
            }

            @Override
            public void getInfFail(Object error) {
                ToastUtil.showShort(error.toString());
            }
        });
    }

    @Override
    public MessagesModel createModel() {
        return new MessagesModelImpl();
    }
}
