package com.blog.demo.image.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasTranslationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CanvasTranslationView(this));
    }

    class CanvasTranslationView extends View {
        private Paint mRedPaint, mBluePaint;

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
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawRect(50, 50, 300, 200, mRedPaint);
            canvas.save();
            canvas.translate(50, 50);
            canvas.drawRect(50, 50, 300, 200, mBluePaint);
            canvas.restore();

        }
    }
}
