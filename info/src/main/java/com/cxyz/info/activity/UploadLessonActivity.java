package com.cxyz.info.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.commons.utils.FileUtil;
import com.cxyz.commons.utils.ToastUtil;
import com.cxyz.commons.widget.TitleView;
import com.cxyz.info.R;
import com.cxyz.info.ipresenter.IUploadLessonPresenter;
import com.cxyz.info.ipresenter.impl.IUpdateLessonPresenterImpl;
import com.cxyz.info.iview.IUploadLessonView;

import java.io.File;
import java.util.List;

import ru.bartwell.exfilepicker.ExFilePicker;
import ru.bartwell.exfilepicker.data.ExFilePickerResult;

public class UploadLessonActivity extends BaseActivity<IUploadLessonPresenter> implements IUploadLessonView {

    private static final int EX_FILE_PICKER_RESULT = 1;
    private TitleView tv_title;

    private LinearLayout ll_download,ll_upload;//上传下载

    private AlertDialog dialog;//上传下载进度dialog

    private ProgressBar pb_pro;//上传下载进度条

    private TextView tv_pro;//进度文字

    private TextView tv_hint;//提示文字
    @Override
    public int getContentViewId() {
        return R.layout.activity_upload_stu_layout;
    }

    @Override
    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        ll_download = findViewById(R.id.ll_download);
        ll_upload = findViewById(R.id.ll_upload);

        tv_title.setTitle("导入课表");
    }

    @Override
    protected boolean isStateBar() {
        return false;
    }

    @Override
    public void initData() {
    }

    @Override
    public void setEvent() {
        tv_title.setBackClickListener(v -> onBackPressed());
        ll_download.setOnClickListener(view -> iPresenter.download());
        ll_upload.setOnClickListener((View view) ->
        {
            ExFilePicker exFilePicker = new ExFilePicker();
            exFilePicker.setCanChooseOnlyOneItem(true);// 单选
            exFilePicker.setQuitButtonEnabled(true);
            exFilePicker.setStartDirectory(Environment.getExternalStorageDirectory().getPath()+"/data");
            exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);
            exFilePicker.start(getActivity(), EX_FILE_PICKER_RESULT);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EX_FILE_PICKER_RESULT) {
            ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
            if (result != null && result.getCount() > 0) {
                String path = result.getPath();

                List<String> names = result.getNames();
                for (int i = 0; i < names.size(); i++) {
                    File f = new File(path, names.get(i));
                    try {
                        Uri uri = Uri.fromFile(f); //这里获取了真实可用的文件资源
                        iPresenter.upload(f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    protected IUploadLessonPresenter createIPresenter() {
        return new IUpdateLessonPresenterImpl();
    }

    @Override
    public void showSuccess(File file) {
        FileUtil.open(getActivity(),file);
    }


    @Override
    public void updateProgress(int progress) {
        if(dialog == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View view = View.inflate(getActivity(),R.layout.dialog_progress_layout,null);
            pb_pro = view.findViewById(R.id.pb_pro);
            tv_hint = view.findViewById(R.id.tv_hint);
            tv_pro = view.findViewById(R.id.tv_pro);
            builder.setView(view);
            dialog = builder.create();
        }
        pb_pro.setProgress(progress);
        tv_pro.setText(progress+"");
        if(progress != 100)
        {
            tv_hint.setText("正在下载中...");
            if(!dialog.isShowing())
                dialog.show();
        }
        else
        {
            tv_hint.setText("下载完成");
            dialog.cancel();
            return;
        }
    }

    @Override
    public void showFail(String error) {
        ToastUtil.showShort(error);
    }

    @Override
    public void UploadSuccess(Object info) {
        if(info instanceof String)
            ToastUtil.showShort(info.toString());
    }

}
