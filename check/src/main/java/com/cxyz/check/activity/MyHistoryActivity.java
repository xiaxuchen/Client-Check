package com.cxyz.check.activity;

import android.content.Intent;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.check.R;
import com.cxyz.check.adapter.MyHistoryAdapter;
import com.cxyz.check.dto.MyHistoryDto;
import com.cxyz.check.ipresenter.IMyHistoryPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IMyHistoryPresenterImpl;
import com.cxyz.check.view.IMyHistoryView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */
@Route(path = "/check/MyHistoryActivity")
public class MyHistoryActivity extends BaseActivity<IMyHistoryPresenter> implements IMyHistoryView{

    //考勤历史
    private ListView lv_history;

    //下拉刷新
    private RefreshLayout refresh;

    //列表的适配器
    private MyHistoryAdapter adapter;

    //list的空view
    private QMUIEmptyView ev_empty;

    private TitleView tv_title;

    private Integer result = -100;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_history_layout;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent != null);
            result = intent.getIntExtra("result",-100);//获取需要查看的考勤类型
        if(result == -100)
            result = null;
    }

    @Override
    public void initView() {
        QMUIStatusBarHelper.setStatusBarDarkMode(getActivity());
        lv_history = findViewById(R.id.lv_history);
        refresh = findViewById(R.id.refresh);
        refresh.setEnableAutoLoadMore(false);
        ev_empty = findViewById(R.id.ev_empty);
        tv_title = findViewById(R.id.tv_title);
        lv_history.setEmptyView(ev_empty);
    }

    @Override
    public void initData() {
    }

    @Override
    protected boolean isStateBar() {
        return false;
    }

    @Override
    public void setEvent() {
        refresh.setOnRefreshListener(refreshLayout -> {
            if(result == null)
            {
                iPresenter.getHistory(null);
                return;
            }

            if(result != -100)
                iPresenter.getHistory(result);
            else
            {
                refresh.finishRefresh(500,false);
                return;
            }
        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            if(result == -100)
            {
                refresh.finishLoadMore(500,false,false);
                return;
            }
            if(adapter == null)
            {
                iPresenter.getHistory(result);
                return;
            }
            iPresenter.loadMoreHistory(result,adapter.getCount());
        });
        tv_title.setBackClickListener(v -> onBackPressed());
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        if(result != -100)
            iPresenter.getHistory(result);
        ev_empty.setLoadingShowing(true);
        ev_empty.setTitleText("正在努力加载中...");
    }

    @Override
    protected IMyHistoryPresenter createIPresenter() {
        return new IMyHistoryPresenterImpl();
    }

    @Override
    public void finishRefresh(List<MyHistoryDto> historyDtos) {
        if(adapter == null)
        {
            adapter = new MyHistoryAdapter(getActivity(),historyDtos,R.layout.item_my_history_layout);
            lv_history.setAdapter(adapter);
        }else {
            adapter.setList(historyDtos);
            adapter.notifyDataSetChanged();
        }
        refresh.finishRefresh();
        ev_empty.setTitleText("暂无考勤历史");
        ev_empty.setLoadingShowing(false);
    }

    @Override
    public void refreshFail(String error) {
        refresh.finishRefresh();
        ev_empty.setTitleText("暂无考勤历史");
        ev_empty.setLoadingShowing(false);
    }

    @Override
    public void finishLoad(List<MyHistoryDto> dtos) {
        adapter.appendToList(dtos);
        adapter.notifyDataSetChanged();
        refresh.finishLoadMore(500);
    }

    @Override
    public void loadMoreFail(String error) {
        if(error.equals("没有更多数据了"))
            refresh.finishLoadMore(500,true,true);
        else
            refresh.finishLoadMore(false);
    }

}
