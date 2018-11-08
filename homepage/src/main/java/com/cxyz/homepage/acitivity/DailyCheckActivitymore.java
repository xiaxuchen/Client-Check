package com.cxyz.homepage.acitivity;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;
import com.cxyz.homepage.adapter.DailyChcekAdapter;
import com.cxyz.homepage.bean.DailyCheckBean;

import java.util.ArrayList;
import java.util.List;


public class DailyCheckActivitymore extends BaseActivity  {
    ListView lv_dailycheck_list;
    TitleView tv_dailycheck_title;
    private List<DailyCheckBean> mData;

    @Override
    public int getContentViewId() {
        return R.layout.activity_daily_checkmore_layout;
    }

    @Override
    public void initView() {
        lv_dailycheck_list = findViewById(R.id.lv_dailycheck_list);
        LayoutInflater inflater = getLayoutInflater();
        //创建自定义Adapter的对象
        DailyChcekAdapter adapter = new DailyChcekAdapter(inflater, mData);
        //将布局添加到ListView中
        lv_dailycheck_list.setAdapter(adapter);


        tv_dailycheck_title=findViewById(R.id.tv_dailycheck_title);
        tv_dailycheck_title.setTitle("日常考勤");
        tv_dailycheck_title.setOnClickListener(new TitleView.OnClickListener() {
            @Override
            public void onBackClick() {
                onBackPressed();
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onNameClick() {

            }

            @Override
            public void onSetClick() {

            }
        });
    }

    @Override
    public void initData() {
        mData = new ArrayList<DailyCheckBean>();
        DailyCheckBean tangmaru = new DailyCheckBean("唐马儒", "17478001", "2018.10.28", R.mipmap.member);
        DailyCheckBean lisi = new DailyCheckBean("李四", "17478002", "2018.10.29",R.mipmap.newspaper);
        DailyCheckBean zhangsan = new DailyCheckBean("张三", "17478003", "2018.10.30",R.mipmap.notice);
        mData.add(tangmaru);
        mData.add(zhangsan);
        mData.add(lisi);

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
}
