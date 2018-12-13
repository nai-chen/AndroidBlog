package com.blog.demo.media;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/4/7.
 */

public class FontMetricsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contentView = new FontMetricsView(this);
        contentView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setContentView(contentView);
    }

    private static class FontMetricsView extends View {
        public FontMetricsView(Context context) {
            super(context);
        }

        @Override
        public void draw(Canvas canvas) {
//            super.draw(canvas);

            canvas.drawColor(getResources().getColor(R.color.product_bg));

            String text = "abfgABFG";
            Paint paint = new Paint();
            paint.setTextSize(100);
            float width = paint.measureText(text);
            int beginX = 80;
            int beginY = 200;

            Paint textPaint = new Paint();
            textPaint.setTextSize(36);

            paint.setColor(getResources().getColor(R.color.black));
            canvas.drawText(text, beginX, beginY, paint);
            canvas.drawLine(0, beginY, beginX + width + beginX, beginY, paint);
            textPaint.setColor(getResources().getColor(R.color.black));
            canvas.drawText("base", beginX + width, beginY, textPaint);

            Paint.FontMetrics fm = paint.getFontMetrics();

            paint.setColor(getResources().getColor(R.color.red));
            canvas.drawLine(0, beginY + fm.top, beginX + width + beginX, beginY + fm.top, paint);
            LogUtil.log("FontMetricsActivity", "fm.top = " + fm.top);
            textPaint.setColor(getResources().getColor(R.color.red));
            canvas.drawText("top", 40, beginY + fm.top, textPaint);

            paint.setColor(getResources().getColor(R.color.blue));
            canvas.drawLine(0, beginY + fm.ascent, beginX + width + beginX, beginY + fm.ascent, paint);
            LogUtil.log("FontMetricsActivity", "fm.ascent = " + fm.ascent);
            textPaint.setColor(getResources().getColor(R.color.blue));
            canvas.drawText("ascent", width, beginY + fm.ascent, textPaint);

            paint.setColor(getResources().getColor(R.color.green));
            canvas.drawLine(0, beginY + fm.descent, beginX + width + beginX, beginY + fm.descent, paint);
            LogUtil.log("FontMetricsActivity", "fm.descent = " + fm.descent);
            textPaint.setColor(getResources().getColor(R.color.green));
            canvas.drawText("descent", 40, beginY + fm.descent, textPaint);

            paint.setColor(getResources().getColor(R.color.red));
            canvas.drawLine(0, beginY + fm.bottom, beginX + width + beginX, beginY + fm.bottom, paint);
            LogUtil.log("FontMetricsActivity", "fm.bottom = " + fm.bottom);
            textPaint.setColor(getResources().getColor(R.color.red));
            canvas.drawText("bottom", width, beginY + fm.bottom, textPaint);
        }

    }

}
