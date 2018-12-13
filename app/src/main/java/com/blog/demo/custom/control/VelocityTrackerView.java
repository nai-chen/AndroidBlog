package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class VelocityTrackerView extends RelativeLayout {
	private VelocityTracker mVelocityTracker;
	private Scroller mScroller;

	private int mMaxVelocity, mMinVelocity, mTouchSlop;
	private int mDownX;
	private boolean mSlide;

	public VelocityTrackerView(Context context) {
		this(context, null);
	}

	public VelocityTrackerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
		mMinVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		LogUtil.log("CVelocityTrackerView", "MaxVelocity = " + mMaxVelocity);
		LogUtil.log("CVelocityTrackerView", "MinVelocity = " + mMinVelocity);
		LogUtil.log("CVelocityTrackerView", "TouchSlop = " + mTouchSlop);
		
		mScroller = new Scroller(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch(ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				LogUtil.log("CVelocityTrackerView", "findChildOnPoint");
				mDownX = (int) ev.getX();
				acquireVelocityTracker(ev);
				break;
			case MotionEvent.ACTION_MOVE:
				if (mVelocityTracker != null) {
					mVelocityTracker.addMovement(ev);
					mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
					final float velocityX = mVelocityTracker.getXVelocity();
					final float velocityY = mVelocityTracker.getYVelocity();

					LogUtil.log("CVelocityTrackerView", "velocityX = " + velocityX + ", velocityY = " + velocityY);

					if (Math.abs(velocityX) > mMinVelocity && Math.abs(ev.getX() - mDownX) > mTouchSlop) {
						mSlide = true;
					}
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				if (mVelocityTracker != null )
					releaseVelocityTracker();
				if (mSlide) {
					backToOrigin(mDownX - (int)ev.getX(), 0);
					mSlide = false;
				}
				break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				if (mSlide) {
					scrollTo(mDownX - (int)event.getX(), 0);
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

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

			postInvalidate();
		}
	}

	public void backToOrigin (int scrollX, int scrollY) {
		mScroller.startScroll(scrollX, scrollY, -scrollX, -scrollY, 1000);
		postInvalidate();
	}

}
