package com.cxyz.homepage.acitivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.GsonUtil;
import com.cxyz.commons.utils.HttpUtil.listener.DisposeDataListener;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.constant.RequestCenter;
import com.cxyz.homepage.ipresenter.MassageListPresenter;
import com.cxyz.homepage.iview.MassageListView;
import com.cxyz.homepage.myAdapter.MessageRVAdapter;
import com.cxyz.homepage.myAdapter.TaskInfoRVAdapter;
import com.cxyz.homepage.myAdapter.cell.TaskInfoCell;
import com.cxyz.homepage.myAdapter.cell.MessageCell;
import com.cxyz.logiccommons.domain.RecordDetail;
import com.cxyz.logiccommons.domain.TaskInfo;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个消息页面
 */

public class MessageActivity extends BaseActivity<MassageListPresenter> implements MassageListView{
    private boolean isTaskInfo = true;
    private CalendarLayout calendarLayout;
    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private MessageRVAdapter messageRVAdapter;
    private TaskInfoRVAdapter taskInfoRVAdapter;
    private QMUIRoundButton btn_clazz;
    private QMUIRoundButton btn_check;
    @Override
    public int getContentViewId() {
        return R.layout.activity_message;
    }

    /**
     * 1
     */
    @Override
    public void initData() {
        //UserManager.getInstance().getUser().get_id();
        // iPresenter.getMassageData(UserManager.getInstance().getUser().get_id());

    }

    /**
     * 2
     */
    @Override
    public void initView() {
        calendarLayout = findViewById(R.id.calendarLayout);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        btn_check = findViewById(R.id.btn_check);
        btn_clazz = findViewById(R.id.btn_clazz);
        messageRVAdapter = new MessageRVAdapter();
        taskInfoRVAdapter = new TaskInfoRVAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 3
     */
    @Override
    public void setEvent() {
        btn_clazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTaskInfo=true;
                messageRVAdapter.clear();
                taskInfoRVAdapter.notifyDataSetChanged();
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTaskInfo=false;
                taskInfoRVAdapter.clear();
                messageRVAdapter.notifyDataSetChanged();
            }
        });
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                Date date = new Date();
                date.setYear(calendar.getYear()).setMonth(calendar.getMonth()).setDay(calendar.getDay());
                if(isTaskInfo()){
                    RequestCenter.getTaskInfos(122, date, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            List<TaskInfo> taskInfos = GsonUtil.GsonToList(responseObj.toString(), TaskInfo.class);
                            List<TaskInfoCell> taskInfoCells = new ArrayList<>();
                            for (int j = 0 ; j < taskInfos.size() ; j++){
                                    taskInfoCells.add(new TaskInfoCell(taskInfos.get(j)));
                            }
                            taskInfoRVAdapter.clear();
                            taskInfoRVAdapter.addAll(taskInfoCells);
                            recyclerView.setAdapter(taskInfoRVAdapter);
                            LogUtil.e(taskInfos.toString());
                        }

                        @Override
                        public void onFailure(Object error) {
                            LogUtil.e(error.toString());
                        }
                    });
                }else{
                    RequestCenter.getRecords("17478093", 0, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            List<RecordDetail> recordDetails = GsonUtil.GsonToList(responseObj.toString(),RecordDetail.class);
                            List<MessageCell> userClazzsCells = new ArrayList<>();
                            for (int j = 0 ; j < recordDetails.size() ; j++){
                                userClazzsCells.add(new MessageCell(recordDetails.get(j)));
                            }
                            messageRVAdapter.clear();
                            messageRVAdapter.addAll(userClazzsCells);
                            recyclerView.setAdapter(taskInfoRVAdapter);
                            LogUtil.e(recordDetails.toString());
                        }

                        @Override
                        public void onFailure(Object error) {
                            ToastUtil.showShort(error+"");
                        }
                    });
                }

            }
        });
    }

    /**
     * 判断显示课程还是时间
     * @return
     */
    public boolean isTaskInfo(){
        return isTaskInfo;
    }
    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    protected MassageListPresenter createIPresenter() {
        return null;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected boolean isStateBar() {
        return true;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }
    @Override
    public void setListItem(List<RecordDetail> listItem) {
      //  lv_massage.setAdapter(new Massage_SmpleAdapter(getActivity(),listItem,R.layout.item_massage));
    }

}
