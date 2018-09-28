package com.cxyz.homepage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 鱼塘主 on 2018/9/26.
 */

public class myTableTextView extends TextView {
    Paint paint = new Paint();
    public myTableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int color = Color.parseColor("#80b9f2");
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画TextView的4个边
               canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
               canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
               canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
               canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
    }
}
