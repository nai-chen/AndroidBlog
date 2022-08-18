package com.blog.demo.image.path;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathTextActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PathTextView(this));
    }

    class PathTextView extends View {

        private Paint mRedPaint;
        private Paint mBluePaint;

        public PathTextView(Context context) {
            this(context, null);
        }

        public PathTextView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setAntiAlias(true);
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.STROKE);
            mRedPaint.setStrokeWidth(5);

            mBluePaint = new Paint();
            mBluePaint.setAntiAlias(true);
            mBluePaint.setColor(Color.BLUE);
            mBluePaint.setStyle(Paint.Style.FILL);
            mBluePaint.setTextSize(50);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Path path = new Path();
            path.addOval(50, 50, 300, 200, Path.Direction.CW);
            canvas.drawPath(path, mRedPaint);
            canvas.drawTextOnPath("This is a text", path, 0, 0, mBluePaint);

            path.reset();
            path.addOval(400, 50, 650, 200, Path.Direction.CCW);
            canvas.drawPath(path, mRedPaint);
            canvas.drawTextOnPath("This is a text", path, 0, 0, mBluePaint);
        }
    }

}
