package com.cxyz.untilchecked.activity;

import android.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.mine.fragment.MineFragment;
import com.cxyz.untilchecked.R;

public class HomeActivity extends FragmentActivity implements View.OnClickListener{

    private TitleView tv_title;

    private RadioButton rb_check;

    private RadioButton rb_home;

    private RadioButton rb_mine;

    private MineFragment mineFragment = MineFragment.newInstance();

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
        tv_title = (TitleView) findViewById(R.id.tv_title);
        rb_check = (RadioButton) findViewById(R.id.rb_check);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        tv_title.setTitle("主页");
        tv_title.setBack(0,"");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        rb_mine.setOnClickListener(this);
        rb_check.setOnClickListener(this);
        rb_home.setOnClickListener(this);
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
            case R.id.rb_home:{
                rb_mine.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_mine_off),null,null);
                rb_check.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_check_off),null,null);
                break;
            }
            case R.id.rb_mine:{
                rb_mine.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_mine_on),null,null);
                rb_check.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_check_off),null,null);
                switchFragment(mineFragment);
                break;
            }
            case R.id.rb_check:{
                rb_mine.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_mine_off),null,null);
                rb_check.setCompoundDrawablesWithIntrinsicBounds(null,getResources().
                        getDrawable(R.mipmap.app_check_on),null,null);
                break;
            }
        }
    }
}
