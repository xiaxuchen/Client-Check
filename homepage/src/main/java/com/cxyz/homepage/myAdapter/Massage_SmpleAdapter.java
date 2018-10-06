package com.cxyz.homepage.myAdapter;

import android.content.Context;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.domain.CheckRecord;
import com.cxyz.commons.domain.RecordDetail;
import com.cxyz.commons.utils.ColorsUtil;
import com.cxyz.homepage.R;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/9/29.
 * 加载学生的个人考勤信息listview的adapter
 * 未在这里设置监听
 */

public class Massage_SmpleAdapter extends AdapterBase{
    private Context mContext;
    private List<RecordDetail> stu_massage;
    String[] status = new String[]{"请假","缺勤","早退","迟到"};//存放考勤结果的字符数组
    int backC;//设置考勤结果TEXT的background的颜色
    /**
     *
     * @param mContext 上下文
     * @param stu_massage 考勤情况(结构:)
     * @param mItemLayoutId item布局id
     */
    public Massage_SmpleAdapter(Context mContext,List<RecordDetail> stu_massage, int mItemLayoutId){
        super(mContext, stu_massage, mItemLayoutId);
        this.stu_massage = stu_massage;
        this.mContext = mContext;
    }
    /**
     * 载入自己的出勤数据
     * @param holder view助手-->声明已经封装好了可以直接用了
     * @param item
     */
    @Override
    public void convertView(ViewHolder holder, Object item) {
        //载入数据
        int posit = holder.getPosition(); //获取item的位置
        String result;//获取考勤结果的文字内容
        switch (stu_massage.get(posit).getResult()){
            case CheckRecord.ABSENTEEISM:result="缺勤";break;// 缺勤
            case CheckRecord.VACATE:result="请假";break;// 请假
            case CheckRecord.LATE:result="迟到";break;// 迟到
            case CheckRecord.EARLYLEAVE:result="早退";break;// 早退
            default:result = "异常";break;
        }


        ////载入考勤情况的数据
        holder.setText(R.id.item_tv_clazzname,stu_massage.get(posit).get_name());
        holder.setText(R.id.item_tv_checkperson,stu_massage.get(posit).getChecker().get_name());
        holder.setText(R.id.item_tv_checktime,stu_massage.get(posit).getCheckTime().toString());
        holder.setText(R.id.item_tv_status,result);
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
