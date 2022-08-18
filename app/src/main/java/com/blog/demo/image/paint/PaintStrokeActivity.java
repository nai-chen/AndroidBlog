package com.blog.demo.image.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintStrokeActivity extends Activity {
    private Paint mRedPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PaintStrokeView(this));
    }

    class PaintStrokeView extends View {

        public PaintStrokeView(Context context) {
            this(context, null);
        }

        public PaintStrokeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setAntiAlias(true);  // 设置是否使用抗锯齿功能
            mRedPaint.setStrokeWidth(5);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mRedPaint.setStrokeWidth(5);
            mRedPaint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(50, 50, 400, 50, mRedPaint);

            mRedPaint.setStrokeWidth(20);
            canvas.drawLine(50, 100, 400, 100, mRedPaint);

            mRedPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(50, 150, 400, 150, mRedPaint);

            mRedPaint.setStrokeCap(Paint.Cap.SQUARE);
            canvas.drawLine(50, 200, 400, 200, mRedPaint);

            mRedPaint.setStrokeCap(Paint.Cap.ROUND);
            mRedPaint.setStrokeJoin(Paint.Join.MITER);
            canvas.drawRect(50, 250, 400, 400, mRedPaint);

            mRedPaint.setStrokeJoin(Paint.Join.ROUND);
            canvas.drawRect(50, 450, 400, 600, mRedPaint);

            mRedPaint.setStrokeJoin(Paint.Join.BEVEL);
            canvas.drawRect(50, 650, 400, 800, mRedPaint);
        }

    }

}
