package com.cxyz.check.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.check.checkTools.showstus_Adapter;
import com.cxyz.check.presenter.ICheckPresenter;
import com.cxyz.check.presenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.view.ICheckView;
import com.cxyz.check.R;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.application.MyApp;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 28058 on 2018/9/25.
 */

public class CheckActivity extends BaseActivity<ICheckPresenter> implements ICheckView {



    @Override
    public int getContentViewId() {
        return R.layout.check_activity_main_layout;
    }

    @Override
    public void initView(){
        showGridView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        GridView gv=(GridView)findViewById(R.id.gv_check);
        gv.setOnItemClickListener(getGridViewListener());
    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showResultDialog() {

    }

    @Override
    public void showCommitView() {

    }

    @Override
    public void showStus(List<Student> stus) {

    }

    @Override
    //这个方法是初始化GridView，只有经过调用后才会初始化
    public void showGridView() {
        GridView gv = (GridView) findViewById(R.id.gv_check);
        String[] from = {"img", "text"};
        int[] to = {R.id.image_gv, R.id.text_gv};
        gv.setAdapter(new SimpleAdapter(getActivity(), iPresenter.getGridViewInfo(), R.layout.item, from, to));
    }


    @Override
    //这个方法是通过获取用户的权限，返回一个相应的Listener
    public AdapterView.OnItemClickListener getGridViewListener() {
        //判断用户权限，返回不同的Listener
        //做点假数据
        int power=5;
        //
        AdapterView.OnItemClickListener Listener=null;
        switch (power){
            case 0:
                ;
                break;
            case 5:
                Listener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        switch (position + 1) {
                            case 1:
//                                showListView();
                                Intent intent=new Intent(CheckActivity.this,ListViewAcitivity.class);
                                startActivity(intent);
                                AlertDialog alert = null;//对话框
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                alert = builder.setIcon(R.mipmap.ic_launcher)
                                        .setTitle("系统提示：")
                                        .setMessage("点击开始考勤")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ToastUtil.showShort("你点击了确定");
                                            }
                                        }).create();
//                                alert.show()
                                ;
                                ;break;
                            case 2:
                                alert = null;//对话框
                                builder = new AlertDialog.Builder(getActivity());
                                alert = builder.setIcon(R.mipmap.ic_launcher)
                                        .setTitle("系统提示：")
                                        .setMessage("点击开始临时考勤")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ToastUtil.showShort("你点击了确定");
                                            }
                                        }).create();
                                alert.show()
                                ;
                                break;
                            case 3:
                                ;
                                break;
                            case 4:
                                ;
                                break;
                            case 5:
                                ;
                                break;
                            case 6:
                                ;
                                break;
                            case 7:
                                ;
                                break;
                            case 8:
                                ;
                                break;
                            case 9:
                                ;
                                break;
                            case 10:
                                ;
                                break;
                            case 11:
                                ;
                                break;
                            case 12:
                                ;
                                break;
                        }
                    };
                };
                ;
                break;
            case 30:
                ;
                break;
            case 35:
                ;
                break;
            case 45:
                ;
                break;
            case 55:
                ;
                break;
            case 100:
                ;
                break;

        }
        return Listener;
    }

    @Override
    //这个方法是初始化ListView，只有调用后才会初始化
    public void showListView() {
//        showstus_Adapter myAdapter = new showstus_Adapter(getActivity(),iPresenter.getstuInfo_check(iPresenter.mIModle.getListViewInfo()));
//        LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//        View view=inflater.inflate(R.layout.check_activity_listview_layout, null);
//        ListView lv=(ListView) view.findViewById(R.id.list_check);
//        lv.setAdapter(myAdapter);

    }



}
