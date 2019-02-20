package com.blog.demo.image.path;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PathNormalActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PathNormalView(this));
    }

    class PathNormalView extends View {

        private Paint mRedPaint;

        public PathNormalView(Context context) {
            this(context, null);
        }

        public PathNormalView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setAntiAlias(true);
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setStrokeWidth(5);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Path path = new Path();
            path.moveTo(50, 50);
            path.lineTo(200, 100);
            path.rMoveTo(50, 0);
            path.rLineTo(100, 100);

            path.addRect(50, 250, 300, 350, Path.Direction.CW);
            path.addRoundRect(50, 400, 300, 500, 20, 10, Path.Direction.CW);
            path.addRoundRect(400, 400, 650, 500, new float[]{10, 20, 10, 20, 20, 10, 20, 10},
                    Path.Direction.CW);

            path.addOval(50, 550, 300, 700, Path.Direction.CW);
            path.addCircle(525, 625, 75, Path.Direction.CW);

            path.addArc(50, 750, 300, 900, 0, 180);
            path.addArc(400, 750, 650, 900, 90, 225);

            path.arcTo(50, 950, 300, 1100, 0, 180, true);
            path.arcTo(400, 950, 650, 1100, 90, 225, false);
            canvas.drawPath(path, mRedPaint);
        }
    }

}
