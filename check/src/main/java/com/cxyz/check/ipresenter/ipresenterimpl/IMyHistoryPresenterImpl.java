package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.check.ipresenter.IMyHistoryPresenter;
import com.cxyz.check.model.IMyHistoryModel;
import com.cxyz.check.model.imodelimpl.IMyHistoryModelImpl;
import com.cxyz.commons.IModel.IBaseModel;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class IMyHistoryPresenterImpl extends IMyHistoryPresenter {


    @Override
    public IMyHistoryModel createModel() {
        return new IMyHistoryModelImpl();
    }

    @Override
    public void getHistory(Integer result) {
        mIModle.getHistory(result, new IBaseModel.ModelListener<List<MyHistoryDto>, String>() {
            @Override
            public void onSuccess(List<MyHistoryDto> data) {
                mIView.finishRefresh(data);
            }

            @Override
            public void onFail(String s) {
                mIView.refreshFail(s);
            }
        });
    }

    @Override
    public void loadMoreHistory(Integer result, Integer start) {
        mIModle.loadMoreHistory(result, start, new IBaseModel.ModelListener<List<MyHistoryDto>, String>() {
            @Override
            public void onSuccess(List<MyHistoryDto> data) {
                mIView.finishLoad(data);
            }

            @Override
            public void onFail(String s) {
                mIView.loadMoreFail(s);
            }
        });
    }
}
