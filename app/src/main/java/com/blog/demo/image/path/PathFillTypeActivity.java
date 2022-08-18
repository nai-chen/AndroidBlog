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

public class PathFillTypeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PathFillTypeView(this));
    }

    class PathFillTypeView extends View {
        private Paint mRedPaint;
        private Path mPath;

        public PathFillTypeView(Context context) {
            this(context, null);
        }

        public PathFillTypeView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setAntiAlias(true);
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.FILL);
            mRedPaint.setStrokeWidth(5);

            mPath = new Path();
            mPath.addCircle(100, 100, 75, Path.Direction.CCW);
            mPath.addCircle(150, 150, 75, Path.Direction.CCW);
            mPath.addCircle(200, 200, 75, Path.Direction.CCW);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            showFillType(canvas, 50, 50, Path.FillType.WINDING);
            showFillType(canvas, 400, 50, Path.FillType.EVEN_ODD);
            showFillType(canvas, 50, 400, Path.FillType.INVERSE_WINDING);
            showFillType(canvas, 400, 400, Path.FillType.INVERSE_EVEN_ODD);
        }

        private void showFillType(Canvas canvas, int dx, int dy, Path.FillType ft) {
            canvas.save();
            canvas.translate(dx, dy);
            canvas.clipRect(0, 0, 300, 300);
            canvas.drawColor(Color.WHITE);
            mPath.setFillType(ft);

            canvas.drawPath(mPath, mRedPaint);
            canvas.restore();
        }
    }

}
