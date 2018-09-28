package com.cxyz.check.CheckPresenter;

import android.app.Activity;
import android.widget.ListView;

import com.cxyz.check.CheckModel.IListViewModel;
import com.cxyz.check.CheckUtil.stuInfo_Check;
import com.cxyz.check.CheckUtil.showstus_Adapter;
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

    public void initListView(Activity activity,ListView mListView,stuInfo_Check stuInfo_Check) {
//                                        Resources res=activity.getResources();
//                                        List mList=new ArrayList();
//                                        ArrayList tags=new ArrayList<>();

            showstus_Adapter myAdapter = new showstus_Adapter(activity, getstuInfo_check(stuInfo_Check));
            mListView.setAdapter(myAdapter);
    }
    private ArrayList<HashMap<String,Object>> getstuInfo_check(stuInfo_Check stuInfo_Check){
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        //根据需求添加一些数据,
        HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
        // 相同的样式布局,可以设置显示不同的文字。

        for (int i = 0; i < stuInfo_Check.stu_name.size(); i++) {
            tempHashMap.put("stu_name", stuInfo_Check.stu_name.get(i));
            tempHashMap.put("stu_info", stuInfo_Check.stu_info.get(i));
            arrayList.add(tempHashMap); 	        }
        return arrayList;
    }

}
