package com.cxyz.commons.widget;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxyz.commons.R;


/**
 *
 *
 * <h1>关于TitleView
 * <ul>
 *     <li>
 *         在布局文件中的使用(与普通view一样，不过需要带包名)：<br/>
 *         &lt;com.cxyz.commons.widget.TitleView<br/>
 *           android:id="@+id/title"<br/>
 *          android:layout_width="match_parent"<br/>
 *          android:layout_height="wrap_content"/&gt;<br/>
 *      <li>设置显示:<br/>
 *          setTitle、setIcon、setEdit、setSet，详细请看方法注释
 *      <li>设置监听
 *          setOnClickListener(TitleView.OnClickListener);
 *          可以使用OnClickListenerWrapper类，其是OnClickListener的包装类，<br/>
 *          只需重写需要的方法而不是全部方法，减少代码冗余,详细时间请看注释
 * </ul>
 */
public class TitleView extends LinearLayout implements View.OnClickListener {

    private TextView more,title,back;

    private String txt_title = "",txt_more = "",txt_back = "";

    //返回的icon，更多的icon
    private int backRes = R.mipmap.common_title_back,moreRes = 0;

    //相应的颜色
    private int backColor,moreColor,titleColor;

    private OnClickListener listener;

    private OnBackClickListener backClickListener;

    private OnMoreClickListener moreClickListener;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context.obtainStyledAttributes(attrs,R.styleable.TitleView));
        initView();
    }

    /**
     * 初始化属性
     */
    private void initAttr(TypedArray array) {
        txt_title = array.getString(R.styleable.TitleView_title);
        txt_more = array.getString(R.styleable.TitleView_moreText);
        txt_back = array.getString(R.styleable.TitleView_backText);
        backRes = array.getResourceId(R.styleable.TitleView_backIcon,backRes);
        moreRes = array.getResourceId(R.styleable.TitleView_moreIcon,moreRes);
        array.recycle();
    }

    /**
     * 初始化view
     */
    private void initView()
    {
        setOrientation(LinearLayout.VERTICAL);
        View.inflate(getContext(), R.layout.views_title_layout, this);
        back = findViewById(R.id.title_back);
        title = findViewById(R.id.title_title);
        more = findViewById(R.id.title_more);

        setBack(backRes,txt_back);
        setMore(moreRes,txt_more);
        setTitle(txt_title);

        back.setOnClickListener(this);
        title.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    public TextView getMore() {
        return more;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getBack() {
        return back;
    }

    public void setBackColor(int backColor) {
        this.backColor = backColor;
    }

    public void setMoreColor(int moreColor) {
        this.moreColor = moreColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    /**
     * 设置标题栏的标题（中间的文字）
     * @param t
     */
    public void setTitle(String t)
    {
        title.setText(t);
    }

    /**
     * 设置更多
     * @param txt 文本
     * @param res icon
     */
    public void setMore(int res,String txt)
    {
        setMoreText(txt);
        setMoreIcon(res);
    }

    /**
     * 设置标题右边的图标
     * @param res 资源id
     */
    public void setMoreIcon(int res)
    {
        setTextDrwRight(more,res);
    }

    /**
     * 设置更多的文本
     * @param txt 字符串
     */
    public void setMoreText(String txt)
    {
        more.setText(txt);
    }


    /**
     * 设置返回的图标和文字，如果图标资源为0则为默认
     * @param res 资源id
     * @param txt 文字
     */
    public void setBack(int res,String txt)
    {
        setBackText(txt);
        setBackIcon(res);
    }

    /**
     * 设置返回的文本
     * @param txt 字符串
     */
    public void setBackText(String txt)
    {
        back.setText(txt);
    }

    /**
     * 设置返回图标
     * @param res 资源id
     */
    public void setBackIcon(int res)
    {
        setTextDrwLeft(back,res);
    }



    /**
     * 设置左侧的图标
     * @param textView
     * @param drawId
     */
    private void setTextDrwLeft(TextView textView,int drawId){
        Drawable drawable=null;
        if (drawId!=0){
            drawable=getResources().getDrawable(drawId);
            drawable.setBounds( 0, 0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }
    /**
     * 设置右侧的图标
     * @param textView
     * @param drawId
     */
    private void setTextDrwRight(TextView textView,int drawId){
        Drawable drawable=null;
        if (drawId==101){
            textView.setTextColor(Color.parseColor("#C39F8A"));
        }else if(drawId!=101&&drawId!=0){
            drawable=getResources().getDrawable(drawId);
            drawable.setBounds( 0, 0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null,drawable, null);
    }


    /**
     * 设置监听,建议使用OnClickListenerWrapper，只需重写需要的方法，而不是所有
     * @param listener TitleView.OnClickListener
     */
    public void setOnClickListener(OnClickListener listener)
    {
        this.listener = listener;
    }

    /**
     * 返回的监听
     * @param backClickListener
     */
    public void setBackClickListener(OnBackClickListener backClickListener) {
        this.backClickListener = backClickListener;
    }

    /**
     * 更多的监听
     * @param moreClickListener
     */
    public void setMoreClickListener(OnMoreClickListener moreClickListener) {
        this.moreClickListener = moreClickListener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

//        if(onClickImpl != null)
//        {
//            if(id == R.id.title_back)
//            {
//                if((txt_back == null || txt_back.isEmpty())&& backRes == 0)
//                    return;
//            }
//            else if(id == R.id.title_more)
//            {
//                if((txt_more == null || txt_more.isEmpty())&& moreRes == 0)
//                    return;
//            }
//            else if(id == R.id.title_title)
//                if(txt_title == null || txt_title.isEmpty())
//                    return;
//        }
        if(id == R.id.title_back)
        {
            if (backClickListener != null)
            {
                if ((txt_back != null && !txt_back.isEmpty()) || backRes != 0)
                    backClickListener.onBackClick(back);
            }
            else if(listener != null)
                listener.onBackClick(back);
        }
        else if(id == R.id.title_more)
        {
            if (moreClickListener != null)
            {
                if ((txt_more != null && !txt_more.isEmpty()) || moreRes != 0)
                    moreClickListener.onMoreClick(more);
            }
            else if(listener != null)
                listener.onMoreClick(more);

        }
    }


    /**
     * TitleView点击事件的接口
     */
    public interface OnClickListener extends OnBackClickListener,OnMoreClickListener{
    }


    public interface OnBackClickListener{
        /**
         * 返回点击的事件
         * @param v
         */
        void onBackClick(View v);
    }

    public interface OnMoreClickListener{

        /**
         * 更多点击的事件
         * @param v
         */
        void onMoreClick(View v);
    }
}

