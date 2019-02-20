package com.blog.demo.image.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class FontMetricsView extends View {
    private static final String LOG_TAG = "FontMetricsView";

    public FontMetricsView(Context context) {
        super(context);
    }

    public FontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        String text = "abfgABFG";

        Paint paint = new Paint();
        paint.setTextSize(100);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float width = paint.measureText(text);

        float beginX = 80, endX = beginX * 2 + width;
        float base = 200;
        canvas.drawText(text, beginX, base, paint);

        // base line
        canvas.drawLine(0, base, endX, base, paint);

        LogTool.logi(LOG_TAG, "fm.top = " + fm.top);
        LogTool.logi(LOG_TAG, "fm.ascent = " + fm.ascent);
        LogTool.logi(LOG_TAG, "fm.bottom = " + fm.bottom);
        LogTool.logi(LOG_TAG, "fm.descent = " + fm.descent);

        // top
        float top = base + fm.top;
        paint.setColor(getResources().getColor(R.color.red));
        canvas.drawLine(0, top, endX, top, paint);
        // ascent
        float ascent = base + fm.ascent;
        paint.setColor(getResources().getColor(R.color.green));
        canvas.drawLine(0, ascent, endX, ascent, paint);
        // bottom
        float bottom = base + fm.bottom;
        paint.setColor(getResources().getColor(R.color.red));
        canvas.drawLine(0, bottom, endX, bottom, paint);
        // ascent
        float descent = base + fm.descent;
        paint.setColor(getResources().getColor(R.color.green));
        canvas.drawLine(0, descent, endX, descent, paint);

        Paint textPaint = new Paint();
        textPaint.setTextSize(36);
        canvas.drawText("base", beginX + width, base, textPaint);
        canvas.drawText("top", 40, top, textPaint);
        canvas.drawText("ascent", width, ascent, textPaint);
        canvas.drawText("bottom", 40, bottom, textPaint);
        canvas.drawText("descent", width, descent, textPaint);
    }

}
