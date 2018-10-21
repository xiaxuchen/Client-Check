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
import com.cxyz.check.ipresenter.IMyCheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IMyCheckPresenterImpl;
import com.cxyz.check.view.IMyCheckView;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.qmuiteam.qmui.widget.QMUIProgressBar;

import java.util.List;
import java.util.Map;

@Route(path = "/check/MyCheckFragment")
public class MyCheckFragment extends BaseFragment<IMyCheckPresenter> implements IMyCheckView {
    //出勤率
    private QMUIProgressBar qmuiProgressBar;
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
        iPresenter.showRecords();
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
    public void showRecords(List<Map<String, Object>> data,int times,int checkerror,int lateAndEarly,int absent,int progress) {
        el_checksituation.setAdapter(new RecordAdapter(data,getActivity()));
        tv_absent.setText("旷课"+absent+"次");
        tv_late.setText("迟到/早退"+lateAndEarly+"次");
        tv_checkerror.setText(checkerror+"项考勤异常");
        tv_dayinfo.setText(times+"次考勤共"+checkerror+"次项勤异常");
        qmuiProgressBar.setMaxValue(100);
        qmuiProgressBar.setProgress(progress);
        tvAnimate(0,progress);
        this.progress = progress;
        el_checksituation.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                ImageView iv_indicate = view.findViewById(R.id.iv_indicate);
                if(el_checksituation.isGroupExpanded(i))
                {
                    iv_indicate.setImageResource(R.mipmap.check_down);
                    LogUtil.e("down"+i);
                }
                else
                {
                    iv_indicate.setImageResource(R.mipmap.check_up);
                    LogUtil.e("up"+i);
                }
                return false;
            }
        });
    }

    @Override
    public void showError(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
}
