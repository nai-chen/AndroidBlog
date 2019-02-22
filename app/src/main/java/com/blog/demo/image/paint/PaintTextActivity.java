package com.blog.demo.image.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PaintTextActivity extends Activity {
    private Paint mRedPaint;
    private Typeface typeface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PaintTextView(this));
    }

    class PaintTextView extends View {

        public PaintTextView(Context context) {
            this(context, null);
        }

        public PaintTextView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

            mRedPaint = new Paint();
            mRedPaint.setColor(Color.RED);
            mRedPaint.setStyle(Paint.Style.FILL);
            mRedPaint.setAntiAlias(true);  // 设置是否使用抗锯齿功能

            typeface = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
        }

        @Override
        protected void onDraw(Canvas canvas) {
            final String text = "This is a text";
            mRedPaint.setTextSize(50);
            canvas.drawText(text, 50, 80, mRedPaint);

            Paint paint = new Paint(mRedPaint);
            paint.setTextSize(30);
            canvas.drawText(text, 50, 120, paint);

            paint = new Paint(mRedPaint);
            paint.setFakeBoldText(true); // 设置文本仿粗体
            canvas.drawText(text, 50, 180, paint);

            paint = new Paint(mRedPaint);
            paint.setUnderlineText(true); // 设置文字的下划线
            canvas.drawText(text, 50, 240, paint);

            paint = new Paint(mRedPaint);
            paint.setTextSkewX(-0.25f); // 设置斜体字，值为负右倾值为正左倾
            canvas.drawText(text, 50, 300, paint);

            paint.setTextSkewX(0.25f);
            canvas.drawText(text, 50, 360, paint);

            paint = new Paint(mRedPaint);
            paint.setStrikeThruText(true); // 设置文本删除线
            canvas.drawText(text, 50, 420, paint);

            paint = new Paint(mRedPaint);
            paint.setTextScaleX(2); // 文本沿X轴水平缩放，默认值为1
            canvas.drawText(text, 50, 480, paint);

            paint = new Paint(mRedPaint);
            paint.setLetterSpacing(0.1f); // 设置行的间距
            canvas.drawText(text, 50, 540, paint);

            paint = new Paint(mRedPaint);
            paint.setShadowLayer(5, 5, 5, Color.BLACK);
            canvas.drawText(text, 50, 600, paint);

            paint = new Paint(mRedPaint);
            paint.setTypeface(typeface); // 设置文本字体样式
            canvas.drawText(text, 50, 660, paint);

            paint = new Paint(mRedPaint);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(text, 400, 720, paint);

            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(text, 400, 780, paint);

            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, 400, 840, paint);

            paint.setStrokeWidth(5);
            canvas.drawLine(400, 660, 400, 860, paint);

        }

    }

}
