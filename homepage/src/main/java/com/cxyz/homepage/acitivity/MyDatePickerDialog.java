package com.cxyz.homepage.acitivity;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * Created by ${喻济生} on 2018/12/23.
 */

public class MyDatePickerDialog extends DatePickerDialog {
    public MyDatePickerDialog(Context context, OnDateSetListener callBack,
                              int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {
        //super.onStop();
    }
}

