package com.cxyz.commons.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cxyz.commons.utils.ImageLoaderManager;

/**
 * Created by 夏旭晨 on 2018/9/22.
 * ViewHolder中定义了一些比较常用的设置View的方法，配合AdapterBase使用
 * 其中的set方法大多为链式调用
 */
public class ViewHolder {
    private int mPosition;
    private int mLayoutId;
    private Context mContext;
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mContext = context;
        mLayoutId = layoutId;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     *
     * @return 该viewholder所对应的position
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * @return 获取该vieholder所持有的布局文件
     */
    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 通过viewId获取控件（不需要强转）
     *
     * @param viewId 控件id
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取容器view
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return this
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 为ImageView加载网络图片
     * @param viewID ImageView的id
     * @param url 资源路径
     * @return this
     */
    public ViewHolder setImageUrl(int viewID, String url) {
        ImageView view = getView(viewID);
        if (!url.isEmpty()) {
            ImageLoaderManager.getInstance(mContext).displayImage(view,url);
        }
        return this;
    }

    /**
     * 为ImageView加载图片，如果url为空则加载resource所对应的图片
     * @param viewID ImageView的id
     * @param url 资源路径
     * @param resource 资源id
     * @return this
     */
    public ViewHolder setImageUrl(int viewID, String url, int resource) {
        ImageView view = getView(viewID);
        if (!TextUtils.isEmpty(url)) {
            ImageLoaderManager.getInstance(mContext).displayImage(view, url);
        } else {
            view.setImageResource(resource);
        }
        return this;
    }

    /**
     *  根据资源id为ImageView设置图片
     * @param viewId 控件id
     * @param resId 资源id
     * @return this
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 根据传入的bitmap设置ImageView的图片
     * @param viewId 控件id
     * @param bitmap 位图
     * @return this
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }


    /**
     * 根据传入的drawable设置ImageView的图片
     * @param viewId 控件id
     * @param drawable drawable对象
     * @return
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 根据颜色值设置背景色
     * @param viewId 控件id
     * @param color 颜色值
     * @return this
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 通过颜色资源id设置背景色
     * @param viewId 控件id
     * @param backgroundRes 背景色资源
     * @return this
     */
    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置TextView的字体颜色
     * @param viewId 控件id
     * @param textColor 文字颜色
     * @return this
     */
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 设置TextView的字体颜色
     * @param viewId 控件id
     * @param textColorRes 颜色资源
     * @return this
     */
    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * 设置控件透明度
     * @param viewId 控件id
     * @param value 值
     * @return this
     */
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * 设置控件可见性
     * @param viewId 控件id
     * @param visible View.VISIBLE/View.INVISIBLE/View.GONE
     * @return this
     */
    public ViewHolder setVisible(int viewId,int visible)
    {
        getView(viewId).setVisibility(visible);
        return this;
    }
    /**
     * TextView的文本显示链接
     * @param viewId 控件id
     * @return this
     */
    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * 设置字体
     * @param typeface 字体
     * @param viewIds 控件id
     * @return this
     */
    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * 设置进度条进度
     * @param viewId 控件id
     * @param progress 进度
     * @return this
     */
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置进度条进度
     * @param viewId 控件id
     * @param progress 进度
     * @param max 最大进度
     * @return this
     */
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置最大进度
     * @param viewId 控件id
     * @param max 最大进度
     * @return this
     */
    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * 设置RatingBar的进度
     * @param viewId 控件id
     * @param rating 进度
     * @return this
     */
    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置RatingBar的进度
     * @param viewId 控件id
     * @param rating 进度
     * @param max 最大进度
     * @return this
     */
    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 把对象附加给相应的view
     * @param viewId 控件id
     * @param tag 值
     * @return
     */
    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 把对象附加给相应的view
     * @param viewId 控件id
     * @param key 键
     * @param tag 值
     * @return
     */
    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * 给可选的控件设置选择状态
     * @param viewId 控件id
     * @param checked 是否选中
     * @return
     */
    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId 控件id
     * @param listener 监听
     * @return this
     */
    public ViewHolder setOnClickListener(int viewId, View.
            OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置触摸事件
     * @param viewId 控件id
     * @param listener 监听
     * @return this
     */
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     * @param viewId 控件id
     * @param listener 监听
     * @return this
     */
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}

