package com.cxyz.homepage.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.DateUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.acitivity.CheckRedordActivity;
import com.cxyz.homepage.acitivity.ClazzActivity;
import com.cxyz.homepage.acitivity.ExportActivity;
import com.cxyz.homepage.adapter.FunctionAdapter;
import com.cxyz.homepage.dto.CheckTaskDto;
import com.cxyz.homepage.ipresenter.IHomePresenter;
import com.cxyz.homepage.ipresenter.impl.IHomePresenterImpl;
import com.cxyz.homepage.iview.IHomeView;
import com.cxyz.logiccommons.manager.UserManager;
import com.cxyz.logiccommons.typevalue.PowerType;

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

    //功能
    private GridView gv_function;

    private int indexs[];

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
        String texts[] = new String[]{"考勤","考勤统计","班级课表","请假申请","上传假条","请假审核","数据导出","导入数据"};
        int imgs[] = new int[]{R.mipmap.checkin, R.mipmap.checkcharts, R.mipmap.monthclass,
                R.mipmap.appointapply, R.mipmap.classlists, R.mipmap.gradescase, R.mipmap.statistics,
                R.mipmap.othercondition};
        data = new ArrayList<>();
        Map<String, Object> map = null;
        switch (UserManager.getInstance().getUser().getPower())
        {
            case PowerType.STU_NORMAL:
                {
                    indexs= new int[]{2, 3, 4};
                    for (int index:indexs) {
                        map = new HashMap<>();
                        map.put("text", texts[index]);
                        map.put("img", imgs[index]);
                        data.add(map);
                    }
                }break;
            case PowerType.STU_CHECKER:
                {
                    indexs = new int[]{0, 2, 3, 4, 7};
                    for (int index:indexs) {
                        map = new HashMap<>();
                        map.put("text", texts[index]);
                        map.put("img", imgs[index]);
                        data.add(map);
                    }
                }break;

            default:
            {
                indexs = new int[]{0, 1, 5, 6};
                for (int index:indexs) {
                    map = new HashMap<>();
                    map.put("text", texts[index]);
                    map.put("img", imgs[index]);
                    data.add(map);
                }
            }break;

        }
    }
    /**
     * 初始化view的
     * @param view mRootView
     * @param savedInstanceState
     */
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
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
        gv_function.setOnItemClickListener((adapterView, view, i, l) -> {

            switch (indexs[i])
            {
                case 0:
                    {
                        ARouter.getInstance().build("/check/CheckActivity").navigation();break;//跳转至考勤页面
                    }
                case 1:
                    {
                        getHoldingActivity().startActivity(CheckRedordActivity.class);break;//跳转至考勤图表;
                    }
                case 2:
                    {
                        getHoldingActivity().startActivity(ClazzActivity.class);break;//跳转至日历课次;
                    }
                case 3:
                    {
                        ARouter.getInstance().build("/vac/VacateActivity").navigation();break;
                    }
                case 4:
                    {
                        ToastUtil.showShort("此功能正在开发中...");break;
                    }
                case 5:
                    {
                        ARouter.getInstance().build("/vac/AuditActivity").navigation();break;
                    }
                case 6:
                    {
                        getHoldingActivity().startActivity(ExportActivity.class);break;
                    }
                case 7:
                    {
                        ARouter.getInstance().build("/info/UploadActivity").navigation();break;
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
