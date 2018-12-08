package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.check.ipresenter.IHistoryPresenter;
import com.cxyz.check.model.IHistoryModel;
import com.cxyz.check.model.imodelimpl.IHistoryModelImpl;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class IHistoryPresenterImpl extends IHistoryPresenter {
    @Override
    public void getHistory() {
        mIModle.getHistory(new IHistoryModel.GetHistoryListener() {
            @Override
            public void getHistorySuccess(List<CheckHistoryDto> history) {
                mIView.finishRefresh(history);
            }

            @Override
            public void getHistoryFail(String error) {
                mIView.refreshFail(error);
            }
        });
    }

    @Override
    public void loadMoreHistory() {

    }

    @Override
    public IHistoryModel createModel() {
        return new IHistoryModelImpl();
    }
}
