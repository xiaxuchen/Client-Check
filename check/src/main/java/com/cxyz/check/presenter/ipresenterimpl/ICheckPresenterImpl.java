package com.cxyz.check.presenter.ipresenterimpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.cxyz.check.R;
import com.cxyz.check.checkTools.MySwitch;
import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.model.ICheckModel;
import com.cxyz.check.model.imodelimpl.ICheckModelImpl;
import com.cxyz.check.presenter.ICheckPresenter;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28058 on 2018/9/29.
 */

public class ICheckPresenterImpl extends ICheckPresenter {

    @Override
    public ICheckModel createModel() {
        return new ICheckModelImpl();
    }

    @Override
    public void getStusToShow() {

    }

    @Override
    //getGridView功能是返回将会在显示在GridView内的功能名字，返回List，里面包含了功能名和功能图片
    public List getGridViewInfo() {
        //在这里根据返回的用户权限为用户配置相应的功能
        //返回用户权限相应的功能
        //当本地没有用户信息时，从网络获取用户权限
        String function[] = MySwitch.switch_userPower(mIModle.getUser().power);
        List<Integer> iconList = new ArrayList<>();
        for (int i = 1; i <= function.length; i++) {
            iconList.add(R.mipmap.ic_launcher);
        }
        int[] icon = new int[iconList.size()];
        for (int i = 0; i < iconList.size(); i++) {
            icon[i] = iconList.get(i);
        }
        iconList.clear();
        List datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("text", function[i]);
            datalist.add(map);
        }
        return datalist;
    }

    @Override
    public ArrayList<HashMap<String,Object>> getstuInfo_check(StuInfo_Check StuInfo_Check){
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        //根据需求添加一些数据,
        HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
        // 相同的样式布局,可以设置显示不同的文字。

        for (int i = 0; i < StuInfo_Check.stu_name.size(); i++) {
            tempHashMap.put("stu_name", StuInfo_Check.stu_name.get(i));
            tempHashMap.put("stu_info", StuInfo_Check.stu_info.get(i));
            arrayList.add(tempHashMap); 	        }
        return arrayList;
    }
}