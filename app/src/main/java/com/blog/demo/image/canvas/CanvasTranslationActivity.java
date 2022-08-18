package com.blog.demo.image.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasTranslationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CanvasTranslationView(this));
    }

    class CanvasTranslationView extends View {
        private Paint mRedPaint, mBluePaint, mGreenPaint;

        public CanvasTranslationView(Context context) {
            this(context, null);
        }

        public CanvasTranslationView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setAntiAlias(true);
            mRedPaint.setStyle(Paint.Style.FILL);

            mBluePaint = new Paint(mRedPaint);
            mBluePaint.setColor(Color.BLUE);
            mBluePaint.setAlpha(100);

            mGreenPaint = new Paint(mRedPaint);
            mGreenPaint.setColor(Color.GREEN);
            mGreenPaint.setAlpha(50);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Rect rect = new Rect(0, 0, 250, 100);

            canvas.save();
            canvas.translate(100, 100);
            canvas.drawRect(rect, mRedPaint);
            canvas.translate(50, 50);
            canvas.drawRect(rect, mBluePaint);
            canvas.restore();

            canvas.save();
            canvas.translate(600, 100);
            canvas.drawRect(rect, mRedPaint);
            canvas.rotate(45);
            canvas.drawRect(rect, mBluePaint);
            canvas.restore();

            canvas.save();
            canvas.translate(600, 100);
            canvas.rotate(90, 125, 50);
            canvas.drawRect(rect, mGreenPaint);
            canvas.restore();

            canvas.save();
            canvas.translate(100, 500);
            canvas.drawRect(rect, mRedPaint);
            canvas.skew(1, 0);
            canvas.drawRect(rect, mBluePaint);
            canvas.restore();

            canvas.save();
            canvas.translate(100, 500);
            canvas.skew(0, 1);
            canvas.drawRect(rect, mGreenPaint);
            canvas.restore();

            canvas.save();
            canvas.translate(600, 500);
            canvas.drawRect(rect, mRedPaint);
            canvas.scale(0.5f, 2f);
            canvas.drawRect(rect, mBluePaint);
            canvas.restore();
        }

    }


}
