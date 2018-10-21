package com.cxyz.check.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxyz.check.R;
import com.cxyz.logiccommons.domain.CheckRecord;
import com.cxyz.logiccommons.domain.RecordDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordAdapter extends BaseExpandableListAdapter {
    Map<Integer,View> viewMap = new HashMap<>();
    //需要装填的数据
    private List<Map<String,Object>> data;

    private Context context;


    public RecordAdapter(List<Map<String,Object>> data, Context context)
    {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return ((List<RecordDetail>)data.get(i).get("child")).size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i).get("parent");
    }

    @Override
    public Object getChild(int i, int i1) {
        return ((List<RecordDetail>)data.get(i).get("child")).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return Integer.parseInt(i+""+i1);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if((view=viewMap.get(i))==null)
        {
            view = View.inflate(context,R.layout.item_expand_parent,null);
            viewMap.put(i,view);
            ImageView iv_icon = view.findViewById(R.id.iv_icon);
            TextView tv_info = view.findViewById(R.id.tv_info);
            ParentInfo group = (ParentInfo) getGroup(i);
            iv_icon.setImageResource(group.pic);
            tv_info.setText(group.info);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder = null;
        if (view == null){
            view = View.inflate(context, R.layout.item_expand_child, null);
            holder = new ChildViewHolder();
            holder.tv_lesson_name = view.findViewById(R.id.tv_lesson_name);
            holder.tv_date = view.findViewById(R.id.tv_date);
            holder.tv_time = view.findViewById(R.id.tv_time);
            holder.tv_des = view.findViewById(R.id.tv_des);
            view.setTag(holder);
        }else{
            holder = (ChildViewHolder) view.getTag();
        }
        RecordDetail item = (RecordDetail) getChild(i, i1);

        holder.tv_lesson_name.setText(item.get_name());
        holder.tv_date.setText(item.getCheckTime().getDate());
        holder.tv_time.setText(item.getCheckTime().getTime());
        holder.tv_des.setText(item.getDes()==null?"":item.getDes());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class ParentViewHolder{
        //图标
        public ImageView iv_icon;
        //信息
        public TextView tv_info;
        //指示器
        public ImageView iv_indicate;
    }

    class ChildViewHolder{
        //课程名
        public TextView tv_lesson_name;
        //日期
        public TextView tv_date;
        //时间
        public TextView tv_time;
        //描述
        public TextView tv_des;
    }

    public static class ParentInfo{
        //图标，根据result设置图标
        public int pic;
        //父view标题信息
        public String info;

        public ParentInfo(int result,int count)
        {
            switch (result)
            {
                case CheckRecord.ABSENTEEISM:
                {
                    pic = R.mipmap.check_icon_red;
                    info = count+"次缺勤记录";
                }break;
                case CheckRecord.LATE:
                {
                    pic = R.mipmap.check_icon_yellow;
                    info = count+"次迟到记录";
                }break;
                case CheckRecord.VACATE:
                {
                    pic = R.mipmap.check_icon_green;
                    info = count+"次请假记录";
                }break;
                case CheckRecord.EARLYLEAVE:
                {
                    pic = R.mipmap.check_icon_blue;
                    info = count+"次早退记录";
                }break;
            }
        }
    }
}