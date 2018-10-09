package com.cxyz.logiccommons.manager;


import com.cxyz.logiccommons.domain.Grade;
import com.cxyz.logiccommons.domain.Student;
import com.cxyz.logiccommons.domain.User;

/**
 * Created by 夏旭晨 on 2018/10/5.
 * 用户信息管理类
 */
public class UserManager {
    public User getUser() {
        return u;
    }

    public void setUser(User u) {
        this.u = u;
    }

    private User u;

    private static UserManager userManager;

    public static UserManager getInstance()
    {
        if(userManager == null)
            userManager = new UserManager();
        Student u = new Student();
        u.setPwd("123456");
        u.set_name("夏旭晨");
        u.set_id("17478093");
        u.setPower(5);
        u.setCollege_name("信计");
        u.setGrade(new Grade(122));
        userManager.setUser(u);
        return userManager;
    }

}
