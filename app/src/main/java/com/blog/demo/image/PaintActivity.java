package com.blog.demo.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintActivity extends Activity {

    private Paint mPaint = new Paint();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintView(this));
    }

    private class PaintView extends View {

        public PaintView(Context context) {
            super(context);
        }

        public PaintView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mPaint.setAntiAlias(true); // 设置是否使用抗锯齿功能
            mPaint.setDither(true); // 设定是否使用图像抖动处理
            mPaint.setColor(Color.RED); // 给画笔设置颜色
            mPaint.setStyle(Paint.Style.STROKE); // 描边
            mPaint.setStrokeWidth(10);
            canvas.drawLine(50, 50, 600, 50, mPaint);

            mPaint.setStrokeWidth(20);
            canvas.drawLine(50, 80, 600, 80, mPaint);

        }
    }
}
