package com.cxyz.info.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.LogUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.info.R;
import com.cxyz.info.ipresenter.IUploadPresenter;
import com.cxyz.info.ipresenter.impl.IUploadPresenterImpl;
import com.cxyz.info.iview.IUploadView;

/**
 * Created by Administrator on 2019/1/1.
 */

@Route(path = "/info/UploadActivity")
public class UploadActivity extends BaseActivity<IUploadPresenter> implements IUploadView {

    private TitleView tv_title;

    private View stu_line,lesson_line;

    private TextView tv_stu_import,tv_lesson_import;

    private TextView tv_empty;

    private boolean isStuImportEnable = false;

    private boolean isLessonImportEnable = false;

    int times = 0;

    @Override
    public int getContentViewId() {
        return R.layout.activity_upload_layout;
    }

    @Override
    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        stu_line = findViewById(R.id.stu_line);
        lesson_line = findViewById(R.id.lesson_line);
        tv_stu_import = findViewById(R.id.tv_stu_import);
        tv_lesson_import = findViewById(R.id.tv_lesson_import);
        tv_empty = findViewById(R.id.tv_empty);

        tv_title.setBackClickListener(v -> onBackPressed());
    }

    @Override
    public void initData() {

    }

    @Override
    public void setEvent() {
        tv_stu_import.setOnClickListener(view -> {
            startActivity(UploadStuActivity.class);
        });

        tv_lesson_import.setOnClickListener(view -> {
            startActivity(UploadLessonActivity.class);
        });
    }

    @Override
    protected void afterInit() {
        super.afterInit();
        iPresenter.check();
    }

    @Override
    protected IUploadPresenter createIPresenter() {
        return new IUploadPresenterImpl();
    }

    @Override
    public void userImportEnable() {
        tv_stu_import.setVisibility(View.VISIBLE);
        stu_line.setVisibility(View.VISIBLE);
        isStuImportEnable = true;
        LogUtil.d("ccc");
        hideLoad();
    }

    @Override
    public void lessonImportEnable() {
        tv_lesson_import.setVisibility(View.VISIBLE);
        lesson_line.setVisibility(View.VISIBLE);
        isLessonImportEnable = true;
        LogUtil.d("suc");
        hideLoad();
    }

    @Override
    public void error(String error) {
        hideLoad();
    }

    private void hideLoad()
    {
        times++;
        if(times == 2)
        {
            hideLoadingView();
            if(!isLessonImportEnable && !isStuImportEnable)
                tv_empty.setVisibility(View.GONE);
        }
    }
}
