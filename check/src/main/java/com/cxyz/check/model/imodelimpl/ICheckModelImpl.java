package com.cxyz.check.model.imodelimpl;

import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;

import java.util.ArrayList;
import java.util.List;

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
        user.setPower(5);
        return user;
    }

    /*public ArrayList<HashMap<String, Object>> getListViewInfo(StuInfo_Check StuInfo_Check) {
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
    }*/

}
