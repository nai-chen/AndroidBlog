package com.blog.demo.custom.control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/4/7.
 */

public class GestureView extends View {

    private int mWidth;
    private int mHeight;
    private boolean mNormalStatus = true;

    private int mValidCount = 4;

    private List<CircleArea> mTotalCircles = new ArrayList<CircleArea>();
    private List<CircleArea> mLinkCircle = new ArrayList<CircleArea>();
    private Bitmap mNormalBitmap, mClickBitmap, mErrorBitmap;
    private Paint mBitmapPaint, mLinePaint;
    private int mNormalColor, mErrorColor;
    private PointF mCurrentPoint = null;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mNormalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_normal);
        mClickBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_click);
        mErrorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_error);

        mNormalColor = getResources().getColor(R.color.color_eed400);
        mErrorColor = getResources().getColor(R.color.color_ff7088);

        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(getResources()
                .getDimensionPixelOffset(R.dimen.margin_xdpi_5));
        mLinePaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        int squareWidth = Math.min(mWidth, mHeight);
        // 界面被3等分，每个手势占1/6
        float radius = squareWidth / 12.0f;
        float space = squareWidth / 3.0f;
        int beginX = (mWidth - squareWidth) / 2;
        int beginY = (mHeight - squareWidth) / 2;

        mTotalCircles.clear();
        mTotalCircles.add(new CircleArea(beginX + space*0.5f, beginY + space*0.5f, radius, 1));
        mTotalCircles.add(new CircleArea(beginX + space*1.5f, beginY + space*0.5f, radius, 2));
        mTotalCircles.add(new CircleArea(beginX + space*2.5f, beginY + space*0.5f, radius, 3));

        mTotalCircles.add(new CircleArea(beginX + space*0.5f, beginY + space*1.5f, radius, 4));
        mTotalCircles.add(new CircleArea(beginX + space*1.5f, beginY + space*1.5f, radius, 5));
        mTotalCircles.add(new CircleArea(beginX + space*2.5f, beginY + space*1.5f, radius, 6));

        mTotalCircles.add(new CircleArea(beginX + space*0.5f, beginY + space*2.5f, radius, 7));
        mTotalCircles.add(new CircleArea(beginX + space*1.5f, beginY + space*2.5f, radius, 8));
        mTotalCircles.add(new CircleArea(beginX + space*2.5f, beginY + space*2.5f, radius, 9));

        int width = (int)(radius * 2);
        mNormalBitmap = Bitmap.createScaledBitmap(mNormalBitmap, width, width, false);
        mClickBitmap = Bitmap.createScaledBitmap(mClickBitmap, width, width, false);
        mErrorBitmap = Bitmap.createScaledBitmap(mErrorBitmap, width, width, false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mNormalStatus) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    getContainCircle(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    getContainCircle(event);
                    break;
                case MotionEvent.ACTION_UP:
                    getContainCircle(event);
                    if (mLinkCircle.size() < mValidCount) {
                        Toast.makeText(getContext(), "InvalidCount " + mLinkCircle.size(),
                                Toast.LENGTH_LONG).show();
                        mNormalStatus = false;
                    } else {
                        Toast.makeText(getContext(), "Success " + getLinkText(),
                                Toast.LENGTH_LONG).show();
                    }
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearCircle();
                            postInvalidate();
                        }
                    }, 2000);
                    break;
            }
            postInvalidate();
        }
        return true;
    }

    private void getContainCircle(MotionEvent event) {
        mCurrentPoint = new PointF(event.getX(), event.getY());

        for(CircleArea circle : mLinkCircle) {
            if (circle.contain(event.getX(), event.getY())) {
                return;
            }
        }

        for (CircleArea circle : mTotalCircles) {
            if (circle.contain(event.getX(), event.getY())) {
                mLinkCircle.add(circle);
                circle.setSelection(true);
                return;
            }
        }
    }

    private String getLinkText() {
        String text = "";
        for(CircleArea circle : mLinkCircle) {
            text += circle.getValue();
        }
        return text;
    }

    private void clearCircle() {
        for (CircleArea circle : mLinkCircle) {
            circle.setSelection(false);
        }
        mLinkCircle.clear();
        mCurrentPoint = null;
        mNormalStatus = true;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.black));
        if (mLinkCircle.size() > 0) {
            if (mNormalStatus) {
                mLinePaint.setColor(mNormalColor);
            } else {
                mLinePaint.setColor(mErrorColor);
            }
            CircleArea lastCircle = mLinkCircle.get(0);
            for (int i = 1; i < mLinkCircle.size(); i++) {
                CircleArea circle = mLinkCircle.get(i);
                canvas.drawLine(lastCircle.getX(), lastCircle.getY(),
                        circle.getX(), circle.getY(), mLinePaint);
                lastCircle = circle;
            }
            if (mCurrentPoint != null && mNormalStatus) {
                canvas.drawLine(lastCircle.getX(), lastCircle.getY(),
                        mCurrentPoint.x, mCurrentPoint.y, mLinePaint);
            }
        }

        for (CircleArea circle : mTotalCircles) {
            drawCircle(canvas, circle);
        }

    }

    private void drawCircle(Canvas canvas, CircleArea circle) {
        Bitmap circleBitmap = mNormalBitmap;
        if (circle.isSelection()) {
            if (mNormalStatus) {
                circleBitmap = mClickBitmap;
            } else {
                circleBitmap = mErrorBitmap;
            }
        }

        canvas.drawBitmap(circleBitmap, circle.getX() - circle.getRadius(),
                circle.getY() - circle.getRadius(), mBitmapPaint);
    }

    private static class CircleArea {
        private float x;
        private float y;
        private float radius;
        private boolean mSelection;
        private int mValue;

        public CircleArea(float x, float y, float radius, int value) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.mValue = value;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getRadius() {
            return radius;
        }

        public boolean contain(float x, float y) {
            return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) < radius;
        }

        public void setSelection(boolean selection) {
            mSelection = selection;
        }
        public boolean isSelection() {
            return mSelection;
        }

        public int getValue() {
            return mValue;
        }

    }

}
