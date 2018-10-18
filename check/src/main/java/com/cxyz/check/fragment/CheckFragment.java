package com.cxyz.check.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.adapter.FunctionAdapter;
import com.cxyz.check.constant.IDs;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.domain.TaskInfo;

/**
 * Created by 夏旭晨 on 2018/10/4.
 */
@Route(path = "/check/CheckFragment")
public class CheckFragment extends BaseFragment<ICheckPresenter> implements ICheckView,AdapterView.OnItemClickListener{

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
            iPresenter.checkTask();
            //getHoldingActivity().startActivity(DailyCheckActivity.class);
        }else if(id == IDs.SHORTTIMEID)
        {

        }
    }

    /**
     * TODO 需要显示一个dialog
     * @param taskInfo
     */
    @Override
    public void showTask(TaskInfo taskInfo) {
        LogUtil.e(taskInfo.toString());
    }

    @Override
    public void showNoTask(String info) {
        ToastUtil.showShort(info);
    }
}
