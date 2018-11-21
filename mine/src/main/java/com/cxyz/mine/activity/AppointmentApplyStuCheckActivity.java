package com.cxyz.mine.activity;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.mine.R;
import com.cxyz.mine.adapter.AppoitmentAdapter;
import com.cxyz.mine.bean.AppointmentInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/28.
 */

public class AppointmentApplyStuCheckActivity extends BaseActivity {
    private ListView lv_applyment_applyinfo;
    private List<AppointmentInfoBean> mData;

    @Override
    public int getContentViewId() {
        return R.layout.activity_appointmentapplyteachcheck_layout;
    }

    @Override
    public void initView() {
        lv_applyment_applyinfo = findViewById(R.id.lv_applyment_applyinfo);
        LayoutInflater inflater = getLayoutInflater();
        //创建自定义Adapter的对象
        AppoitmentAdapter adapter = new AppoitmentAdapter(inflater, mData);
        //将布局添加到ListView中
        lv_applyment_applyinfo.setAdapter(adapter);


    }

    @Override
    public void initData() {
        mData = new ArrayList<AppointmentInfoBean>();
        AppointmentInfoBean tangmaru = new AppointmentInfoBean("唐马儒", "17478001", "2018.10.28", "心痛2009",
                "没法抗拒浓情蜜意  始终思念你 好想给你知 柔情常在心中想放肆  愿对你说声浓情莫变", R.mipmap.member);
        AppointmentInfoBean lisi = new AppointmentInfoBean("李四", "17478002", "2018.10.29", "心痛20010",
                "没法抗拒浓情蜜意  始终思念你 好想给你知 柔情常在心中想放肆  愿对你说声浓情莫变",R.mipmap.newspaper);
        AppointmentInfoBean zhangsan = new AppointmentInfoBean("张三", "17478003", "2018.10.30", "心痛20011",
                "没法抗拒浓情蜜意  始终思念你 好想给你知 柔情常在心中想放肆  愿对你说声浓情莫变", R.mipmap.notice);
        mData.add(tangmaru);
        mData.add(zhangsan);
        mData.add(lisi);




      /*  Student zhangsan  = new Student("张三", "30", "男", "喜欢玩游戏",R.mipmap.header_icon );
        Student lisi  = new Student("李四", "18", "女", "喜欢读书",R.mipmap.header_icon );
        Student wangwu  = new Student("王五", "25", "男", "喜欢运动",R.mipmap.header_icon );
        Student zhaoliu  = new Student("赵六", "22", "男", "喜欢吃饭",R.mipmap.header_icon );
        mData.add(zhangsan);
        mData.add(lisi);
        mData.add(wangwu);
        mData.add(zhaoliu);
*/

    }

    @Override
    public void setEvent() {

    }

    @Override
    protected IBasePresenter createIPresenter() {
        return null;
    }
}
