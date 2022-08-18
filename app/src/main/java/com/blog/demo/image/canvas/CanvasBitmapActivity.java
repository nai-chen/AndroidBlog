package com.blog.demo.image.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class CanvasBitmapActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_canvas_bitmap);

        ImageView imageView = findViewById(R.id.image_view);

        Bitmap bitmap = Bitmap.createBitmap(600, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);

        imageView.setImageBitmap(bitmap);
    }

    private void draw(Canvas canvas) {
        final String text = "This is a text";
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL);
        redPaint.setTextSize(20);
        redPaint.setAntiAlias(true); // 设置是否使用抗锯齿功能

        canvas.drawText(text, 50, 30, redPaint);
        canvas.drawText(text, 2, 14, 50, 55, redPaint);

        Paint paint = new Paint(redPaint);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(50, 100);
        path.lineTo(300, 200);
        canvas.drawPath(path, paint);
        canvas.drawTextOnPath(text, path, 80, 20, redPaint);
    }


}
