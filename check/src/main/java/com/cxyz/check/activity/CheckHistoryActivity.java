package com.cxyz.check.activity;

import android.widget.ListView;

import com.cxyz.check.R;
import com.cxyz.check.adapter.HistoryAdapter;
import com.cxyz.check.dto.CheckHistoryDto;
import com.cxyz.check.ipresenter.IHistoryPresenter;
import com.cxyz.check.ipresenter.ipresenterimpl.IHistoryPresenterImpl;
import com.cxyz.check.view.IHistoryView;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.ToastUtil;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

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

    @Override
    public int getContentViewId() {
        return R.layout.activity_check_history_layout;
    }

    @Override
    public void initView() {
        lv_history = findViewById(R.id.lv_history);
        refresh = findViewById(R.id.refresh);
        ev_empty = findViewById(R.id.ev_empty);
        lv_history.setEmptyView(ev_empty);
        ev_empty.show("暂无考勤历史",null);
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
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.getHistory();
        ev_empty.show(true,"正在努力加载中...",null,null,null);
    }

    @Override
    protected IHistoryPresenter createIPresenter() {
        return new IHistoryPresenterImpl();
    }

    @Override
    public void finishRefresh(List<CheckHistoryDto> historyDtos) {
        for (int i = 0;i<20;i++)
            historyDtos.add(historyDtos.get(0));
        adapter = new HistoryAdapter(getActivity(),historyDtos,R.layout.item_history_layout);
        lv_history.setAdapter(adapter);
        refresh.finishRefresh();
    }

    @Override
    public void refreshFail(String error) {
        ToastUtil.showShort(error);
        refresh.finishRefresh();
        ev_empty.show("暂无考勤历史",null);
    }

    @Override
    public void finishLoad() {

    }
}
