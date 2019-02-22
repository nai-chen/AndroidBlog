package com.blog.demo.image.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintStyleActivity extends Activity {
    private Paint mRedPaint, mBluePaint, mYellowPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintStyleView(this));
    }

    private class PaintStyleView extends View {

        public PaintStyleView(Context context) {
            this(context, null);
        }

        public PaintStyleView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setAntiAlias(true);  // 设置是否使用抗锯齿功能
            mRedPaint.setStrokeWidth(20);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawLine(50, 50, 400, 50, mRedPaint);

            mRedPaint.setAlpha(100);
            canvas.drawLine(50, 100, 400, 100, mRedPaint);

            mRedPaint.setARGB(10, 255, 0, 0);
            canvas.drawLine(50, 150, 400, 150, mRedPaint);

            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(150, 300, 75, mRedPaint);

            mRedPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(150, 500, 75, mRedPaint);

            mRedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(150, 700, 75, mRedPaint);
        }

    }

}
