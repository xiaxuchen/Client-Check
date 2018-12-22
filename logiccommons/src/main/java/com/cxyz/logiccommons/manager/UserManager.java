package com.cxyz.logiccommons.manager;


import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.application.MyApp;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.typevalue.UserType;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 夏旭晨 on 2018/10/5.
 * 用户信息管理类
 */
public class UserManager {
    public User getUser() {
        return u;
    }

    public void setUser(User user) {
        LogUtil.e(user.toString());
        this.u = user;
        JPushInterface.setAlias(MyApp.getApplication(),17478094,u.getId());
    }

    private User u = getFakeUser();

    private static UserManager userManager;

    public static UserManager getInstance()
    {
        if(userManager == null)
            userManager = new UserManager();
        return userManager;
    }

    /**
     * TODO 正式上线去除
     * 测试专用方法
     * @return
     */
    private User getFakeUser()
    {
        User u = new User();
        u.setGradeId(1702);
        u.setName("夏旭晨");
        u.setId("17478094");
        u.setGradeName("17软工二班");
        u.setCollegeName("信息与计算机工程学院");
        u.setPower(5);
        u.setType(UserType.STUDENT);
        return u;
    }

}
