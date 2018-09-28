package com.cxyz.check.CheckModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.media.Image;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.cxyz.check.CheckUtil.MySwitch;
import com.cxyz.check.CheckUtil.stuInfo_Check;
import com.cxyz.check.R;
import com.cxyz.commons.IModel.IBaseModel;
import com.cxyz.commons.domain.User;
import com.cxyz.commons.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 28058 on 2018/9/26.
 */

public class ICheckModel extends IBaseModel {

    private int image[];
    private int user_power;
    private String []function_name;
    private List<String>stu_name;
    private List<String>stu_id;
    private List<Image>stu_image;


    List datalist;
    GridView mygv;
    AlertDialog alert;
    AlertDialog.Builder builder;
    ListView mListView;

    public ListView getmListView() {
        return mListView;
    }

    public void setmListView(ListView mListView) {
        this.mListView = mListView;
    }

    public String []getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String []function_name) {
        this.function_name = function_name;
    }

    public int getUser_power() {
        //做点假数据
        user_power=1;
        return user_power;
    }

    public void setUser_power(int user_power) {
        this.user_power = user_power;
    }

    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    public List<String> getStu_name() {
        return stu_name;
    }

    public void setStu_name(List<String> stu_name) {
        this.stu_name = stu_name;
    }

    public List<String> getStu_id() {
        return stu_id;
    }

    public void setStu_id(List<String> stu_id) {
        this.stu_id = stu_id;
    }

    public List<Image> getStu_image() {
        return stu_image;
    }

    public void setStu_image(List<Image> stu_image) {
        this.stu_image = stu_image;
    }

    public Adapter inieGridData(final Activity activity){

        //这里进行初始化GridView的Data(九宫格需要向适配器内添加数组,根据数组展示功能)
        ToastUtil.init(activity.getApplicationContext());
        //这里需要我做设置，根据权限的不同区判断给予什么功能，故这里需要判断和List来存放数据
        setFunction_name(MySwitch.switch_userPower(getUser_power()));

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final List<Integer> iconList=new ArrayList<>();
        for(int i=1;i<=getFunction_name().length;i++){
            //根据用户得到的功能名获取功能图片
            //这样添加功能图片是不灵活的，应该改变为根据获取的功能名获取相对应的图片，后期需要利用键值对来获取
            iconList.add(R.mipmap.ic_launcher);
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //获取存放图片id的int数组
        int[] icon = new int[iconList.size()];
        for(int i = 0;i<iconList.size();i++){
            icon[i] = iconList.get(i);
        }
        //清空iconList内部的数据
        iconList.clear();
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <icon.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("text",function_name[i]);
            datalist.add(map);
        }
        //实例化一下GridView
        mygv=(GridView) activity.findViewById(R.id.check_gv);
        String[] from={"img","text"};
        int[] to={R.id.image_gv,R.id.text_gv};

        return new SimpleAdapter(activity,datalist,R.layout.item,from,to);

    }

    public stuInfo_Check getListViewInfo(User user){
        //在这里获取会显示在ListView的信息
        //根据登陆用户的权限显示他应该获取的班级成员信息
        //①如果本地存储了班级成员信息,就先从本地读取

        /*
        * 这里需要写一个从本地获取数据的方法
        * */


        //先做一点假数据(获取学生名字)
        stu_name=new ArrayList<String>();
        stu_id=new ArrayList<String>();
//        stu_image=new ArrayList<Image>();
        for(int i=0;i<10;i++){
            stu_name.add("安卓小机器人");
            stu_id.add("小机器人没有id");
        };

        return new stuInfo_Check(stu_name,stu_id);
    }

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