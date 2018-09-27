package com.cxyz.mine.MinePresenter.presenter;

import android.content.Context;
import android.content.Intent;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.mine.MineView.mineactivity.M_appointmentActivity;
import com.cxyz.mine.MineView.mineactivity.M_myinfoActivity;
import com.cxyz.mine.MineView.mineactivity.M_settingActivity;
import com.cxyz.mine.MinePresenter.ipresenter.IMineView;

/**
 * Created by Administrator on 2018/9/25.
 */

public class IMinePresenter extends IBasePresenter<IBaseModel,IMineView> {
    @Override
    public IBaseModel createModel() {
        return null;
    }



    //界面跳转
    //从我的界面跳转到设置界面
    public void tvsetting(Context context) {
        Intent  intent=new Intent(context,M_settingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    //从我的界面跳转到预约请假界面
    public void tvappointment(Context context) {
        Intent  intent=new Intent(context,M_appointmentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    //从我的界面跳转到个人信息界面
    public void tvmyinfo(Context  context) {
        Intent intent=new Intent(context,M_myinfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void btsetting_submit(){
        ToastUtil.showShort("已提交");
    }
}
