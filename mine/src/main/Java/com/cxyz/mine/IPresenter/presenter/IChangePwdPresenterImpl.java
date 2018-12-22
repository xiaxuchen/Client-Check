package com.cxyz.mine.IPresenter.presenter;

import com.cxyz.mine.imodel.IChangePwdModelmpl;
import com.cxyz.mine.imodel.IChangePwdModel;

/**
 * Created by ${喻济生} on 2018/11/13.
 */

public class IChangePwdPresenterImpl extends IChangePwdPresenter {
    @Override
    public void testpwd(String oldpwd) {


    }
    @Override
    public void changepwd( String newowd) {
        String msg = this.checkout(newowd);
        //判断数据是否合法
        if(msg != null)
        {
            //不合法直接显示错误信息并停止执行
            mIView.chanegFail(msg);
            return;
        }

    }
    private String checkout(String newpwd)
    {

        if(newpwd.isEmpty())
            return "密码不能为空！";
        if(newpwd.length()<6)
            return "密码不能少于6位！";
        return null;
    }


    @Override
    public IChangePwdModel createModel() {
        return new IChangePwdModelmpl();
    }



}
