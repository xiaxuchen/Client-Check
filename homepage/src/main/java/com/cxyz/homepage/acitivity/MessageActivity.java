package com.cxyz.homepage.acitivity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.feature_z_domain.TestTask;
import com.cxyz.homepage.ipresenter.MassageListPresenter;
import com.cxyz.homepage.ipresenter.mpl.MassageListPresenterImpl;
import com.cxyz.homepage.iview.MassageListView;
import com.cxyz.homepage.myAdapter.MyRecyleViewAdapter;
import com.cxyz.homepage.myAdapter.cell.UserClazzsCell;
import com.cxyz.logiccommons.domain.RecordDetail;
import com.cxyz.logiccommons.domain.Student;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个消息页面
 */

public class MessageActivity extends BaseActivity<MassageListPresenter> implements MassageListView {
    //private ListView lv_massage;
    //   private CalendarDateView calendarDateView;
    private ImageView img_back;
    private List list,lists;//搞点假数据
    private Student stu;//搞点假数据
    private TestTask testTask1 = new TestTask("程序设计基础", "爱的痕迹发撒的回复萨兰斯克的叫法是觉得电视机分厘卡即使对方", "10/23 20:21", 1);
    private TestTask testTask2 = new TestTask("高等数学", "爱的痕迹发撒的回复萨兰斯克的叫法是觉得电视机分厘卡即使对方", "10/23 20:21", 2);
    private TestTask testTask3 = new TestTask("大学英语", "爱的痕迹发撒的回复萨兰斯克的叫法是觉得电视机分厘卡即使对方", "10/23 20:21", 3);
    private TestTask testTask4 = new TestTask("javaWEB", "爱的痕迹发撒的回复萨兰斯克的叫法是觉得电视机分厘卡即使对方", "10/23 20:21", 4);
    private TestTask testTask5 = new TestTask("离散数学", "爱的痕迹发撒的回复萨兰斯克的叫法是觉得电视机分厘卡即使对方", "10/23 20:21", 1);
    private com.haibin.calendarview.CalendarView calendarView;
    private CalendarLayout calendarLayout;
    private RecyclerView recyclerView;
    private MyRecyleViewAdapter myRecyleViewAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_message;
    }



    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    public void initData() {
        ToastUtil.showLong("我是主页");
//        //UserManager.getInstance().getUser().get_id()
//      //  iPresenter.getMassageData(UserManager.getInstance().getUser().get_id());
        list = new ArrayList();
        list.add(testTask1);
        list.add(testTask2);
        list.add(testTask3);
        list.add(testTask4);
        list.add(testTask5);
        lists = new ArrayList();
        lists.add(testTask1);
        myRecyleViewAdapter = new MyRecyleViewAdapter();
        for(int i = 0 ; i < list.size(); i++){
            myRecyleViewAdapter.add(new UserClazzsCell(list));
        }

       // myRecyleViewAdapter.setData(list);
        Log.e(".();","tmd");


    }
    @Override
    public void initView() {
        // lv_massage = (ListView)findViewById(R.id.lv_massage);
        // calendarDateView = (CalendarDateView)findViewById(R.id.calendarDateView);
        recyclerView = findViewById(R.id.recyclerView);
        calendarView = findViewById(R.id.calendarView);
        calendarLayout = findViewById(R.id.calendarLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(myRecyleViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
    /**
     * 模拟从服务器取数据
     */
//    private void loadData() {
//        View loadingView = LayoutInflater.from(this).inflate(R.layout.item_error, null);
//        myRecyleViewAdapter.showLoading(loadingView);
//        recyclerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                myRecyleViewAdapter.hideLoading();
//                Log.e("myrun","tmd");
//                myRecyleViewAdapter.addAll(getCells(list));
//            }
//        }, 2000);
//    }

    /**
     * 设置事件
     */
    @Override
    public void setEvent() {
        calendarView.setOnViewChangeListener(new CalendarView.OnViewChangeListener() {
            @Override
            public void onViewChange(boolean isMonthView) {
                LogUtil.e("setOnViewChangeListener"+isMonthView);
                if(isMonthView){
                    ToastUtil.showShort("我是月视图");
                    myRecyleViewAdapter.clear();

                    for(int i = 0 ; i < list.size(); i++){
                        myRecyleViewAdapter.add(new UserClazzsCell(list));
                    }
                }else{
                    ToastUtil.showShort("我是周视图");
                    myRecyleViewAdapter.clear();
                    myRecyleViewAdapter.add(new UserClazzsCell(lists));

                }
            }
        });
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                ToastUtil.showShort(""+calendar.getDay());

                iPresenter.getMassageData("17478090",calendar.getMonth()+"-"+calendar.getDay());
            }
        });
    }

    @Override
    protected MassageListPresenter createIPresenter() {
        return new MassageListPresenterImpl();
    }


    /**
     * 显示加载视图
     */
    @Override
    public void showLoadingView() {

    }

    /**
     * 隐藏加载视图
     */
    @Override
    public void hideLoadingView() {

    }


    /**
     * 回调方法设置数据
     *
     * @param listItem
     */
    @Override
    public void setListItem(List<RecordDetail> listItem) {
        // lv_massage.setAdapter(new MessageSmpleAdapter(getActivity(),listItem,R.layout.item_massage));
    }
}
