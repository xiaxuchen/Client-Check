package com.cxyz.check.activity;

import android.content.Intent;
import android.widget.ListView;

import com.cxyz.check.R;
import com.cxyz.check.adapter.HistoryAdapter;
import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.check.ipresenter.IHistoryPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IHistoryPresenterImpl;
import com.cxyz.check.view.IHistoryView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.widget.TitleView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2018/12/5.
 */

public class CheckHistoryActivity extends BaseActivity<IHistoryPresenter> implements IHistoryView{

    //考勤历史
    private ListView lv_history;

    //下拉刷新
    private RefreshLayout refresh;

    //列表的适配器
    private HistoryAdapter adapter;

    //list的空view
    private QMUIEmptyView ev_empty;

    private TitleView tv_title;

    @Override
    public int getContentViewId() {
        return R.layout.activity_check_history_layout;
    }

    @Override
    protected boolean eventBusEnable() {
        return true;
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
           iPresenter.getHistory();
        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            if(adapter == null)
                iPresenter.getHistory();
            iPresenter.loadMoreHistory(adapter.getCount());
        });
        lv_history.setOnItemClickListener((adapterView, view, i, l) -> {
            //跳转到修改页面
            Intent intent = new Intent(getActivity(),AlterCheckActivity.class);
            intent.putExtra("compId",adapter.getItem(i).getId());
            startActivity(intent);
        });
        tv_title.setBackClickListener(v -> onBackPressed());
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getHistory();
        ev_empty.setLoadingShowing(true);
        ev_empty.setTitleText("正在努力加载中...");
    }

    @Override
    protected IHistoryPresenter createIPresenter() {
        return new IHistoryPresenterImpl();
    }

    @Override
    public void finishRefresh(List<CheckHistoryDto> historyDtos) {
        if(adapter == null)
        {
            adapter = new HistoryAdapter(getActivity(),historyDtos,R.layout.item_history_layout);
            adapter.setListener((view, position) -> {
                Intent intent = new Intent(getActivity(),AlterCheckActivity.class);
                intent.putExtra("compId",adapter.getItem(position).getId());
                startActivity(intent);
            });
        }
        adapter.setList(historyDtos);
        adapter.notifyDataSetChanged();
        lv_history.setAdapter(adapter);
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
    public void finishLoad(List<CheckHistoryDto> dtos) {
        adapter.appendToList(dtos);
        adapter.notifyDataSetChanged();
        refresh.finishLoadMore(500);
    }

    @Override
    public void loadMoreFail(String error) {
        if(error.equals("＞▂＜ 已经到底了"))
            refresh.finishLoadMore(500,true,true);
        else
            refresh.finishLoadMore(false);
    }

    //TODO 后面加入更新而不是重新获取
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateHistory(AlterCheckActivity.UpdateHistory updateHistory)
    {
        iPresenter.getHistory();
    }
}
