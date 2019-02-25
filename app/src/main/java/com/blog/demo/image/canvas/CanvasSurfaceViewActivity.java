package com.blog.demo.image.canvas;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.blog.demo.R;

public class CanvasSurfaceViewActivity extends Activity {
    private Paint mRedPaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_canvas_surface_view);

        SurfaceView surfaceView = findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(getResources().getColor(R.color.colorProduct));
                draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });

        mRedPaint = new Paint();
        mRedPaint.setColor(Color.RED);
        mRedPaint.setStyle(Paint.Style.FILL);
    }

    private void draw(Canvas canvas) {
        drawClipPath(canvas, 0, 50, Region.Op.DIFFERENCE);
        drawClipPath(canvas, 300, 50, Region.Op.INTERSECT);
        drawClipPath(canvas, 0, 250, Region.Op.UNION);
        drawClipPath(canvas, 300, 250, Region.Op.XOR);
        drawClipPath(canvas, 0, 450, Region.Op.REVERSE_DIFFERENCE);
        drawClipPath(canvas, 300, 450, Region.Op.REPLACE);
    }

    private void drawClipPath(Canvas canvas, float dx, float dy, Region.Op op) {
        canvas.save();
        canvas.translate(dx, dy);

        Path path1 = new Path();
        path1.addCircle(150, 100, 75, Path.Direction.CW);
        canvas.clipPath(path1);

        Path path2 = new Path();
        path2.addCircle(250, 100, 75, Path.Direction.CW);
        canvas.clipPath(path2, op);
        canvas.drawColor(Color.RED);

        canvas.restore();
    }

}
