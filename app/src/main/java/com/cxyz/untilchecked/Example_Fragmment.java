package com.cxyz.untilchecked;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/9/25.
 * 不与activity通信的Fragment
 */

public class Example_Fragmment extends BaseFragment{

    /**
     * 写一个静态的获取实例的方法newInstance，使得activity与Fragment解耦
     * @return
     */
    public static Example_Fragmment newInstance() {
        return new Example_Fragmment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_example_layout;
    }

    @Override
    protected void initData(Bundle bundle) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Button btn_go = findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Example2Fragment.newInstance("你好啊"));
            }
        });
    }

    @Override
    protected void setListener() {

    }
}
