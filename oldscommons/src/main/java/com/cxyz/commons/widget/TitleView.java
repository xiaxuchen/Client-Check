package com.cxyz.commons.widget;



import android.content.Context;
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

    private TextView edit,set,name,back;

    private OnClickListener onClickImpl;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttr(attrs);
    }

    /**
     * 初始化属性
     */
    private void initAttr(AttributeSet attrs) {
    }

    /**
     * 初始化view
     */
    private void initView()
    {
        setOrientation(LinearLayout.VERTICAL);
        View.inflate(getContext(), R.layout.views_title_layout, this);
        back = (TextView) findViewById(R.id.title_back);
        edit = (TextView) findViewById(R.id.title_edit);
        set = (TextView) findViewById(R.id.title_set);
        name = (TextView) findViewById(R.id.title_name);

        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        set.setOnClickListener(this);
        name.setOnClickListener(this);
    }

    /**
     * 设置标题栏的标题（中间的文字）
     * @param title
     */
    public void setTitle(String title)
    {
        name.setText(title);
    }

    /**
     * 设置标题右边的图标
     * @param res 资源id
     */
    public void setIcon(int res)
    {
        setTextDrwRight(name,res);
    }

    /**
     * 设置返回的图标和文字，如果图标资源为0则为默认
     * @param res 资源id
     * @param txt 文字
     */
    public void setBack(int res,String txt)
    {
        back.setText(txt);
        setTextDrwLeft(back,res);
    }

    /**
     * 设置编辑位置的文字
     * @param txt 文字
     */
    public void setEdit(String txt)
    {
        edit.setText(txt);
        edit.setVisibility(View.VISIBLE);
    }

    /**
     * 设置控件右边的文字
     * @param txt
     */
    public void setSet(String txt)
    {
        set.setText(txt);
        set.setVisibility(View.VISIBLE);
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
        this.onClickImpl = listener;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(onClickImpl != null)
        {
            if(id == R.id.title_back)
                onClickImpl.onBackClick();
            else if(id == R.id.title_edit)
                onClickImpl.onEditClick();
            else if(id == R.id.title_name)
                onClickImpl.onNameClick();
            else if(id == R.id.title_set)
                onClickImpl.onSetClick();
        }


    }

    /**
     * TitleView点击事件的接口
     */
    public interface OnClickListener{
        void onBackClick();
        void onEditClick();
        void onNameClick();
        void onSetClick();
    }

    /**
     * TitleView的OnClickListener包装类
     */
    public static class OnClickListenerWrapper implements OnClickListener
    {

        @Override
        public void onBackClick() {

        }

        @Override
        public void onEditClick() {

        }

        @Override
        public void onNameClick() {

        }

        @Override
        public void onSetClick() {

        }
    }
}

