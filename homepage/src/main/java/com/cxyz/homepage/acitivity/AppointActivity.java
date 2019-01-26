package com.cxyz.homepage.acitivity;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.homepage.R;
import com.cxyz.homepage.adapter.AppointdListAdapter;
import com.cxyz.homepage.bean.AppointListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${喻济生} on 2018/12/29.
 */

public class AppointActivity extends BaseActivity {
    private TitleView tv_appoint_title;
    private ListView lv_appoint_list;
    private List<AppointListBean> mData;
    @Override
    public int getContentViewId() {
        return R.layout.activity_appointment_layout;
    }

    @Override
    public void initView() {
        tv_appoint_title=findViewById(R.id.tv_appoint_title);
        lv_appoint_list=findViewById(R.id.lv_appoint_list);
        tv_appoint_title.setTitle("请假申请");
        LayoutInflater inflater = getLayoutInflater();
        //创建自定义Adapter的对象
        AppointdListAdapter adapter = new AppointdListAdapter(inflater, mData);
        //将布局添加到ListView中
        lv_appoint_list.setAdapter(adapter);
    }

    @Override
    public void initData() {
        mData = new ArrayList<AppointListBean>();
        AppointListBean tangmaru = new AppointListBean("唐马儒", "17478001", "2018-11-11-11:00",
                "2018-11-12-14:00","两节课","肚子痛啊 ", "赵铁柱","院级","同意了 ，明天早上第二节课来我办公室","通过",R.drawable.beauty);
        AppointListBean zhangsan = new AppointListBean("李雷", "17478002", "2018-11-12-12:00",
                "2018-13-12-14:00","一整天","感觉身体被掏空 ", "赵铁柱","院级","同意了 ，明天早上第二节课来我办公室","通过",R.drawable.beauty);

        mData.add(tangmaru);
        mData.add(zhangsan);
    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
}
