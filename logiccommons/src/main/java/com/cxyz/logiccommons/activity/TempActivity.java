package com.cxyz.logiccommons.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2018/12/27.
 */

public class TempActivity extends Activity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("info");
        String path = intent.getStringExtra("path");
        //直接跳转至相应的activity，然后关闭自己
        ARouter.getInstance().build(path).with(data).navigation();
        finish();
    }
}
