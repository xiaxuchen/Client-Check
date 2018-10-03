package com.cxyz.homepage.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.ColorsUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.domain.Clazz;
import com.cxyz.homepage.domain.Stu;
import com.cxyz.homepage.myAdapter.Index_PagerAdapter;
import com.cxyz.homepage.view.myTableTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 鱼塘主 on 2018/9/25.
 */

public class Index_fragment extends BaseFragment {

    //虚拟(假)信息
    Stu user = new Stu("17478090","张大壮","123456","男","854703427@qq.com","#","17软工2班","颜丽","100");

    //搞课表
    private ViewPager kebiao;
   // private TextView time_show_data,time_show_week,clazz_1_name,clazz_1_teacher,clazz_1_room;
    private List<Clazz> list = new ArrayList();

    //搞工具
    private GridView gv_tool;
    private int[] function_img_R = new int[]{R.mipmap.logo,R.mipmap.logo,R.mipmap.logo,R.mipmap.logo,R.mipmap.logo,R.mipmap.logo,R.mipmap.logo,R.mipmap.logo};
    private String [] function={"记事本","记事本","记事本","记事本","记事本","记事本","记事本","记事本"};
    private List<Map<String,Object>> datalist;
    private SimpleAdapter adapter;

    //搞个人信息
    private LinearLayout tableLinerLayout,tLinearLayout;
    private String[] table_title={"学号","姓名","本学期缺勤情况:","工号","本学期班级缺勤人数"};
    private String[] table_text={user.getId(),user.getName(),user.getGg()};

    //搞申述按钮
    private Button btn_allege;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    public static Index_fragment newInstance() {
        Index_fragment fragment = new Index_fragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle bundle) {
        /**
         * 搞课表viewpager(分班级课表,开始周数,从周一至周五录,例:周一:以节数(共7节课)为基准,课程名(object),代课老师(object),上课教室(int),taskinfo)
         */

        kebiao = (ViewPager) findViewById(R.id.vp_kebiao);
        Clazz[] c = new Clazz[4];
        c[0] = new Clazz("离散数学","朱哲","3316","1","2");
        c[1] = new Clazz("没有","没有","没有","3","4");
        c[2] = new Clazz("马克思主义基本原理","陈尧嘉","2608","5","6");
        c[3] = new Clazz("马克思主义基本原理","陈尧嘉","2608","7","7");

        for (int j = 0;j < 4 ; j++){
            list.add(c[j]);
        }


        kebiao.setPageMargin(10);  //设置viewpager页面之间的间隔
        kebiao.setOffscreenPageLimit(5);//设置viewpager预加载页面数
        //将数据搞到pageradapter中
        kebiao.setAdapter(new Index_PagerAdapter(this.getActivity(),list));

        /**
         *搞工具表GridView
         */
        gv_tool = (GridView) findViewById(R.id.tools_grid);
        datalist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <function_img_R.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", function_img_R[i]);
            map.put("text",function[i]);
            datalist.add(map);
        }
        String[] from={"img","text"};
        int[] to={R.id.img_tool_0,R.id.tv_tool_0};
        adapter=new SimpleAdapter(getActivity(), datalist, R.layout.tool_item_1, from, to);

        gv_tool.setAdapter(adapter);//向gridview中载入数据

        gv_tool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("提示").setMessage(datalist.get(arg2).get("text").toString()).create().show();
            }
        });

        /**
         * 搞个人信息表
         */
        tableLinerLayout = (LinearLayout) this.findViewById(R.id.MyTable);

        //初始化table
        for (int i = 0 ; i < table_text.length;i++){
            tLinearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_index_table, null);
            myTableTextView title = (myTableTextView) tLinearLayout.findViewById(R.id.list_1_1);
            title.setText(table_title[i]);
            title.setTextColor(Color.BLUE);
            myTableTextView tableText = (myTableTextView) tLinearLayout.findViewById(R.id.list_1_2);
            tableText.setText(table_text[i]);
            if(i==2){
                tableText.setTextColor(ColorsUtil.RED);
            }else{
                tableText.setTextColor(ColorsUtil.BLUE);
            }
            tableLinerLayout.addView(tLinearLayout);
        }
        /**
         * 申述按钮
         */
        btn_allege = (Button) findViewById(R.id.btn_allege);
        btn_allege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.init(getActivity());
                ToastUtil.showShort("..还没得上线(`@`)...");
            }
        });

        /**
         *
         */


    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {



    }

    @Override
    protected void setListener() {

    }


}
