package com.cxyz.homepage.acitivity;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.date.Date;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.homepage.R;
import com.cxyz.homepage.feature_z_domain.MySubject;
import com.cxyz.homepage.ipresenter.impl.MySubjectsPresenterImpl;
import com.cxyz.homepage.iview.SubjectsView;
import com.zhuangfei.timetable.TimetableView;
import com.zhuangfei.timetable.listener.ISchedule;
import com.zhuangfei.timetable.listener.OnItemBuildAdapter;
import com.zhuangfei.timetable.listener.OnSlideBuildAdapter;
import com.zhuangfei.timetable.model.Schedule;
import com.zhuangfei.timetable.view.WeekView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 鱼塘主 on 2018/10/21.
 */

public class ClazzActivity extends BaseActivity<MySubjectsPresenterImpl> implements View.OnClickListener,SubjectsView{
    private static final String TAG = "ClazzActivity";

    TimetableView mTimetableView;
    //控件
    WeekView mWeekView;

    LinearLayout layout;
    TextView titleTextView;
    List<MySubject> mySubjects = new ArrayList<>();

    //记录切换的周次，不一定是当前周
    int target = -1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_clazz;
    }

    @Override
    public void initData() {

    }
    @Override
    public void initView() {
     //   List<MySubject> mySubjects1 = SubjectRepertory.loadDefaultSubjects2();

     //   this.mySubjects = mySubjects1;
     //   this.mySubjects.addAll(SubjectRepertory.loadDefaultSubjects());

        titleTextView = findViewById(R.id.id_title);
        layout = findViewById(R.id.id_layout);
        Date date = new Date();
        initTimetableView();
        iPresenter.setMySubjects("17478090",date);//这个也是要改的
    }


    @Override
    public void setEvent() {
        layout.setOnClickListener(this);
    }
    @Override
    public void setSubjects(List<MySubject> subjects) {//这个是回调的方法也是要改的
        LogUtil.e(subjects);
        List<Schedule> dataSource=mTimetableView.dataSource();
        int size = dataSource.size();
        for(MySubject subject:subjects)
        {
            subject.setRoom("2419");
            subject.setTime("8:00");
            subject.setTerm("diyixueqi");
            dataSource.add(subject.getSchedule());
        }
        mTimetableView.updateView();
    }
    @Override
    protected MySubjectsPresenterImpl createIPresenter() {
        return new MySubjectsPresenterImpl();
    }

    /**
     * 初始化课程控件
     */
    private void initTimetableView() {

        //获取控件
        mWeekView = findViewById(R.id.weekView);
        mTimetableView = findViewById(R.id.timetableView);

        //设置周次选择属性<选择当前周>
        mWeekView.source(mySubjects)
                .curWeek(1)
                .callback(week -> {
                    int cur = mTimetableView.curWeek();
                    //更新切换后的日期，从当前周cur->切换的周week
                    mTimetableView.onDateBuildListener()
                            .onUpdateDate(cur, week);
                    mTimetableView.changeWeekOnly(week);
                })
                .callback(() -> onWeekLeftLayoutClicked())
                .isShow(false)//设置隐藏，默认显示
                .showView();

        mTimetableView.source(mySubjects)
                .curWeek(1)
                .curTerm("大二上学期")
                .maxSlideItem(10)
                .monthWidthDp(30)
                //透明度
                //日期栏0.1f、侧边栏0.1f，周次选择栏0.6f
                //透明度范围为0->1，0为全透明，1为不透明
//                .alpha(0.1f, 0.1f, 0.6f)
                .callback((ISchedule.OnItemClickListener) (v, scheduleList) -> display(scheduleList))
                .callback((v, day, start) -> Toast.makeText(ClazzActivity.this,
                        "长按:周" + day  + ",第" + start + "节",
                        Toast.LENGTH_SHORT).show())
                .callback(curWeek -> {
                    titleTextView.setText("第" + curWeek + "周");
                })
                .callback((ISchedule.OnFlaglayoutClickListener) (day, start) -> { //旗标布局点击监听<课程卡片点击监听>
                    mTimetableView.hideFlaglayout();
                    Toast.makeText(ClazzActivity.this,
                            "点击了旗标:周" + (day + 1) + ",第" + start + "节",
                            Toast.LENGTH_SHORT).show();
                })
                .showView();

        mTimetableView.callback(new OnItemBuildAdapter() {
            @Override
            public String getItemText(Schedule schedule, boolean isThisWeek) {
                StringBuilder builder = new StringBuilder();
                builder.append(schedule.getName()).append("\n").append(schedule.getTeacher()).append("\n").append(schedule.getRoom());
                return builder.toString();
            }
        })
                .updateView();
    }

    /**
     * 更新一下，防止因程序在后台时间过长（超过一天）而导致的日期或高亮不准确问题。
     */
    @Override
    protected void onStart() {
        super.onStart();
        mTimetableView.onDateBuildListener()
                .onHighLight();
    }

    /**
     * 周次选择布局的左侧被点击时回调<br/>
     * 对话框修改当前周次
     */
    protected void onWeekLeftLayoutClicked() {
        final String items[] = new String[20];
        int itemCount = mWeekView.itemCount();
        for (int i = 0; i < itemCount; i++) {
            items[i] = "第" + (i + 1) + "周";
        }
        target = -1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置当前周");
        builder.setSingleChoiceItems(items, mTimetableView.curWeek() - 1,
                (dialogInterface, i) -> target = i);
        builder.setPositiveButton("设置为当前周", (dialog, which) -> {
            if (target != -1) {
                mWeekView.curWeek(target + 1).updateView();
                mTimetableView.changeWeekForce(target + 1);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    /**
     * 显示内容
     *
     * @param beans
     */
    protected void display(List<Schedule> beans) {
        String str = "";
        for (Schedule bean : beans) {
            str += bean.getName() + ","+bean.getWeekList().toString()+","+bean.getStart()+","+bean.getStep()+"\n";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示弹出菜单
     */
    public void showPopmenu() {
//        PopupMenu popup = new PopupMenu(this, moreButton);
//        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.top1:
//                        addSubject();
//                        break;
//                    case R.id.top2:
//                        deleteSubject();
//                        break;
//
//                    case R.id.top4:
//                        hideNonThisWeek();
//                        break;
//                    case R.id.top5:
//                        showNonThisWeek();
//                        break;
//                    case R.id.top6:
//                        setMaxItem(8);
//                        break;
//                    case R.id.top7:
//                        setMaxItem(10);
//                        break;
//                    case R.id.top8:
//                        setMaxItem(12);
//                        break;
//                    case R.id.top9:
//                        showTime();
//                        break;
//                    case R.id.top10:
//                        hideTime();
//                        break;
//                    case R.id.top11:
//                        showWeekView();
//                        break;
//                    case R.id.top12:
//                        hideWeekView();
//                        break;
//                    case R.id.top13:
//                        setMonthWidth();
//                        break;
//                    case R.id.top16:
//                        resetMonthWidth();
//                        break;
//                    case R.id.top14:
//                        hideWeekends();
//                        break;
//                    case R.id.top15:
//                        showWeekends();
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });
//
//        popup.show();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.id_layout) {//如果周次选择已经显示了，那么将它隐藏，更新课程、日期
            //否则，显示
            if (mWeekView.isShowing()) {
                mWeekView.isShow(false);
                titleTextView.setTextColor(getResources().getColor(R.color.app_course_textcolor_blue));
                int cur = mTimetableView.curWeek();
                mTimetableView.onDateBuildListener()
                        .onUpdateDate(cur, cur);
                mTimetableView.changeWeekOnly(cur);
            } else {
                mWeekView.isShow(true);
                titleTextView.setTextColor(getResources().getColor(R.color.app_red));
            }

        }
    }

    /**
     * 删除课程
     * 内部使用集合维护课程数据，操作集合的方法来操作它即可
     * 最后更新一下视图（全局更新）
     */
    protected void deleteSubject() {
        int size = mTimetableView.dataSource().size();
        int pos = (int) (Math.random() * size);
        if (size > 0) {
            mTimetableView.dataSource().remove(pos);
            mTimetableView.updateView();
        }
    }

    /**
     * 添加课程
     * 内部使用集合维护课程数据，操作集合的方法来操作它即可
     * 最后更新一下视图（全局更新）
     */
    protected void addSubject() {
        List<Schedule> dataSource = mTimetableView.dataSource();
        int size = dataSource.size();
        if (size > 0) {
            Schedule schedule = dataSource.get(0);
            dataSource.add(schedule);
            mTimetableView.updateView();
        }
    }

    /**
     * 隐藏非本周课程
     * 修改了内容的显示，所以必须更新全部（性能不高）
     * 建议：在初始化时设置该属性
     * <p>
     * updateView()被调用后，会重新构建课程，课程会回到当前周
     */
    protected void hideNonThisWeek() {
        mTimetableView.isShowNotCurWeek(false).updateView();
    }

    /**
     * 显示非本周课程
     * 修改了内容的显示，所以必须更新全部（性能不高）
     * 建议：在初始化时设置该属性
     */
    protected void showNonThisWeek() {
        mTimetableView.isShowNotCurWeek(true).updateView();
    }

    /**
     * 设置侧边栏最大节次，只影响侧边栏的绘制，对课程内容无影响
     *
     * @param num
     */
    protected void setMaxItem(int num) {
        mTimetableView.maxSlideItem(num).updateSlideView();
    }

    /**
     * 显示时间
     * 设置侧边栏构建监听，TimeSlideAdapter是控件实现的可显示时间的侧边栏
     */
    protected void showTime() {
        String[] times = new String[]{
                "8:00", "9:00", "10:10", "11:00",
                "15:00", "16:00", "17:00", "18:00",
                "19:30", "20:30", "21:30", "22:30"
        };
        OnSlideBuildAdapter listener = (OnSlideBuildAdapter) mTimetableView.onSlideBuildListener();
        listener.setTimes(times)
                .setTimeTextColor(Color.BLACK);
        mTimetableView.updateSlideView();
    }

    /**
     * 隐藏时间
     * 将侧边栏监听置Null后，会默认使用默认的构建方法，即不显示时间
     * 只修改了侧边栏的属性，所以只更新侧边栏即可（性能高），没有必要更新全部（性能低）
     */
    protected void hideTime() {
        mTimetableView.callback((ISchedule.OnSlideBuildListener) null);
        mTimetableView.updateSlideView();
    }

    /**
     * 显示WeekView
     */
    protected void showWeekView() {
        mWeekView.isShow(true);
    }

    /**
     * 隐藏WeekView
     */
    protected void hideWeekView() {
        mWeekView.isShow(false);
    }

    /**
     * 设置月份宽度
     */
    private void setMonthWidth() {
        mTimetableView.monthWidthDp(50).updateView();
    }

    /**
     * 设置月份宽度,默认40dp
     */
    private void resetMonthWidth() {
        mTimetableView.monthWidthDp(40).updateView();
    }

    /**
     * 隐藏周末
     */
    private void hideWeekends() {
        mTimetableView.isShowWeekends(false).updateView();
    }

    /**
     * 显示周末
     */
    private void showWeekends() {
        mTimetableView.isShowWeekends(true).updateView();
    }



}
