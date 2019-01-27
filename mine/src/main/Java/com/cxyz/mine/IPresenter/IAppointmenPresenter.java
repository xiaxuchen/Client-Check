package com.cxyz.mine.IPresenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.iview.IAppointmentView;

import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */

public  class IAppointmenPresenter extends IBasePresenter<IBaseModel,IAppointmentView> {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override public IBaseModel createModel() {
        return null;
    }
    public void btappointment_submit(final List<EditText> e,final  ProgressBar p){
        p.setVisibility(View.VISIBLE);
        new Thread(){
            public void  run(){
                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            p.setVisibility(View.GONE);
                            for (EditText editText:e)
                                editText.setText("");
                            ToastUtil.showShort("已提交");
                        }
                    });
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }.start();
    }
}
