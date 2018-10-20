package com.cxyz.check.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.activity.DailyCheckActivity;
import com.cxyz.check.adapter.FunctionAdapter;
import com.cxyz.check.constant.IDs;
import com.cxyz.check.ipresenter.ICheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.ICheckPresenterImpl;
import com.cxyz.check.view.ICheckView;
import com.cxyz.commons.fragment.BaseFragment;
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
        if(taskInfo != null)
        {
            showDialog(taskInfo);
        }
    }

    @Override
    public void showNoTask(String info) {
        ToastUtil.showShort(info);
    }

    private void showDialog(final TaskInfo info)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("检查到考勤任务,是否考勤？");
        builder.setIcon(R.mipmap.common_logo);
        View view = View.inflate(getActivity(),R.layout.item_dialog_layout,null);
        TextView tv_task_name = (TextView) view.findViewById(R.id.tv_task_name);
        tv_task_name.setText(info.get_name());
        TextView tv_task_tea = (TextView) view.findViewById(R.id.tv_task_tea);
        tv_task_tea.setText(info.getSponser().get_name());
        TextView tv_task_time = (TextView) view.findViewById(R.id.tv_task_time);
        tv_task_time.setText(info.getStart().getHour()+":"+info.getStart().getMinute()
                +"-"+info.getEnd().getHour()+":"+info.getEnd().getMinute());
        TextView tv_task_place = (TextView) view.findViewById(R.id.tv_place);
        //TODO 这里以后需要改成name
        tv_task_place.setText(info.getClassRoom()==null?"":info.getClassRoom().get_id()+"");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), DailyCheckActivity.class);
                intent.putExtra("compId",info.getCompletion().get_id());
                startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
