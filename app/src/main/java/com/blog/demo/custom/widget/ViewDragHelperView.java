package com.blog.demo.custom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

public class ViewDragHelperView extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mClickableView;

    public ViewDragHelperView(@NonNull Context context) {
        this(context, null);
    }

    public ViewDragHelperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragHelperView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper = ViewDragHelper.create(this, new CustomCallback());

        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mClickableView = getChildAt(getChildCount() - 1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    private class CustomCallback extends ViewDragHelper.Callback {
        private int mLeft;
        private int mTop;

        @Override
        public boolean tryCaptureView(@NonNull View view, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            Log.d("ViewDragHelperView", "clampViewPositionHorizontal left = " + left + ", dx = " + dx);
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            Log.d("ViewDragHelperView", "clampViewPositionVertical top = " + top + ", dy = " + dy);
            return top;
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            Log.d("ViewDragHelperView", "onViewCaptured capturedChild = " + capturedChild.getClass().getName());
            mLeft = capturedChild.getLeft();
            mTop = capturedChild.getTop();
            Log.d("ViewDragHelperView", "onViewCaptured mLeft = " + mLeft + ", mTop = " + mTop);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            mViewDragHelper.settleCapturedViewAt(mLeft, mTop);
            invalidate();
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            Log.d("ViewDragHelperView", "onEdgeDragStarted");
            if (edgeFlags == ViewDragHelper.EDGE_LEFT) {
                mViewDragHelper.captureChildView(getChildAt(0), pointerId);
            }
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            Log.d("ViewDragHelperView", "onEdgeTouched");
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            if (child == mClickableView) {
                return 1;
            }
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(@NonNull View child) {
            if (child == mClickableView) {
                return 1;
            }
            return super.getViewVerticalDragRange(child);
        }

    }

}
