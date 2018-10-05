package com.cxyz.commons.activity;

import android.view.KeyEvent;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * <h1 style="color:red">注意:不要重写onkeydown</h1>
 * 当使用的Activity含有Fragment，继承此activity<br><br/>
 * 需要跳转到其他Fragment则使用addFragment<br><br/>
 * 销毁当前Fragment则使用removeActivity
 *
 */

public abstract class FragmentActivity<p extends IBasePresenter> extends BaseActivity<p> {

    /**
     * @return Fragment在activity的布局文件中的父控件的id
     */
    public abstract int getFragmentContentId();

    /**
     * 获取第一个显示的fragment
     */
    protected abstract BaseFragment getFirstFragment();

    /**
     * 在设置布局文件之后显示第一个Fragment，如果getFirstFragment返回null则不显示
     */
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
     * 显示fragment，并将Fragment放入Fragment栈中，类似于startActivity
     * @param fragment 需要显示的Fragment
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
     * 将位于栈首的Fragment退栈，类似与Activity的Finish
     */
    public void removeFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 当按返回键时，Fragment栈中还有Fragment则退栈，若没有，则finish
     * @param keyCode
     * @param event
     * @return
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

