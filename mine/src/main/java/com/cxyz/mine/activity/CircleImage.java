package com.cxyz.mine.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.cxyz.mine.R;

/**
 * Created by 550211 on 2017/9/23.
 */

public class CircleImage extends ImageView {

    private Paint mPaint;//设置画笔
    private Bitmap mBitmap;//获取图片资源
    private int width, height;//获取控件宽高
    private int mOuterRing = 0;//设置外圈大小
    private int outerRingAlpha = 30;//设置外圈透明度
    private int color = Color.BLUE;//设置外圈颜色

    public CircleImage(Context context) {
        this(context, null);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public int getmOuterRing() {
        return mOuterRing;
    }

    public void setmOuterRing(int mOuterRing) {
        this.mOuterRing = mOuterRing;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getOuterRingAlpha() {
        return outerRingAlpha;
    }

    public void setOuterRingAlpha(int outerRingAlpha) {
        this.outerRingAlpha = outerRingAlpha;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取空间宽高
        width = View.getDefaultSize(getMeasuredWidth(), widthMeasureSpec);
        height = View.getDefaultSize(getMeasuredHeight(), heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        Bitmap bitmap;
        if (drawable != null) {
            if (mBitmap != null) {
                bitmap = mBitmap;
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pxxy);
            }
            Matrix matrix = new Matrix();
            //设置图片缩放比例，
            if (mOuterRing>0){
                if (width > height) {
                    matrix.setScale((float) (height) / bitmap.getHeight(), (float) (height) / bitmap.getHeight());
                } else {
                    matrix.setScale((float) (width) / bitmap.getWidth(), (float) (width) / bitmap.getWidth());
                }
            }else {
                if (width > height) {
                    matrix.setScale((float) (width) / bitmap.getWidth(), (float) (width) / bitmap.getWidth());
                } else {
                    matrix.setScale((float) (height) / bitmap.getHeight(), (float) (height) / bitmap.getHeight());
                }
            }

            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            //将图片设置为圆形
            bitmap = toRoundBitmap(bitmap);

            //当外圈大于0的时候要设置外圈加图片的宽度不能大于控件宽度
            if (mOuterRing+bitmap.getWidth()>width){
                mOuterRing = (width-bitmap.getWidth())/2;
            }

            //绘制外圈
            Paint cPaint = new Paint();
            cPaint.setStrokeWidth(2);
            cPaint.setColor(color);
            cPaint.setAlpha(outerRingAlpha);
            // cPaint.setFilterBitmap(true);
            cPaint.setAntiAlias(true);
            canvas.drawCircle(bitmap.getWidth()/2+mOuterRing,bitmap.getHeight()/2+mOuterRing,bitmap.getWidth()/2+mOuterRing,cPaint);

            //绘制图片
            canvas.drawBitmap(bitmap, mOuterRing, mOuterRing, mPaint);

        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 设置图片为圆形
     *
     * @param bitmap
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst_left, dst_top, dst_right, dst_bottom);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
