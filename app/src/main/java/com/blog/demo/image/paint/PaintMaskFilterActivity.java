package com.blog.demo.image.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintMaskFilterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PaintMaskFilterView(this));
    }

    class PaintMaskFilterView extends View {
        private Paint mPaint = new Paint();

        public PaintMaskFilterView(Context context) {
            this(context, null);
        }

        public PaintMaskFilterView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.RED);

            setLayerType(LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            mPaint.setMaskFilter(null);
            canvas.drawRect(50, 50, 300, 200, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
            canvas.drawRect(50, 300, 300, 450, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID));
            canvas.drawRect(400, 300, 650, 450, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER));
            canvas.drawRect(50, 550, 300, 700, mPaint);

            mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.INNER));
            canvas.drawRect(400, 550, 650, 700, mPaint);
        }
    }
}
