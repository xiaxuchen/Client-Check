package com.cxyz.untilchecked.activity;

import android.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyz.check.fragment.CheckFragment;
import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.fragment.MineFragment;
import com.cxyz.untilchecked.R;

public class HomeActivity extends FragmentActivity implements View.OnClickListener{

    private TitleView tv_title;

    private ImageView iv_check;

    private ImageView iv_home;

    private ImageView iv_mine;

    private TextView tv_check;

    private TextView tv_mine;

    private LinearLayout ll_check;

    private LinearLayout ll_mine;

    private MineFragment mineFragment = MineFragment.newInstance();

    private CheckFragment checkFragment = CheckFragment.newInstance();

    private BaseFragment currentFragment = mineFragment;


    @Override
    public int getFragmentContentId() {
        return R.id.fl_content;
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

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
        tv_title = (TitleView) findViewById(R.id.tv_title);
        ll_check = (LinearLayout) findViewById(R.id.ll_check);
        ll_mine = (LinearLayout) findViewById(R.id.ll_mine);
        tv_title.setTitle("主页");
        tv_title.setBack(0,"");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        ll_check.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        iv_home.setOnClickListener(this);
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
     * @param targetFragment
     */
    public void switchFragment(BaseFragment targetFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.fl_content, targetFragment,
                    targetFragment.getClass().getSimpleName()).show(targetFragment);
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_check:{
                iv_check.setImageResource(R.mipmap.app_check_on);
                iv_mine.setImageResource(R.mipmap.app_mine_off);
                tv_check.setTextColor(getResources().getColor(R.color.app_on));
                tv_mine.setTextColor(getResources().getColor(R.color.app_off));
                switchFragment(checkFragment);
                tv_title.setTitle("考勤");
                break;
            }
            case R.id.ll_mine:{
                iv_check.setImageResource(R.mipmap.app_check_off);
                iv_mine.setImageResource(R.mipmap.app_mine_on);
                tv_mine.setTextColor(getResources().getColor(R.color.app_on));
                tv_check.setTextColor(getResources().getColor(R.color.app_off));
                switchFragment(mineFragment);
                tv_title.setTitle("我的");
                break;
            }
            case R.id.iv_home:{
                iv_check.setImageResource(R.mipmap.app_check_off);
                iv_mine.setImageResource(R.mipmap.app_mine_off);
                tv_mine.setTextColor(getResources().getColor(R.color.app_off));
                tv_check.setTextColor(getResources().getColor(R.color.app_off));
                tv_title.setTitle("主页");
                break;
            }
        }
    }
}
