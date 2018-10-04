package com.cxyz.check.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cxyz.check.R;
import com.cxyz.check.adapter.FunctionAdapter;
import com.cxyz.check.constant.IDs;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.commons.IView.IBaseView;
import com.cxyz.commons.fragment.BaseFragment;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */

public class CheckFragment extends BaseFragment<ICheckPresenter> implements IBaseView,AdapterView.OnItemClickListener{

    private GridView gv_function;

    public static CheckFragment newInstance() {
        return new CheckFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_layout;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        gv_function = (GridView) findViewById(R.id.gv_function);

        gv_function.setAdapter(new FunctionAdapter(getActivity(),iPresenter.
                getGridViewInfo(),R.layout.item_grid_fun_layout));
    }

    @Override
    public void setListener() {
        gv_function.setOnItemClickListener(this);
    }

    @Override
    protected ICheckPresenter createIPresenter() {
        return new ICheckPresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(id == IDs.DAILYID)
        {

        }else if(id == IDs.SHORTTIMEID)
        {

        }
    }
}
