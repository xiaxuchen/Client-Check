package com.cxyz.check.presenter;

import android.app.Activity;
import android.widget.ListView;

import com.cxyz.check.model.IListViewModel;
import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.checkTools.showstus_Adapter;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 28058 on 2018/9/26.
 */

public class IListViewPresenter extends IBasePresenter<IBaseModel,IBaseView> {

    IListViewModel mModel;
    IBaseView mView;

    @Override
    public IBaseModel createModel() {

        return null;
    }

    public void initListView(Activity activity,ListView mListView,StuInfo_Check StuInfo_Check) {
            showstus_Adapter myAdapter = new showstus_Adapter(activity, getstuInfo_check(StuInfo_Check));
            mListView.setAdapter(myAdapter);
    }
    private ArrayList<HashMap<String,Object>> getstuInfo_check(StuInfo_Check StuInfo_Check){
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
