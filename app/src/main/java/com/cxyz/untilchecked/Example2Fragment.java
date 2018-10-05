package com.cxyz.untilchecked;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/9/25.
 */

public class Example2Fragment extends BaseFragment {

    public static Example2Fragment newInstance(String text) {
        //activity传递数据过来，然后Fragment把数据放入arguments，到时候取的时候getArguments取，数据可以是很多类型的，不止是String
        Bundle args = new Bundle();
        args.putString("text",text);
        Example2Fragment fragment = new Example2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_example2_layout;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        TextView tv_show = findViewById(R.id.tv_show);
        Button btn_back = findViewById(R.id.btn_back);
        tv_show.setText(getArguments().getString("text"));
        //销毁最上面的Fragment
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
    }

    @Override
    protected void setListener() {

    }
}
