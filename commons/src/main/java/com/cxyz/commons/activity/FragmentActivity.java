package com.cxyz.commons.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * <h1 style="color:red">注意:不要重写onkeydown</h1>
 * 当使用的Activity含有Fragment，继承此activity<br/>
 * 需要跳转到其他Fragment则使用addFragment<br/>
 * 销毁当前Fragment则使用removeActivity
 *
 */

public abstract class FragmentActivity<p extends IBasePresenter> extends BaseActivity<p> {

    /**
     * 获取Fragment在布局文件中的id
     *
     * @return
     */
    public abstract int getFragmentContentId();

    /**
     * 获取第一个fragment
     */
    protected abstract BaseFragment getFirstFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void afterSetContent() {
        super.afterSetContent();
        if (null != getIntent()) {
            handleIntent(getIntent());
        } //避免重复添加Fragment
        if (getFragmentManager().getBackStackEntryCount()<1) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
    }

    /**
     * 添加fragment
     */
    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {

            getFragmentManager()
                    .beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }

    /**
     * 移除fragment
     */
    public void removeFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 返回键返回事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

