package com.cxyz.untilchecked.tinker.applicationlike;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.cxyz.commons.context.ContextManager;
import com.cxyz.untilchecked.R;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2019/1/24.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        init();
    }

    private void init() {

        EditText et_alia = findViewById(R.id.et_alia);
        Button btn_change = findViewById(R.id.btn_change);

        btn_change.setOnClickListener(view -> {
            JPushInterface.setAlias(ContextManager.getContext(),1,et_alia.getText().toString().trim());
        });
    }
}
