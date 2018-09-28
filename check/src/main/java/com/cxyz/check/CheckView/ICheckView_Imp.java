package com.cxyz.check.CheckView;

import android.app.Activity;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

/**
 * Created by 28058 on 2018/9/26.
 */

public class ICheckView_Imp implements ICheckView {

    GridView mGridView;
    Activity activity;

    @Override
    public void initGridView(Adapter adapter,GridView mGridView) {
        //在这里初始化我们的GridView

        mGridView.setAdapter((SimpleAdapter)adapter);

    }

    @Override
    public void initActivity(Activity activity) {

        this.activity=activity;

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
