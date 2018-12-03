package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.dto.CommitCheckDto;
import com.cxyz.check.dto.GradeStusDto;
import com.cxyz.check.ipresenter.IDailyPresenter;
import com.cxyz.check.model.IDailyModel;
import com.cxyz.check.model.imodelimpl.IDailyModelImpl;
import com.cxyz.logiccommons.typevalue.TaskCompletionState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/10/5.
 */

public class IDailyPresenterImpl extends IDailyPresenter{
    @Override
    public void getStusToShow(int grade) {
        mIView.showLoadingView();
        mIModle.getStus(grade, new IDailyModel.GetStusListener() {
            @Override
            public void onSuccess(List<GradeStusDto> stus) {
                mIView.showStus(stus);
                mIView.hideLoadStus();
            }

            @Override
            public void onFail(String fail) {
                mIView.showError(fail);
            }
        });
    }

    @Override
    public void commit(Map<String,CommitCheckDto.StuInfo> stuInfos, int completion) {
        mIView.showLoadingView();
        mIModle.commit(getCommitCheckDto(getStus(stuInfos),completion),new IDailyModel.CommitListener() {
            @Override
            public void onCompletion(String info) {
                mIView.hideLoadingView();
                mIView.showCommitResult(info);
            }

            @Override
            public void onFail(String error) {
                mIView.showError(error);
            }
        });

    }

    private List<CommitCheckDto.StuInfo> getStus(Map<String, CommitCheckDto.StuInfo> stuInfos) {
        List<CommitCheckDto.StuInfo> list = new ArrayList<>();
        for(String key:stuInfos.keySet())
        {
            list.add(stuInfos.get(key));
        }
        return list;
    }

    @Override
    public IDailyModel createModel() {
        return new IDailyModelImpl();
    }


    /**
     * 封装为dto
     * @param stuInfos
     * @param compId
     * @return
     */
    private CommitCheckDto getCommitCheckDto(List<CommitCheckDto.StuInfo> stuInfos,int compId)
    {
        CommitCheckDto commitCheckDto = new CommitCheckDto();
        commitCheckDto.setTaskId(compId);
        commitCheckDto.setState(TaskCompletionState.COMPLE);
        commitCheckDto.setStuInfos(stuInfos);
        return commitCheckDto;
    }
}
