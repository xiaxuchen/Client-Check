package com.cxyz.check.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.activity.MyHistoryActivity;
import com.cxyz.check.adapter.RecordAdapter;
import com.cxyz.check.dto.CheckRecordDto;
import com.cxyz.check.ipresenter.IMyCheckPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IMyCheckPresenterImpl;
import com.cxyz.check.view.IMyCheckView;
import com.cxyz.commons.fragment.BaseFragment;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.logiccommons.typevalue.CheckRecordResult;
import com.qmuiteam.qmui.widget.QMUIProgressBar;

@Route(path = "/check/MyCheckFragment")
public class MyCheckFragment extends BaseFragment<IMyCheckPresenter> implements IMyCheckView {

    //出勤率
    private QMUIProgressBar qmuiProgressBar;
    //进度
    private ProgressBar pb_load;
    //异常详情
    private ListView lv_check;
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

    private RecordAdapter adapter;

    //进度
    private int progress;

    /**
     * 出勤率文字动画
     * @param progress 当前进度
     */
    private void tvAnimate(final int start,final int progress)
    {
        ValueAnimator animator = ValueAnimator.ofInt(start,progress);
        animator.setDuration(QMUIProgressBar.TOTAL_DURATION);
        animator.addUpdateListener(valueAnimator -> {
            tv_progress.setText(valueAnimator.getAnimatedValue().toString()+"%");
        });
        animator.start();
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        lv_check = findViewById(R.id.lv_check);
        qmuiProgressBar = findViewById(R.id.qmuiProgressBar);
        tv_progress = findViewById(R.id.tv_progress);
        tv_dayinfo = findViewById(R.id.tv_dayinfo);
        tv_checkerror = findViewById(R.id.tv_checkerror);
        tv_late = findViewById(R.id.tv_late);
        tv_absent = findViewById(R.id.tv_absent);
        pb_load = findViewById(R.id.pb_load);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(qmuiProgressBar == null || tv_progress == null)
            return;
        qmuiProgressBar.setMaxValue(100);
        if(!isVisibleToUser)
        {
            qmuiProgressBar.setProgress(0);
            tv_progress.setText(0+"%");
        }
        else
        {
            int start = qmuiProgressBar.getProgress();
            qmuiProgressBar.setProgress(this.progress);
            tvAnimate(start,this.progress);
        }
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
        //跳转至我的历史考勤页面
        lv_check.setOnItemClickListener((adapterView, view, i, l) ->
        {
            ToastUtil.showShort("触发");
            Bundle bundle = new Bundle();
            bundle.putInt("result",adapter.getItem(i).getResultType());
            getHoldingActivity().startActivity(MyHistoryActivity.class,bundle);
        });
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        //如果是第一次显示则加载数据，因为加载adapter会显示一次，所以真正第一次用户看到是在第二次
        if(first != 0)
            iPresenter.showRecords();
        first++;
    }



    @Override
    public void showRecords(CheckRecordDto checkRecordDto) {
        //由于进度和不良记录条数由计算得出，为避免重复计算使用变量记录
        int pro = checkRecordDto.getProgress();
        int badCount = checkRecordDto.getBadCount();

        //更新view
        tv_absent.setText("旷课"+checkRecordDto.getTypeCount(CheckRecordResult.ABSENTEEISM)+"次");
        tv_late.setText("迟到/早退"+(checkRecordDto.getTypeCount(CheckRecordResult.LATE)+
                checkRecordDto.getTypeCount(CheckRecordResult.LATE))+"次");
        tv_checkerror.setText(badCount+"项考勤异常");
        tv_dayinfo.setText(checkRecordDto.getAll()+"次考勤共"+badCount+"次项勤异常");
        qmuiProgressBar.setMaxValue(100);
        qmuiProgressBar.setProgress(pro);
        adapter = new RecordAdapter(getHoldingActivity(),
                checkRecordDto.getResults(),R.layout.item_check_layout);
        lv_check.setAdapter(adapter);

        //设置动画
        tvAnimate(0,pro);
        this.progress = pro;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoadingView() {
        pb_load.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        pb_load.setVisibility(View.GONE);
    }
}
