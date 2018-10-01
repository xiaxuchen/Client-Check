package com.cxyz.check.model.imodelimpl;

import android.app.AlertDialog;
import android.media.Image;
import android.widget.GridView;
import android.widget.ListView;

import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28058 on 2018/9/29.
 */

public class ICheckModelImpl implements ICheckModel {

    @Override
    public List<Student> getStus() {
        List<Student> list = new ArrayList<>();
        list.add(new Student());
        return list;
    }


    @Override
    public User getUser() {
        //这里返回登录的用户
        //返回一个假数据

        User user=new User();
        user.power=5;
        return user;
    }

    public ArrayList<HashMap<String, Object>> getListViewInfo(StuInfo_Check StuInfo_Check) {
        //做点假数据
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        //根据需求添加一些数据,
        HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
        // 相同的样式布局,可以设置显示不同的文字。

        for (int i = 0; i < StuInfo_Check.stu_name.size(); i++) {
            tempHashMap.put("stu_name", StuInfo_Check.stu_name.get(i));
            tempHashMap.put("stu_info", StuInfo_Check.stu_info.get(i));
            tempHashMap.put("stu_check",StuInfo_Check.stu_check.get(i));
            arrayList.add(tempHashMap);
        }
        return arrayList;
    }

    @Override
    public StuInfo_Check getListViewInfo(){
        //在这里获取会显示在ListView的信息
        //根据登陆用户的权限显示他应该获取的班级成员信息
        //①如果本地存储了班级成员信息,就先从本地读取*/
        /*
        * 这里需要写一个从本地获取数据的方法
        * */
    //先做一点假数据(获取学生名字)
        List stu_name;
        List stu_id;
        List stu_check;
        stu_name=new ArrayList<String>();
        stu_id=new ArrayList<String>();
        stu_check=new ArrayList<String>();
//        stu_image=new ArrayList<Image>();
        for(int i=0;i<10;i++){
            stu_name.add("安卓小机器人");
            stu_id.add("小机器人没有id");
            stu_check.add("小机器人每天都出勤了");
        };

        return new StuInfo_Check(stu_name,stu_id,stu_check);
    }



}
