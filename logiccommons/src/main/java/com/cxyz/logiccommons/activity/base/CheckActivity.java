package com.cxyz.logiccommons.activity.base;

import com.cxyz.commons.activity.BaseActivity;
import com.cxyz.logiccommons.ipresenter.base.ICheckPresenter;
import com.cxyz.logiccommons.iview.base.ICheckView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * Created by xiaxuchen on 18-10-27.
 * 当前项目activity的基类,关于业务逻辑的修改写在此处,此项目的所以activity继承
 */

public abstract class CheckActivity<p extends ICheckPresenter> extends BaseActivity<p> implements ICheckView {

    //加载
    private QMUITipDialog progress;

    /**
     * 设置加载时提示文字
     * @return 提示文本
     */
    protected String getTip()
    {
        return "正在加载";
    }

    /**
     * 设置进度条是否可以取消,默认不可以
     * @return
     */
    protected boolean progressCancleable()
    {
        return false;
    }

    @Override
    public void showLoadingView() {
        if(progress == null)
        {
            LogUtil.i("创建progress");
            progress = buildProgress();
        }
        progress.show();
    }

    @Override
    public void hideLoadingView() {
        if(progress == null)
        {
            LogUtil.i("progress为空,不能隐藏");
            return;
        }
        progress.dismiss();
    }


    /**
     * 创建进度条
     * @return
     */
    private QMUITipDialog buildProgress()
    {
        QMUITipDialog.Builder builder = new QMUITipDialog.Builder(getActivity());
        builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING);
        builder.setTipWord(getTip());
        return builder.create(progressCancleable());
    }
}
