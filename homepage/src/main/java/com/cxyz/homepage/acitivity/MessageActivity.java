package com.cxyz.homepage.acitivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.date.Date;
import com.cxyz.homepage.R;
import com.cxyz.homepage.ipresenter.impl.MeassgesPresenterImpl;
import com.cxyz.homepage.iview.MessagesView;
import com.cxyz.homepage.myAdapter.MessageRVAdapter;
import com.cxyz.homepage.myAdapter.cell.MessageCell;
import com.cxyz.logiccommons.domain.User;
import com.cxyz.logiccommons.manager.UserManager;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.List;

/**
 * 这是一个消息页面
 */

public class MessageActivity extends BaseActivity<MeassgesPresenterImpl> implements MessagesView{
    private boolean isTaskInfo = true;
    private CalendarLayout calendarLayout;
    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private MessageRVAdapter messageRVAdapter;
//    private TaskInfoRVAdapter taskInfoRVAdapter;
//    private QMUIRoundButton btn_clazz;
//    private QMUIRoundButton btn_check;
    private User testU;
    @Override
    public int getContentViewId() {
        return R.layout.activity_message;
    }

    /**
     * 1
     */
    @Override
    public void initData() {
        calendarLayout = findViewById(R.id.calendarLayout);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        messageRVAdapter = new MessageRVAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(messageRVAdapter);
        testU = UserManager.getInstance().getUser();

    }

    /**
     * 2
     */
    @Override
    public void initView() {

    }

    /**
     * 3
     */
    @Override
    public void setEvent() {
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {//周试图与月视图切换监听
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                Date date = new Date();
                date.setYear(calendar.getYear()).setMonth(calendar.getMonth()).setDay(calendar.getDay());
                iPresenter.setMessagesCall(testU.getId(),date);
                messageRVAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    protected MeassgesPresenterImpl createIPresenter() {
        return new MeassgesPresenterImpl();
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void setMessages(List<MessageCell> recordDetailList) {
        messageRVAdapter.clear();
        messageRVAdapter.addAll(recordDetailList);
    }
}
