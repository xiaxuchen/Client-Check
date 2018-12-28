package com.cxyz.logiccommons.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.receiver.JPushReceiver;
import com.cxyz.logiccommons.typevalue.NotifyType;

/**
 * Created by Administrator on 2018/12/27.
 */

public class TempActivity extends Activity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("info");
        String path = intent.getStringExtra("path");
        Integer type = intent.getIntExtra("type", NotifyType.ERROR);
        if(type!=NotifyType.ERROR)
        {
            if(type == NotifyType.BAD_CHECK_RECORD)
                JPushReceiver.getNotifyIds().remove(type);
        }
        LogUtil.e(path);
        //直接跳转至相应的activity，然后关闭自己
        ARouter.getInstance().build(path).with(data).navigation();
        finish();
    }
}
