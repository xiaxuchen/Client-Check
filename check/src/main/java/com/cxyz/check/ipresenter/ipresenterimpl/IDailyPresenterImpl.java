package com.cxyz.check.ipresenter.ipresenterimpl;

import com.cxyz.check.ipresenter.IDailyPresenter;
import com.cxyz.check.model.IDailyModel;
import com.cxyz.check.model.imodelimpl.IDailyModelImpl;
import com.cxyz.commons.utils.HttpUtil.exception.OKHttpException;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.Student;
import com.cxyz.logiccommons.domain.TaskCompletion;

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
            public void onSuccess(List<Student> stus) {
                mIView.showStus(stus);
                mIView.hideLoadingView();
            }

            @Override
            public void onFail(Object fail) {
                if(fail instanceof  String)
                    mIView.showError(fail);
                else if(fail instanceof OKHttpException)
                    mIView.showError(((OKHttpException) fail).getMessage());
                mIView.hideLoadingView();
            }
        });
    }

    @Override
    public void commit(Map<String, CheckRecord> crs, TaskCompletion completion) {
        LogUtil.e("我在调用它");
        /*mIView.showLoadingView();
        Check c = Check.getCheck(crs,completion);
        c.getCompletion().setState(TaskCompletion.NORMAL);
        mIModle.commit(c,new IDailyModel.CommitListener() {
            @Override
            public void onCompletion(String info) {
                mIView.showCommitResult(info);
                mIView.hideLoadingView();
            }
        });*/

    }

    @Override
    public IDailyModel createModel() {
        return new IDailyModelImpl();
    }
}
