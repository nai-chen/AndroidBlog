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

public class PathOpActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PathOpView(this));
    }

    class PathOpView extends View {

        private Paint mRedPaint;

        public PathOpView(Context context) {
            this(context, null);
        }

        public PathOpView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setAntiAlias(true);
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.FILL);
            mRedPaint.setStrokeWidth(5);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            drawOp(canvas, 50, 50, Path.Op.DIFFERENCE);
            drawOp(canvas, 50, 250, Path.Op.INTERSECT);
            drawOp(canvas, 50, 450, Path.Op.UNION);
            drawOp(canvas, 50, 650, Path.Op.XOR);
            drawOp(canvas, 50, 850, Path.Op.REVERSE_DIFFERENCE);
        }

        private void drawOp(Canvas canvas, float dx, float dy, Path.Op op) {
            canvas.save();
            canvas.translate(dx, dy);

            Path path1 = new Path();
            path1.addCircle(100, 100, 75, Path.Direction.CW);

            Path path2 = new Path();
            path2.addCircle(200, 100, 75, Path.Direction.CW);

            path1.op(path2, op);
            canvas.drawPath(path1, mRedPaint);

            canvas.restore();
        }

    }

}
