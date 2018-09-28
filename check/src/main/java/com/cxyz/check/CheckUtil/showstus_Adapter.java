package com.cxyz.check.CheckUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cxyz.check.CheckView.Iitem_ListView;
import com.cxyz.check.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 28058 on 2018/9/26.
 */

    public class showstus_Adapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> list;
    private LayoutInflater layoutInflater;
    private Context context;


    public showstus_Adapter(Context context, ArrayList<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Iitem_ListView item = null;
        if (convertView == null) {
            item = new Iitem_ListView();
            // 获取组件布局
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            item.stu_name = (TextView) convertView.findViewById(R.id.stu_name);
            item.stu_info = (TextView) convertView.findViewById(R.id.stu_info);

            // 这里要注意，是使用的tag来存储数据的。
            convertView.setTag(item);
        } else {
            item = (Iitem_ListView) convertView.getTag();
        }

			 /*获取map中对应的数据的数据,然后显示在ListView中*/
        item.stu_name.setText((String) list.get(position).get("stu_name"));
        item.stu_info.setText((String) list.get(position).get("stu_info"));

        return convertView;
    }

}
