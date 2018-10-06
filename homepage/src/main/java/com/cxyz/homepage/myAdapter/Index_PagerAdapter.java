package com.cxyz.homepage.myAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxyz.commons.domain.TaskInfo;
import com.cxyz.homepage.R;
import com.cxyz.homepage.util.DataUtil;
import com.cxyz.homepage.util.GetRintId;

import java.util.List;

/**
 * Created by 鱼塘主 on 2018/9/25.
 */

public class Index_PagerAdapter extends PagerAdapter {
//TaskInfo
    private Context mContext;
    private List<TaskInfo> mData;  //存入当天课程对象
    private TaskInfo clazz;
    public Index_PagerAdapter(Context mContext, List<TaskInfo> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public float getPageWidth(int position) {
        return  0.35f;
    }

    @Override
    public int getCount() {                                 //获取载入布局的数量
        return mData.size()+1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        TextView clazz_name;

        if(position==0){
            //时间显示
            view = View.inflate(mContext, R.layout.pager_time_0,null);
            TextView time_show_data = (TextView) view.findViewById(R.id.data_show);
            TextView time_show_week = (TextView) view.findViewById(R.id.week_show);

            new DataUtil(time_show_data,time_show_week).start();//更新时间日期

        }else{

            int count = position-1;
            TaskInfo clazz = mData.get(count);
            //获取布局文件
            String lyout_r = "pager_kebiao_"+count;
            int layout_mr = GetRintId.getFieldValue("layout",lyout_r,mContext);
            view = View.inflate(mContext,layout_mr,null);

            //显示任务开始时间
            String tv_r_time = "clazz_"+count+"_time";
            int tv_time_r = GetRintId.getFieldValue("id",tv_r_time,mContext);
            TextView this_clazz_time = (TextView) view.findViewById(tv_time_r);
            this_clazz_time.setText(clazz.getStart()+"");


            //显示课程名
            String tv_r_name = "clazz_"+count+"_name";
            int tv_name_r = GetRintId.getFieldValue("id",tv_r_name,mContext);
            TextView this_clazz_name = (TextView) view.findViewById(tv_name_r);
            this_clazz_name.setText(clazz.get_name());


            //显示代课老师
            String tv_r_teacher = "clazz_"+count+"_teacher";
            int tv_teacher_r = GetRintId.getFieldValue("id",tv_r_teacher,mContext);
            TextView this_clazz_teacher = (TextView) view.findViewById(tv_teacher_r);
            this_clazz_teacher.setText(clazz.getSponser().get_name());



            //显示上课教室
            String tv_r_room = "clazz_"+count+"_room";
            int tv_room_r = GetRintId.getFieldValue("id",tv_r_room,mContext);
            TextView this_clazz_room = (TextView) view.findViewById(tv_room_r);
            this_clazz_room.setText(clazz.getClassRoom().get_name());

            //判断是否到了上给该科的周数  ps:优先级别 : 二
            /**
             * 需要数据:该课上课开始周数和结束周数
             *          当日上课时间第几节
             */
            //显示课程状态check(status in(下节课,上课中,上完了))



            //判断是否是节假日
            /**
             *
             */
            //显示课程状态: status :休假中

        }
        container.addView(view);
        return view;
    }
//    //pagerView的刷新方法
//        private void refresh() {
//           // mDataList.set(0, "更新数据源测试");
//            this.notifyDataSetChanged();
//        }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
