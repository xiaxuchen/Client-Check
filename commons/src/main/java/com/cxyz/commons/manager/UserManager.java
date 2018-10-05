package com.cxyz.commons.manager;

import com.cxyz.commons.domain.User;

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
        return userManager;
    }

}
