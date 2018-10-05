package com.cxyz.check.view;

import android.app.Activity;
import android.widget.Adapter;
import android.widget.GridView;

import com.cxyz.commons.IView.IBaseView;

/**
 * Created by 28058 on 2018/9/26.
 */

public interface IListView extends IBaseView
{

}



/*
*
    private int image[];
    private int user_power;
    private String []function_name;
    List datalist;
    GridView mygv;
    AlertDialog alert;
    AlertDialog.Builder builder;

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    public String []getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String []function_name) {
        this.function_name = function_name;
    }

    public int getUser_power() {
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

    public void initGridView(final Activity activity){
        //初始化ToastUtil
        ToastUtil.init(activity.getApplicationContext());

        //这里需要我做设置，根据权限的不同区判断给予什么功能，故这里需要判断和List来存放数据
        setFunction_name(MySwitch.switch_userPower(getUser_power()));
        //图标
        // int icno[] = { R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
        //       R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
        //     R.mipmap.ic_launcher,R.mipmap.ic_launcher};

        //图标下的文字
        //为类中已有的function_name数组赋值，由于各用户权限不同，故可以看到的权限也不同
        //判断用户权限，返回用户权限可以看见的功能
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
        mygv.setAdapter(new SimpleAdapter(activity,datalist,R.layout.item,from,to));

        mygv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ToastUtil.showLong("你点击的格子名叫"+name[position]+"，让我们看看它的id"+(id+1));
                switch (position+1){
                    case 1:
                        alert=null;//对话框
                        builder=new AlertDialog.Builder(activity);
                        alert = builder.setIcon(R.mipmap.ic_launcher)
                                .setTitle("系统提示：")
                                .setMessage("点击开始考勤")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ToastUtil.showShort("你点击了确定");
                                        //                                        ListView showstuInfo=(ListView)findViewById(R.id.check_list);
//                                        Resources res=activity.getResources();
//                                        List mList=new ArrayList();
//                                        ArrayList tags=new ArrayList<>();
//                                        showstus_Adapter stu1;
//                                        mList.add(stu1)
//                                        showstus_Adapter myAdapter=showstus_Adapter(mList,OrderResultsActivity.this);
//                                        showstuInfo.setAdapter(myAdapter);
//                                    }
//                                    public void onItemClick(AdapterView parent, View view,int position,long id) {
//
//                                        Intent intent = new Intent();
//                                        intent.setClass(OrderResultsActivity.this, RoomResultsDetailActivity.class);
//                                        startActivity(intent);
//                                    }
//                                }
//                }
//                ).create();
//                alert.show();
                                    }
                                }).create();
                        alert.show();
                ;break;
                case 2: alert=null;//对话框
                builder=new AlertDialog.Builder(activity);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("系统提示：")
                        .setMessage("点击开始临时考勤")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShort("你点击了确定");
                            }
                        }).create();
                alert.show();
                ;;break;
                case 3:ToastUtil.showShort(function_name[position]);break;
                case 4:ToastUtil.showShort(function_name[position]);break;
                case 5:ToastUtil.showShort(function_name[position]);break;
                case 6:ToastUtil.showShort(function_name[position]);break;
                case 7:ToastUtil.showShort(function_name[position]);break;
                case 8:ToastUtil.showShort(function_name[position]);break;
                case 9:ToastUtil.showShort(function_name[position]);break;
                case 10:ToastUtil.showShort(function_name[position]);break;
                case 11:ToastUtil.showShort(function_name[position]);break;
                case 12:ToastUtil.showShort(function_name[position]);break;
            }
        }
    });
}
    public int getPower(){

            //返回登录用户权限，这里为默认返回1
            //学生为0级权限
            setUser_power(1);
            return getUser_power();
        }
* */
