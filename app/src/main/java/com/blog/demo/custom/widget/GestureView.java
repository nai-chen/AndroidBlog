package com.blog.demo.custom.widget;

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

import androidx.annotation.Nullable;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class GestureView extends View {
    private static final int INVALID_COUNT = 4;

    enum Status {
        Normal,
        Error
    }

    private Bitmap mNormalBmp, mClickBmp, mErrorBmp;
    private int mNormalColor, mErrorColor;
    private Paint mBitmapPaint, mLinePaint;

    private List<CircleArea> mTotalCircles = new ArrayList<>();
    private List<CircleArea> mLinkCircle = new ArrayList<>();

    private PointF mCurrentPoint = null;
    private Status mStatus = Status.Normal;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mNormalBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_normal);
        mClickBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_click);
        mErrorBmp = BitmapFactory.decodeResource(getResources(), R.drawable.gesture_circle_error);

        mNormalColor = getResources().getColor(R.color.gesture_normal_line);
        mErrorColor = getResources().getColor(R.color.gesture_error_line);

        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(10);
        mLinePaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        int squareWidth = Math.min(measuredWidth, measuredHeight);
        // 界面被3等分，每个手势占1/6
        float radius = squareWidth / 12.0f;
        float startX = (measuredWidth - squareWidth) / 2.0f;
        float startY = (measuredHeight - squareWidth) / 2.0f;

        mTotalCircles.clear();
        CircleArea circleArea = new CircleArea(startX + radius*2, startY + radius*2, radius, 1);
        mTotalCircles.add(circleArea);
        mTotalCircles.add(circleArea.moveTo(radius*4, 0, 2));
        mTotalCircles.add(circleArea.moveTo(radius*8, 0, 3));

        mTotalCircles.add(circleArea.moveTo(0, radius*4, 4));
        mTotalCircles.add(circleArea.moveTo(radius*4, radius*4, 5));
        mTotalCircles.add(circleArea.moveTo(radius*8, radius*4, 6));

        mTotalCircles.add(circleArea.moveTo(0, radius*8, 7));
        mTotalCircles.add(circleArea.moveTo(radius*4, radius*8, 8));
        mTotalCircles.add(circleArea.moveTo(radius*8, radius*8, 9));

        int width = (int)(radius * 2);
        mNormalBmp = Bitmap.createScaledBitmap(mNormalBmp, width, width, false);
        mClickBmp = Bitmap.createScaledBitmap(mClickBmp, width, width, false);
        mErrorBmp = Bitmap.createScaledBitmap(mErrorBmp, width, width, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.black));
        // 如果选择列表不为空
        if (mLinkCircle.size() > 0) {
            // 设置连接线的颜色
            if (mStatus == Status.Normal) {
                mLinePaint.setColor(mNormalColor);
            } else {
                mLinePaint.setColor(mErrorColor);
            }
            // 手势键盘之间的连接线
            CircleArea lastCircle = mLinkCircle.get(0);
            for (int i = 1; i < mLinkCircle.size(); i++) {
                CircleArea circle = mLinkCircle.get(i);
                canvas.drawLine(lastCircle.x, lastCircle.y, circle.x, circle.y, mLinePaint);
                lastCircle = circle;
            }
            // 手势键盘和当前点之间的连接线
            if (mCurrentPoint != null && mStatus == Status.Normal) {
                canvas.drawLine(lastCircle.x, lastCircle.y, mCurrentPoint.x, mCurrentPoint.y, mLinePaint);
            }
        }

        for (CircleArea circle : mTotalCircles) {
            drawCircle(canvas, circle);
        }
    }

    private void drawCircle(Canvas canvas, CircleArea circle) {
        Bitmap circleBitmap = mNormalBmp;
        if (mLinkCircle.contains(circle)) {
            if (mStatus == Status.Normal) {
                circleBitmap = mClickBmp;
            } else {
                circleBitmap = mErrorBmp;
            }
        }
        // 不同状态下绘制不同的图片。
        canvas.drawBitmap(circleBitmap, circle.x - circle.radius, circle.y - circle.radius, mBitmapPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 只有在正常状态下，触摸才生效
        if (mStatus == Status.Normal) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    // 记录手势
                    moveTo(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    moveTo(event.getX(), event.getY());

                    // 如果小于有效数量
                    if (mLinkCircle.size() < INVALID_COUNT) {
                        Toast.makeText(getContext(), "InvalidCount " + mLinkCircle.size(),
                                Toast.LENGTH_LONG).show();
                        // 错误状态，界面不可点
                        mStatus = Status.Error;
                        // 2秒后清空
                        postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                clearCircle();
                                postInvalidate();
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(getContext(), "Success " + getLinkText(),
                                Toast.LENGTH_LONG).show();
                        clearCircle();
                    }
                    break;
            }
            postInvalidate();
        }
        return true;
    }

    private void moveTo(float x, float y) {
        // 当前触摸点
        mCurrentPoint = new PointF(x, y);

        // 如果不在选择列表中，加入选择列表
        for (CircleArea circle : mTotalCircles) {
            if (circle.contain(x, y) && !mLinkCircle.contains(circle)) {
                if (!mLinkCircle.contains(circle)) {
                    mLinkCircle.add(circle);
                }
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
        mLinkCircle.clear();
        mCurrentPoint = null;
        mStatus = Status.Normal;
    }

    static class CircleArea {
        float x, y; // 圆心X
        float radius; // 圆半径
        int mValue; // 值

        public CircleArea(float x, float y, float radius, int value) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.mValue = value;
        }

        public CircleArea moveTo(float dx, float dy, int value) {
            return new CircleArea(x + dx, y + dy, radius, value);
        }

        // 该点是否在圆内
        public boolean contain(float x, float y) {
            return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) < radius;
        }

        public int getValue() {
            return mValue;
        }

    }

}
