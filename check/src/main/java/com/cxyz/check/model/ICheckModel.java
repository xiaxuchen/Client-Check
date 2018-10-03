package com.cxyz.check.model;

import com.cxyz.check.checkTools.StuInfo_Check;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.Student;
import com.cxyz.commons.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 28058 on 2018/9/26.
 */
//Model负责获取（本机没有）数据
public interface ICheckModel extends IBaseModel {

    /**
     *从服务器获取学生数据
     * @return
     */
    public List<Student> getStus();

    /*
    从本地获取学生数据
    * */

    public User getUser();

}























//        mygv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // ToastUtil.showLong("你点击的格子名叫"+name[position]+"，让我们看看它的id"+(id+1));
//                switch (position+1){
//                    case 1:
//                        alert=null;//对话框
//                        builder=new AlertDialog.Builder(activity);
//                        alert = builder.setIcon(R.mipmap.ic_launcher)
//                                .setTitle("系统提示：")
//                                .setMessage("点击开始考勤")
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        ToastUtil.showShort("你点击了确定");
//                                        //                                        ListView showstuInfo=(ListView)findViewById(R.id.check_list);
////                                        Resources res=activity.getResources();
////                                        List mList=new ArrayList();
////                                        ArrayList tags=new ArrayList<>();
////                                        showstus_Adapter stu1;
////                                        mList.add(stu1)
////                                        showstus_Adapter myAdapter=showstus_Adapter(mList,OrderResultsActivity.this);
////                                        showstuInfo.setAdapter(myAdapter);
////                                    }
////                                    public void onItemClick(AdapterView parent, View view,int position,long id) {
////
////                                        Intent intent = new Intent();
////                                        intent.setClass(OrderResultsActivity.this, RoomResultsDetailActivity.class);
////                                        startActivity(intent);
////                                    }
////                                }
////                }
////                ).create();
////                alert.show();
//                                    }
//                                }).create();
//                        alert.show();
//                        ;break;
//                    case 2: alert=null;//对话框
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
//                        ;;break;
//                }
//            }
//        });


//图标
// int icno[] = { R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
//       R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
//     R.mipmap.ic_launcher,R.mipmap.ic_launcher};

//图标下的文字
//为类中已有的function_name数组赋值，由于各用户权限不同，故可以看到的权限也不同
//判断用户权限，返回用户权限可以看见的功能