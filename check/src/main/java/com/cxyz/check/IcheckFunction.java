package com.cxyz.check;
import android.app.Activity;
import android.content.Context;

/**
 * Created by 28058 on 2018/9/25.
 */

public interface IcheckFunction {

    public int getPower();
    //为实现接口的类获取登陆用户的权限

    public void initGridView(Activity activity);

    public void add_stu();

}
