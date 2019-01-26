package com.cxyz.logiccommons.manager;


import com.cxyz.commons.context.ContextManager;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.typevalue.PowerType;
import com.cxyz.logiccommons.typevalue.UserType;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 夏旭晨 on 2018/10/5.
 * 用户信息管理类
 */
public class UserManager {

    private static final int SET_ALIAS = 1;

    public User getUser() {
        return u;
    }

    public void setUser(User user) {
        this.u = user;
        if(u!=null)
            JPushInterface.setAlias(ContextManager.getContext(),SET_ALIAS,u.getId());
        else
            JPushInterface.setAlias(ContextManager.getContext(),SET_ALIAS,"");
    }

    public void testJpush()
    {
        int start = 17478001;
        for(int i = 0;i<50;i++)
            try{
                JPushInterface.setAlias(ContextManager.getContext(),SET_ALIAS,(start++)+"");
                LogUtil.d("注册别名:"+start);
        }catch (Exception e)
        {
            e.printStackTrace();
            LogUtil.e("发生错误");
        }
        JPushInterface.getAlias(ContextManager.getContext(),SET_ALIAS);
        JPushInterface.setAlias(ContextManager.getContext(),SET_ALIAS,17478001+"");
    }

    /**
     * 是否已登录
     * @return
     */
    public boolean isLogined()
    {
        return getUser() == null?false:true;
    }

    private User u=getFakeUser();

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
        u.setId("17478093");
        u.setGradeName("17软工二班");
        u.setCollegeName("信息与计算机工程学院");
        u.setPower(PowerType.STU_CHECKER);
        u.setType(UserType.STUDENT);
        return u;
    }

}
