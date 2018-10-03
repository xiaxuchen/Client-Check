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

    public ICheckModel createModel() {
        return new ICheckModelImpl();
    }

    public void getStusToShow() {

    }

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

}