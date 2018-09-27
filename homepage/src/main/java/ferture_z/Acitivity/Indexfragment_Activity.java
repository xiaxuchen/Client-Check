package ferture_z.acitivity;

import com.cxyz.commons.IPresenter.IBasePresenter;
import com.cxyz.commons.activity.FragmentActivity;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.homepage.R;

import ferture_z.fragments.Index_fragment;


/**
 * Created by 鱼塘主 on 2018/9/25.
 */

public class Indexfragment_Activity extends FragmentActivity{

    @Override
    public int getFragmentContentId() {   //放fragmentlayuot的id
        return R.id.ceshi;
    }

    @Override
    protected BaseFragment getFirstFragment() {  //放第一个fragment的对象
        return Index_fragment.newInstance();
    }

    @Override
    public int getContentViewId() {     //放布局的id
        return R.layout.smaple_table;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
}
