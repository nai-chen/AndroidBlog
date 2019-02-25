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

import com.blog.demo.R;

public class CanvasViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new CanvasView(this));
    }

    class CanvasView extends View {
        private Paint mRedPaint, mBluePaint;

        public CanvasView(Context context) {
            this(context, null);
        }

        public CanvasView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setStrokeWidth(5);

            mBluePaint = new Paint();
            mBluePaint.setColor(Color.BLUE);
            mBluePaint.setStyle(Paint.Style.FILL);
            mBluePaint.setStrokeWidth(10);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPoint(100, 50, mRedPaint);
            float[] pts = new float[]{100, 75, 150, 100, 200, 125};
            canvas.drawPoints(pts, mBluePaint);

            canvas.drawLine(100, 200, 400, 200, mRedPaint);
            pts = new float[]{100, 250, 400, 250,
                    400, 250, 250, 450,
                    250, 450, 100, 250};
            canvas.drawLines(pts, mBluePaint);

            canvas.drawRect(100, 500, 300, 600, mRedPaint);
            canvas.drawRect(400, 500, 600, 600, mBluePaint);

            canvas.drawRoundRect(100, 650, 300, 750, 40, 20, mRedPaint);
            canvas.drawRoundRect(400, 650, 600, 750, 40, 20, mBluePaint);

            canvas.drawOval(100, 800, 300, 900, mRedPaint);
            canvas.drawOval(400, 800, 600, 900, mBluePaint);

            canvas.drawCircle(200, 1000, 50, mRedPaint);
            canvas.drawCircle(500, 1000, 50, mBluePaint);

            canvas.drawArc(100, 1100, 300, 1200, 45, 225, false, mRedPaint);
            canvas.drawArc(400, 1100, 600, 1200, 45, 225, true, mRedPaint);

            canvas.drawArc(100, 1250, 300, 1350, 45, 225, false, mBluePaint);
            canvas.drawArc(400, 1250, 600, 1350, 45, 225, true, mBluePaint);
        }
    }

}
