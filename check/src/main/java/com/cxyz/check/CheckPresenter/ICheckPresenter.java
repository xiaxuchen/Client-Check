package com.cxyz.check.CheckPresenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cxyz.check.CheckModel.ICheckModel;
import com.cxyz.check.CheckUtil.stuInfo_Check;
import com.cxyz.check.ListViewAcitivity;
import com.cxyz.check.R;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 28058 on 2018/9/26.
 */

public class ICheckPresenter extends IBasePresenter<IBaseModel,IBaseView> {

    stuInfo_Check stuInfo_Check;
    public ICheckModel mModel;
    IBaseView mView;

    @Override
    public IBaseModel createModel() {

        return null;
    }

    public Adapter getGridView(Activity activity){

        return new ICheckModel().inieGridData(activity);

    }

    public void setGridViewListener(final GridView mGridView, final Activity activity){
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position+1){
                    case 1:
                        Intent intent=new Intent();
                        intent.setClass(activity,ListViewAcitivity.class);
                        activity.startActivity(intent);
//                        AlertDialog alert=null;//对话框
//                        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
//                        alert = builder.setIcon(R.mipmap.ic_launcher)
//                                .setTitle("系统提示：")
//                                .setMessage("点击开始考勤")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        ToastUtil.showShort("你点击了确定");
//                                        Intent intent=new Intent();
//                                        intent.setClass(activity,ListViewAcitivity.class);
//                                        activity.startActivity(intent);
//                                    }
//                                }).create();
//                        alert.show();
                        break;
                    case 2:
//                        alert=null;//对话框
//                        builder=new AlertDialog.Builder(activity);
//                        alert = builder.setIcon(R.mipmap.ic_launcher)
//                                .setTitle("系统提示：")
//                                .setMessage("点击开始临时考勤")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        ToastUtil.showShort("你点击了确定");
//                                    }
//                                }).create();
//                        alert.show();
                        ;;break;
                    case 3:;break;
                    case 4:;break;
                    case 5:;break;
                    case 6:;break;
                    case 7:;break;
                    case 8:;break;
                    case 9:;break;
                    case 10:;break;
                    case 11:;break;
                    case 12:
                        ToastUtil.showShort("点击失效");break;
                }
            }
        });
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
