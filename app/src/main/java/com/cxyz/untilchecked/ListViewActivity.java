package com.cxyz.untilchecked;

import android.content.Context;
import android.widget.ListView;

import com.cxyz.commons.Adapter.AdapterBase;
import com.cxyz.commons.Adapter.ViewHolder;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 夏旭晨 on 2018/9/25.
 */

public class ListViewActivity extends BaseActivity {

    private ListView lv_example;

    private TitleView title;
    //listview需要的数据
    private ArrayList<Map<String,Object>> data;

    @Override
    public int getContentViewId() {
        return R.layout.activity_list_layout;
    }

    @Override
    public void initView() {
        lv_example = (ListView) findViewById(R.id.lv_show);
        title = (TitleView) findViewById(R.id.title);
        lv_example.setAdapter(new MyAdapter(getActivity(),data,R.layout.item_lv_example_layout));
    }

    @Override
    public void initData() {
        //装载数据
        data = new ArrayList<>();
        Map<String,Object> map = null;
        //这里做点假数据
        for(int i=0;i<10;i++)
        {
            map = new HashMap();
            map.put("text","hello"+i);
            map.put("img",R.mipmap.ic_launcher);
            data.add(map);
        }
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
    //这里的泛型就是你要装载的数据类型，可以是任意
    class MyAdapter extends AdapterBase<Map<String,Object>>{
        //一定要有这个构造方法
        public MyAdapter(Context mContext, List<Map<String, Object>> list, int mItemLayoutId) {
            super(mContext, list, mItemLayoutId);
        }

        @Override
        public void convertView(ViewHolder holder, Map<String, Object> item) {
            String text = (String)item.get("text");
            int img = (int)item.get("img");
            //这里在使用ViewHolder设置view，详细请看viewholder
            holder.setText(R.id.tv_text,text);
            holder.setImageResource(R.id.iv_img,img);
        }
    }
}
