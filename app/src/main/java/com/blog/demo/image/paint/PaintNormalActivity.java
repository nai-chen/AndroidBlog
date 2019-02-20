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

public class PaintNormalActivity extends Activity {
    private Paint mRedPaint, mBluePaint, mYellowPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PaintNormalView(this));
    }

    class PaintNormalView extends View {

        public PaintNormalView(Context context) {
            this(context, null);
        }

        public PaintNormalView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setStrokeWidth(10);

            mBluePaint = new Paint();
            mBluePaint.setColor(Color.BLUE);
            mBluePaint.setStyle(Paint.Style.FILL);

            mYellowPaint = new Paint();
            mYellowPaint.setColor(Color.YELLOW);
            mYellowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawLine(50, 50, 400, 100, mRedPaint);

            mRedPaint.setAntiAlias(true);  // 设置是否使用抗锯齿功能
            canvas.drawLine(50, 100, 400, 150, mRedPaint);


//            mPaint.setAntiAlias(true);
//            mPaint.setDither(true); // 设定是否使用图像抖动处理
//            mPaint.setColor(Color.RED); // 给画笔设置颜色
//            mPaint.setStyle(Paint.Style.STROKE); // 描边
//            mPaint.setStrokeWidth(10);
//            canvas.drawLine(50, 50, 600, 50, mPaint);
//
//            mPaint.setStrokeWidth(20);
//            canvas.drawLine(50, 80, 600, 80, mPaint);
//
//            mPaint.setStrokeCap(Paint.Cap.ROUND);
//            canvas.drawLine(50, 110, 600, 110, mPaint);
//
//            mPaint.setStrokeCap(Paint.Cap.SQUARE);
//            canvas.drawLine(50, 140, 600, 140, mPaint);
//
//            canvas.drawCircle(100, 240, 50, mPaint);
//
//            mPaint.setStyle(Paint.Style.FILL);
//            canvas.drawCircle(250, 240, 50, mPaint);
//
//            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//            canvas.drawCircle(400, 240, 50, mPaint);
        }
    }
}
