package com.cxyz.mine.IPresenter.presenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.iview.ISettingResView;

/**
 * Created by Administrator on 2018/9/27.
 */

public class IResponsePresenter extends IBasePresenter<IBaseModel,ISettingResView> {
    private Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public IBaseModel createModel() {
        return null;
    }
    public  void btsetting_submit(final  EditText e,final ProgressBar p)
    {
        p.setVisibility(View.VISIBLE);
        new  Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setVisibility(View.GONE);
                            e.setText("");
                            ToastUtil.showShort("已提交");
                        }
                    });
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            };

        }.start();
    }
}
