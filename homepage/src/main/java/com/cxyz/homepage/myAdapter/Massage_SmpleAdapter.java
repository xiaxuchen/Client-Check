package com.cxyz.homepage.myAdapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.utils.ColorsUtil;
import com.cxyz.homepage.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 鱼塘主 on 2018/9/29.
 * 加载学生的个人考勤信息listview的adapter
 * 未在这里设置监听
 */

public class Massage_SmpleAdapter extends AdapterBase{
    private Context mContext;
    private List<HashMap<String,String>> stu_massage;
    LayoutInflater inflater = null;
    String[] status = new String[]{"请假","缺勤","早退","迟到"};//存放考勤结果的字符数组
    int backC;//设置考勤结果TEXT的background的颜色
    /**
     *
     * @param mContext 上下文
     * @param stu_massage 考勤情况(结构:)
     * @param mItemLayoutId item布局id
     * @param inflater listlayoutid打气筒
     */
    public Massage_SmpleAdapter(Context mContext,List<HashMap<String, String>> stu_massage, int mItemLayoutId, LayoutInflater inflater){
        super(mContext, stu_massage, mItemLayoutId);
        this.stu_massage = stu_massage;
        this.inflater = inflater;
        this.mContext = mContext;
    }

    /**
     * 载入自己的出勤数据
     * @param holder view助手-->声明已经封装好了可以直接用了
     * @param item 这是个什么鬼?
     */
    @Override
    public void convertView(ViewHolder holder, Object item) {
        //载入数据
        int posit = holder.getPosition(); //获取item的位置
        String result = stu_massage.get(posit).get("考勤结果").trim();//获取考勤结果的文字内容

        ////载入考勤情况的数据
        holder.setText(R.id.item_tv_clazzname,stu_massage.get(posit).get("课程名"));
        holder.setText(R.id.item_tv_checkperson,stu_massage.get(posit).get("考勤人"));
        holder.setText(R.id.item_tv_checktime,stu_massage.get(posit).get("考勤时间"));
        holder.setText(R.id.item_tv_status,stu_massage.get(posit).get("考勤结果"));
        if (result.equals(status[0])){
            backC = ColorsUtil.BLUE;
        }else if(result.equals(status[1])){
            backC = ColorsUtil.RED;
        }else if(result.equals(status[2])){
            backC = ColorsUtil.YELLOW;
        }else if(result.equals(status[3])){
            backC = ColorsUtil.BLACK;
        }else{
            backC = ColorsUtil.LOWLIGHT;
        }
        holder.setBackgroundColor(R.id.item_tv_status,backC);


    }

}
