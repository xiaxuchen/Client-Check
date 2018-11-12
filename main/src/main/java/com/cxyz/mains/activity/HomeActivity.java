package com.cxyz.mains.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mains.R;
import com.cxyz.mains.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity implements View.OnClickListener{
    //标题栏
    private TitleView tv_title;

    /**
     * 各个tab的图标
     */
    private ImageView iv_check;

    private ImageView iv_home;

    private ImageView iv_mine;

    private TextView tv_check;

    /**
     * 各个tab的文字
     */
    private TextView tv_mine;

    private TextView tv_home;

    private LinearLayout ll_check;

    private LinearLayout ll_mine;

    //装载fragment的viewpager
    private ViewPager vp_content;

    //所需要展示的所以fragment
    private List<BaseFragment> fragmentList;

    private PagerAdapter pagerAdapter;

    private int[] ids;

    @Override
    public int getContentViewId() {
        return R.layout.activity_home_layout;
    }

    @Override
    public void initView() {
        iv_check = (ImageView) findViewById(R.id.iv_check);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_mine = (TextView) findViewById(R.id.tv_mine);
        tv_home = (TextView)findViewById(R.id.tv_home);
        tv_title = (TitleView) findViewById(R.id.tv_title);
        ll_check = (LinearLayout) findViewById(R.id.ll_check);
        ll_mine = (LinearLayout) findViewById(R.id.ll_mine);
        vp_content = findViewById(R.id.vp_content);
        //将fragment添加进viewpager
        vp_content.setAdapter(pagerAdapter);
        switchFragment(1,true);
        tv_title.setTitle("主页");
        tv_title.setBack(0,"");
    }

    @Override
    public void initData() {
        //获取所有的Fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(getFragment("/check/MyCheckFragment"));
        fragmentList.add(getFragment("/homepage/HomeFragment"));
        fragmentList.add(getFragment("/mine/MineFragment"));
        int i = 0;
        ids = new int[]{R.id.ll_check,R.id.iv_home,R.id.ll_mine};
        //初始化pagerAdapter
        pagerAdapter = new FragmentAdapter(getFragmentManager()) {
            @Override
            public android.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };

    }

    private BaseFragment getFragment(String fragmentName)
    {
        return (BaseFragment) ARouter.getInstance().
                build(fragmentName).navigation();
    }

    @Override
    public void setEvent() {
        ll_check.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        iv_home.setOnClickListener(this);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                switchFragment(position,false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    /**
     * 选择要显示的fragment
     */
    public void switchFragment(int position,boolean ismove)
    {
        int id = ids[position];
        if(id == R.id.ll_check)
        {
            iv_check.setImageResource(R.mipmap.app_statistic_on);
            iv_mine.setImageResource(R.mipmap.app_mine_off);
            iv_home.setImageResource(R.mipmap.app_home_off);
            tv_check.setTextColor(getResources().getColor(R.color.app_on));
            tv_mine.setTextColor(getResources().getColor(R.color.app_off));
            tv_home.setTextColor(getResources().getColor(R.color.app_off));
            tv_title.setTitle("统计");
        }
        else if(id == R.id.ll_mine)
        {
            iv_check.setImageResource(R.mipmap.app_statistic_off);
            iv_home.setImageResource(R.mipmap.app_home_off);
            iv_mine.setImageResource(R.mipmap.app_mine_on);
            tv_mine.setTextColor(getResources().getColor(R.color.app_on));
            tv_check.setTextColor(getResources().getColor(R.color.app_off));
            tv_home.setTextColor(getResources().getColor(R.color.app_off));
            tv_title.setTitle("我的");
        }
        else if(id == R.id.iv_home)
        {
            iv_check.setImageResource(R.mipmap.app_statistic_off);
            iv_mine.setImageResource(R.mipmap.app_mine_off);
            tv_mine.setTextColor(getResources().getColor(R.color.app_off));
            tv_check.setTextColor(getResources().getColor(R.color.app_off));
            tv_home.setTextColor(getResources().getColor(R.color.app_on));
            iv_home.setImageResource(R.mipmap.app_home_on);
            tv_title.setTitle("主页");
        }
        if(ismove)
        {
            vp_content.setCurrentItem(position);
            LogUtil.e(position+"");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int i = 0;
        for(int fid:ids)
        {
            if(fid==id)
            {
                switchFragment(i,true);
                break;
            }
            i++;
        }
    }

    @Override
    public int getFragmentContentId() {
        return 0;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }
}
