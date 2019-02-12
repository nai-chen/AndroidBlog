package com.blog.demo.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.TextView;

public class VelocityTrackerView extends TextView {

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private int mDownX;
    private boolean mSlide;
    private int mMaxVelocity;
    private int mTouchSlop;

    public VelocityTrackerView(Context context) {
        this(context, null);
    }

    public VelocityTrackerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);

        ViewConfiguration configuration = ViewConfiguration.get(context);
        mMaxVelocity = configuration.getScaledMaximumFlingVelocity();
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                // 请求一个新的VelocityTracker
                acquireVelocityTracker(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mVelocityTracker != null) {
                    // 添加触摸对象
                    mVelocityTracker.addMovement(ev);
                    // 计算当前速率
                    mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                    final float velocityX = mVelocityTracker.getXVelocity();
                    if (Math.abs(velocityX) > 600 &&
                            Math.abs(ev.getX() - mDownX) > mTouchSlop) {
                        mSlide = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mSlide) {
                    // 回到初始位置
                    backToOrigin(mDownX - (int) ev.getX(), 0);
                    mSlide = false;
                }
                if (mVelocityTracker != null )
                    releaseVelocityTracker();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mSlide) {
                    scrollTo(mDownX - (int) event.getX(), 0);
                }
                break;
        }
        return true;
    }

    private void acquireVelocityTracker(final MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        mVelocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    // 移动到原点
    public void backToOrigin (int scrollX, int scrollY) {
        mScroller.startScroll(scrollX, scrollY, -scrollX, -scrollY, 1000);
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            postInvalidate();
        }
    }
}
