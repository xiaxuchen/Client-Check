package com.cxyz.commons.IView;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * Created by Administrator on 2018/11/7.
 */

public class IDefaultView implements IBaseView{

    /**
     * 加载的进度条
     */
    private QMUITipDialog loadingView;

    private Context context;
    //提示文本
    private String tipword;
    //可取消
    private boolean cancelable;

    /**
     *
     * @param context
     * @param tipword loadingview显示时的提示文字
     * @param cancelable loadingview 是否可取消
     */
    public IDefaultView(Context context,String tipword,boolean cancelable)
    {
        this.context = context;
        this.tipword = tipword;
        this.cancelable = cancelable;
    }

    /**
     * 创建LoadView
     */
    private void createLoadView()
    {
        QMUITipDialog.Builder builder = new QMUITipDialog.Builder(context);
        builder.setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING);
        builder.setTipWord(tipword);
        loadingView = builder.create(cancelable);
    }

    @Override
    public void showLoadingView() {
        if(loadingView == null)
            createLoadView();
        loadingView.show();
    }

    @Override
    public void hideLoadingView() {
        if(loadingView != null)
            loadingView.dismiss();
    }
}
