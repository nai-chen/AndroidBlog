package com.blog.demo.image.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintPathActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintPathView(this));
    }

    class PaintPathView extends View {
        private Path mPath = new Path();
        private Paint mPaint = new Paint();
        private int mPhase = 0;

        public PaintPathView(Context context) {
            this(context, null);
        }

        public PaintPathView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mPath.moveTo(50, 50);
            for (int index = 1; index < 20; index++) {
                mPath.lineTo(50 + 30 * index, 50 + (float) (60 * Math.random()));
            }

            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Path path = new Path();
            path.addCircle(0, 0, 3, Path.Direction.CW);

            PathEffect[] effects = new PathEffect[]{null,
                    new CornerPathEffect(25),
                    new DashPathEffect(new float[]{20, 5, 10, 5}, mPhase),
                    new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE),
                    new DiscretePathEffect(3.0f, 5.0f),
                    new DiscretePathEffect(5.0f, 3.0f)
            };

            drawPath(canvas, 0, effects[0]);

            drawPath(canvas, 80, effects[1]);

            drawPath(canvas, 160, effects[2]);

            drawPath(canvas, 240, effects[3]);

            drawPath(canvas, 320, effects[4]);
            drawPath(canvas, 400, effects[5]);

            mPhase++;
            invalidate();
        }

        private void drawPath(Canvas canvas, int dy, PathEffect effect) {
            canvas.save();

            canvas.translate(0, dy);
            mPaint.setPathEffect(effect);
            canvas.drawPath(mPath, mPaint);

            canvas.restore();
        }

    }


}
