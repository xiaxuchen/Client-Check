package com.example.test;

import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by xiaxuchen on 18-10-29.
 */

@EActivity(R.layout.activity_main)
public class ChildActivity extends MainActivity {

    @Override
    public void init() {
        super.init();
        Toast.makeText(this,"我草你妈",Toast.LENGTH_LONG).show();
    }

}
