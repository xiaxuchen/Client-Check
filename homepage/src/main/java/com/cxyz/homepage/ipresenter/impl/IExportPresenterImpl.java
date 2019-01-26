package com.cxyz.homepage.ipresenter.impl;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.homepage.dto.GradeTaskDto;
import com.cxyz.homepage.imodel.IExportModel;
import com.cxyz.homepage.imodel.impl.IExportModelImpl;
import com.cxyz.homepage.ipresenter.IExportPresenter;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class IExportPresenterImpl extends IExportPresenter {
    @Override
    public void getGradeTasks() {
        mIView.showLoadingView();
        mIModle.getGradeTasks(new IBaseModel.ModelListener<List<GradeTaskDto>, String>() {
            @Override
            public void onSuccess(List<GradeTaskDto> data) {
                mIView.showSpinner(data);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String s) {
                mIView.showError(s);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public void getStatisticExcel(Integer gradeId, String taskName) {
        mIView.showLoadingView();
        mIModle.getStatisticExcel(gradeId, taskName, new IExportModel.getExcelListener() {
            @Override
            public void onProgress(int progress) {
                mIView.onProgress(progress);
            }

            @Override
            public void onSuccess(File file) {
                mIView.downloadSuccess(file);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(String error) {
                mIView.downloadFail(error);
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public IExportModel createModel() {
        return new IExportModelImpl();
    }
}
