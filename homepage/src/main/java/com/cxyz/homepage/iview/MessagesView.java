package com.cxyz.homepage.iview;

import com.cxyz.commons.IView.IBaseView;
import com.cxyz.homepage.myAdapter.cell.MessageCell;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/11/18.
 */

public interface MessagesView extends IBaseView{
    /**
     *
     * @param recordDetailList
     */
    void setMessages(List<MessageCell> recordDetailList);
}
