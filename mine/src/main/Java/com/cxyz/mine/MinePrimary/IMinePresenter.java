package com.cxyz.mine.MinePrimary;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;

/**
 * Created by Administrator on 2018/9/25.
 */

public class IMinePresenter extends IBasePresenter<IBaseModel,IMineView> {
    @Override
    public IBaseModel createModel() {
        return null;
    }
    public void getInfo() {
        String username ="张国荣";
        String sex="男";
        String  schoolcode="17478091";
        int power=1;
        User user= new User();
        user.power=power;
        user._id=schoolcode;
        user._name = username;
        user.sex=sex;
        mIView.showMineInfo(user);
    }
    public  void getClassname(){
        String classname="1702软件工程";
        Grade grade=new Grade();
        grade._name=classname;
        mIView.showMineClass(grade);
    }
    public void getCollege(){
        String collegename="信息与计算机工程学院";
        College college=new College();
        college._name=collegename;
        mIView.showMineCollege(college);

    }

}
