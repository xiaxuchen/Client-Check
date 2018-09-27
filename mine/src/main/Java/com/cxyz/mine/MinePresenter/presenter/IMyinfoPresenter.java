package com.cxyz.mine.MinePresenter.presenter;

import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.domain.College;
import com.cxyz.commons.domain.Grade;
import com.cxyz.commons.domain.User;
import com.cxyz.mine.MinePresenter.ipresenter.IMyinfoView;

/**
 * Created by Administrator on 2018/9/27.
 */

public class IMyinfoPresenter extends IBasePresenter<IBaseModel,IMyinfoView>{
    @Override
    public IBaseModel createModel() {
        return null;
    }
    //获取数据
    //从User获取姓名，性别，学号，电话，等级权限
    public void getInfo() {
        String username ="张国荣";
        String sex="男";
        String  schoolcode="17478091";
        int power=1;
        String tel="12345678910";
        User user= new User();
        user.tel=tel;
        user.power=power;
        user._id=schoolcode;
        user._name = username;
        user.sex=sex;
        mIView.showMyInfo(user);
    }
    //从Grade获取班级
    public  void getClassname(){
        String classname="1702软件工程";
        Grade grade=new Grade();
        grade._name=classname;
        mIView.showMyClass(grade);
    }
    //从College获取学院名字
    public void getCollege(){
        String collegename="信息与计算机工程学院";
        College college=new College();
        college._name=collegename;
        mIView.showMyCollege(college);
    }


}
