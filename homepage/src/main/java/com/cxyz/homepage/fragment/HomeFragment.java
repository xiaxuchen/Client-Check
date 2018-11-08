package com.cxyz.homepage.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.acitivity.DailyCheckActivitymore;
import com.cxyz.homepage.adapter.FunctionAdapter;
import com.cxyz.homepage.ipresenter.IHomePresenter;
import com.cxyz.homepage.ipresenter.impl.IHomePresenterImpl;
import com.cxyz.homepage.iview.IHomeView;
import com.cxyz.logiccommons.domain.TaskInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by 鱼塘主 on 2018/9/25.
 */
@Route(path="/homepage/HomeFragment")
public class HomeFragment extends BaseFragment<IHomePresenter> implements IHomeView{

    //Adapter需要的数据
    ArrayList<Map<String,Object>> data;
    //用于显示第几周
    private TextView tv_week;
    //用于显示日期
    private TextView tv_date;
    //用于显示准确位置
    private TextView tv_location;
    //签到
    private LinearLayout ll_check;
    //考勤图表
    private LinearLayout ll_checkpic;
    //月历课次
    private LinearLayout ll_month_lessons;
    //请假申请
    private LinearLayout ll_vacation;
    //班级课表
    private LinearLayout ll_grade_lessons;
    //成绩情况
    private LinearLayout ll_score;
    //统计报告
    private LinearLayout ll_statistic;
    //其他信息
    private LinearLayout ll_otherinfo;

    //功能
    private GridView gv_function;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle bundle) {
        int backs[] = new int[]{R.drawable.circleborder,R.drawable.circlebordertwo,
                R.drawable.circleborderthree,R.drawable.circleborderfour,R.drawable.circleborderfive
                ,R.drawable.circlebordersix,R.drawable.circleborderseven,R.drawable.circlebordereight};
        String texts[] = new String[]{"考勤","考勤图表","月历课次","请假申请",
                "班级课表","成绩情况","统计报告","其他信息"};
        int imgs[] = new int[]{R.drawable.checkin,R.drawable.checkcharts,R.drawable.monthclass,
                R.drawable.appointapply,R.drawable.classlist,R.drawable.gradescase,R.drawable.statistics,
                R.drawable.othercondition};
        data = new ArrayList<>();
        Map<String,Object> map = null;
        for(int i = 0;i<backs.length;i++)
        {
            map = new HashMap<>();
            map.put("back",backs[i]);
            map.put("text",texts[i]);
            map.put("img",imgs[i]);
            data.add(map);
        }
    }
    /**
     * 初始化view的
     * @param view mRootView
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        tv_week = findViewById(R.id.tv_week);
        tv_date = findViewById(R.id.tv_date);
        tv_location = findViewById(R.id.tv_location);
        gv_function = findViewById(R.id.gv_function);
        gv_function.setAdapter(new FunctionAdapter(getActivity(),data,R.layout.item_function_layout));
    }

    @Override
    protected IHomePresenter createIPresenter() {
        return new IHomePresenterImpl();
    }

    /**
     * 设置监听的
     */
    @Override
    protected void setListener() {
        gv_function.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:iPresenter.checkTask();break;
                    case 1:{
                        Intent intent=new Intent(getActivity().getApplicationContext(), DailyCheckActivitymore.class);
                        startActivity(intent);break;
                    }
                    default:ToastUtil.showShort("此功能正在扩充");
                }
            }
        });
    }


    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

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

    /**
     * 显示对话框
     * @param info
     */
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
                ARouter.getInstance().build("/check/DailyCheckActivitymore").
                        withInt("compId",info.getCompletion().get_id()).navigation();
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
