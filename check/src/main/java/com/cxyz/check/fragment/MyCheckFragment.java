package com.cxyz.check.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.adapter.RecordAdapter;
import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.check.ipresenter.IMyCheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IMyCheckPresenterImpl;
import com.cxyz.check.view.IMyCheckView;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = "/check/MyCheckFragment")
public class MyCheckFragment extends BaseFragment<IMyCheckPresenter> implements IMyCheckView {
    //出勤率
    private QMUIProgressBar qmuiProgressBar;
    //异常详情
    private ExpandableListView el_checksituation;
    //多少天多少异常
    private TextView tv_dayinfo;
    //旷课次数
    private TextView tv_absent;
    //迟到、早退次数
    private TextView tv_late;
    //出勤率tv
    private TextView tv_progress;
    //考勤异常
    private TextView tv_checkerror;
    //记录是否为第一次加载
    private int first = 0;

    //进度
    private int progress;


    private RecordAdapter adapter;



    @Override
    public void onHiddenChanged(boolean hidden) {
        qmuiProgressBar.setMaxValue(100);
        if(hidden)
        {
            qmuiProgressBar.setProgress(0);
        }
        else
        {
            qmuiProgressBar.setProgress(progress);
            tvAnimate(0,progress);
        }

    }

    /**
     * 出勤率文字动画
     * @param progress 当前进度
     * @param max 最大进度
     */
    private void tvAnimate(final int progress, final int max)
    {
        tv_progress.setText(progress+"%");
        if(progress == max)
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(10);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvAnimate(progress+1,max);
                    }
                });
            }
        }).start();
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        el_checksituation = (ExpandableListView) findViewById(R.id.el_checksituation);
        qmuiProgressBar = (QMUIProgressBar) findViewById(R.id.qmuiProgressBar);
        tv_progress = findViewById(R.id.tv_progress);
        tv_dayinfo = findViewById(R.id.tv_dayinfo);
        tv_checkerror = findViewById(R.id.tv_checkerror);
        tv_late = findViewById(R.id.tv_late);
        tv_absent = findViewById(R.id.tv_absent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mycheck_layout;
    }

    @Override
    protected void initData(Bundle bundle) {

    }


    @Override
    protected IMyCheckPresenter createIPresenter() {
        return new IMyCheckPresenterImpl();
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        //如果是第一次显示则加载数据，因为加载adapter会显示一次，所以真正第一次用户看到是在第二次
        if(first == 1)
            iPresenter.showRecords();
        first++;
    }


    /**
     * 进行数据转换获取List<Map<String,Object>>类型数据
     * @param infos 源数据
     * @return
     */
    private List<Map<String,Object>> parse(List<CheckRecordDto.RecordInfo> infos)
    {
        //各种考勤结果
        int types[] = {CheckRecordResult.ABSENTEEISM,CheckRecordResult.
                EARLYLEAVE,CheckRecordResult.LATE,CheckRecordResult.VACATE};

        //与考勤结果相对应的子view的数据List
        List<CheckRecordDto.RecordInfo> infoList[] = new List[4];

        List<Map<String,Object>> list = new ArrayList<>();


        //创建List对象
        for(int i = 0;i<infoList.length;i++)
        {
            infoList[i] = new ArrayList<>();
        }

        //将数据封装到list中
        for(CheckRecordDto.RecordInfo info:infos)
        {
            int i = 0;
            for(int type:types)
            {
                if(type == info.getResult())
                {
                    infoList[i].add(info);
                    break;
                }
                i++;
            }
        }

        for(int i = 0;i<infoList.length;i++)
        {
            if(infoList[i].size() > 0)
            {
                Map<String,Object> data = new HashMap<>();
                data.put("child",infoList[i]);
                data.put("parent",new RecordAdapter.ParentInfo(types[i],infoList[i].size()));
                list.add(data);
            }
        }

        LogUtil.e(list.toString());

        return list;
    }

    @Override
    public void showRecords(CheckRecordDto checkRecordDto) {

        //由于进度和不良记录条数由计算得出，为避免重复计算使用变量记录
        int pro = checkRecordDto.getProgress();
        int badCount = checkRecordDto.getBadCount();

        //更新view
        el_checksituation.setAdapter(new RecordAdapter(parse(checkRecordDto.getRecordInfos()),getActivity()));
        tv_absent.setText("旷课"+checkRecordDto.getAbsenteeism()+"次");
        tv_late.setText("迟到/早退"+checkRecordDto.getEarlyeave()+checkRecordDto.getLate()+"次");
        tv_checkerror.setText(badCount+"项考勤异常");
        tv_dayinfo.setText(checkRecordDto.getAll()+"次考勤共"+badCount+"次项勤异常");
        qmuiProgressBar.setMaxValue(100);
        qmuiProgressBar.setProgress(pro);

        //设置动画
        tvAnimate(0,pro);
        this.progress = pro;

        //设置监听
        el_checksituation.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                ImageView iv_indicate = view.findViewById(R.id.iv_indicate);
                if(el_checksituation.isGroupExpanded(i))
                {
                    iv_indicate.setImageResource(R.mipmap.check_down);
                }
                else
                {
                    iv_indicate.setImageResource(R.mipmap.check_up);
                }
                return false;
            }
        });
    }

    @Override
    public void showError(String error) {
        ToastUtil.showShort(error);
    }

}
