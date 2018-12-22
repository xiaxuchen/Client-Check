package com.cxyz.check.view;

import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.commons.IView.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public interface IHistoryView extends IBaseView {

    /**
     * 完成刷新
     */
    void finishRefresh(List<CheckHistoryDto> historyDtos);

    /**
     * 刷新失败
     * @param error 错误信息
     */
    void refreshFail(String error);

    /**
     * 完成加载
     */
    void finishLoad(List<CheckHistoryDto> historyDtos);

    /**
     * 加载更多失败
     * @param error 错误信息
     */
    void loadMoreFail(String error);

}
