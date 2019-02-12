package com.blog.demo.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blog.demo.R;

public class AttrDeclareView extends View {
    private String mText;
    private Paint mPaint;

    public AttrDeclareView(Context context) {
        this(context, null);
    }

    public AttrDeclareView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttrDeclareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrDeclareView);
        int color = a.getColor(R.styleable.AttrDeclareView_background_color, 0);
        mText = a.getString(R.styleable.AttrDeclareView_android_text);
        int textSize = a.getDimensionPixelSize(R.styleable.AttrDeclareView_android_textSize, 0);
        int textColor = a.getColor(R.styleable.AttrDeclareView_android_textColor, 0);
        a.recycle();

        if (color != 0) {
            setBackgroundColor(color);
        }

        if (mText != null) {
            mPaint = new Paint();
            mPaint.setColor(textColor);
            mPaint.setTextSize(textSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mText != null) {
            canvas.drawText(mText, 0, getHeight()/2, mPaint);
        }
    }

}
