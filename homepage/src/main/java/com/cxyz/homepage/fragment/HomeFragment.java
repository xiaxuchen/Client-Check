package com.cxyz.homepage.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.acitivity.MessageActivity;
import com.cxyz.homepage.acitivity.PieChartActivity;
import com.cxyz.homepage.adapter.FunctionAdapter;
import com.cxyz.homepage.dto.CheckTaskDto;
import com.cxyz.homepage.ipresenter.IHomePresenter;
import com.cxyz.homepage.ipresenter.impl.IHomePresenterImpl;
import com.cxyz.homepage.iview.IHomeView;

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
        String texts[] = new String[]{"考勤","考勤图表","月历课次","请假申请",
                "班级课表","成绩情况","统计报告","其他信息"};
        int imgs[] = new int[]{R.mipmap.checkin,R.mipmap.checkcharts,R.mipmap.monthclass,
                R.mipmap.appointapply,R.mipmap.classlists,R.mipmap.gradescase,R.mipmap.statistics,
                R.mipmap.othercondition};
        data = new ArrayList<>();
        Map<String,Object> map = null;
        for(int i = 0;i<imgs.length;i++)
        {
            map = new HashMap<>();
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
                    case 0:
                    {
                        if(com.cxyz.logiccommons.manager.UserManager.getInstance().getUser().getPower() == 5)
                            ARouter.getInstance().build("/check/CheckActivity").navigation();//跳转至考勤页面
                        else
                            ToastUtil.showShort("您当前暂无此权限");
                            break;
                    }
                    case 2:getHoldingActivity().startActivity(MessageActivity.class);break;//跳转至日历课次;
                    case 6:getHoldingActivity().startActivity(PieChartActivity.class);break;//跳转到统计界面
                    default:ToastUtil.showShort("此功能正在扩充");
                }
            }
        });
    }



    @Override
    public void showTask(CheckTaskDto taskDto) {
        if(taskDto != null)
        {
            showDialog(taskDto);
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
    private void showDialog(final CheckTaskDto info)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("检查到考勤任务,是否考勤？");
        builder.setIcon(R.mipmap.common_logo);
        View view = View.inflate(getActivity(),R.layout.item_dialog_layout,null);
        TextView tv_task_name = (TextView) view.findViewById(R.id.tv_task_name);
        tv_task_name.setText(info.getName());
        TextView tv_task_tea = (TextView) view.findViewById(R.id.tv_task_tea);
        tv_task_tea.setText(info.getSponsorName());
        TextView tv_task_time = (TextView) view.findViewById(R.id.tv_task_time);
        tv_task_time.setText(DateUtil.dateToString(info.getStart(), DateUtil.DatePattern.ONLY_TIME)+
                "-"+DateUtil.dateToString(info.getEnd(), DateUtil.DatePattern.ONLY_TIME));
        TextView tv_task_place = (TextView) view.findViewById(R.id.tv_place);
        //TODO 这里以后需要改成name
        tv_task_place.setText(info.getSpot()==null?"":info.getSpot());
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ARouter.getInstance().build("/check/DailyCheckActivity").
                        withInt("compId",info.getId()).navigation();
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
