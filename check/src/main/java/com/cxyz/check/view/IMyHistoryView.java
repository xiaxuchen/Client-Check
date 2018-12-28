package com.cxyz.check.view;

import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.commons.IView.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/12/27.
 */

public interface IMyHistoryView extends IBaseView {

    /**
     * 完成刷新
     */
    void finishRefresh(List<MyHistoryDto> historyDtos);

    /**
     * 刷新失败
     * @param error 错误信息
     */
    void refreshFail(String error);

    /**
     * 完成加载
     */
    void finishLoad(List<MyHistoryDto> historyDtos);

    /**
     * 加载更多失败
     * @param error 错误信息
     */
    void loadMoreFail(String error);
}
