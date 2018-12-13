package com.blog.demo.custom.control;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2018/1/16.
 */

public class VerticalScrollView extends FrameLayout {
    protected View mViewPrevious, mViewCurrent, mViewNext;

    private VelocityTracker mVelocityTracker;

    private PointF mDownPoint;
    private int mTouchSlop, mMaxVelocity;
    private boolean mScrollable, mSlide, mScrolling;
    private int mSelection;

    private BaseAdapter mAdapter;

    private ViewPager.OnPageChangeListener mListener;
    private Animation mAnimInFromBottom, mAnimInFromTop, mAnimOutToTop, mAnimOutToBottom;
    private Animation.AnimationListener mAnimInFromTopListener = new AnimInFromTopListener();
    private Animation.AnimationListener mAnimInFromBottomListener = new AnimInFromBottomListener();

    public VerticalScrollView(Context context) {
        this(context, null);
    }

    public VerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();

        mAnimInFromBottom = AnimationUtils.loadAnimation(context, R.anim.anim_enter_from_bottom);
        mAnimInFromTop = AnimationUtils.loadAnimation(context, R.anim.anim_enter_from_top);
        mAnimOutToBottom = AnimationUtils.loadAnimation(context, R.anim.anim_exit_to_bottom);
        mAnimOutToTop = AnimationUtils.loadAnimation(context, R.anim.anim_exit_to_top);

        mAnimInFromBottom.setAnimationListener(mAnimInFromBottomListener);
        mAnimInFromTop.setAnimationListener(mAnimInFromTopListener);
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null || mAdapter != null) {
            throw new IllegalStateException();
        }
        mAdapter = adapter;
        mSelection = 0;

        int count = mAdapter.getCount();
        if (count == 0) {
            mScrollable = false;
        } else if (count == 1) {
            mScrollable = false;
            mViewCurrent = getConvertView(mViewCurrent, mSelection);
        } else {
            mScrollable = true;
            mViewCurrent = getConvertView(mViewCurrent, mSelection);
            mViewNext = getConvertView(mViewNext, getNextSelection(true));

            if (count == 2) {
                mViewPrevious = mViewNext;
            } else {
                mViewPrevious = getConvertView(mViewPrevious, getNextSelection(false));
            }
        }
        if (mViewPrevious != null) {
            mViewPrevious.setVisibility(View.GONE);
        }
        if (mViewNext != null) {
            mViewNext.setVisibility(View.GONE);
        }
    }

    private View getConvertView(View convertView, int position) {
        if (convertView == null) {
            convertView = mAdapter.getView(position, null, this);
            addView(convertView);
        } else {
            convertView = mAdapter.getView(position, convertView, this);
        }
        return convertView;
    }

    private int getNextSelection(boolean next) {
        if (next) {
            return (mSelection + 1) % mAdapter.getCount();
        } else {
            return (mAdapter.getCount() + mSelection - 1) % mAdapter.getCount();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mScrollable) {
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                if (!mScrolling) {
                    mDownPoint = new PointF(event.getX(), event.getY());
                    acquireVelocityTracker(event);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.log("VerticalScrollView", "ACTION_MOVE");

                if (mVelocityTracker != null && mDownPoint != null) {
                    // 添加触摸对象
                    mVelocityTracker.addMovement(event);
                    // 计算当前速率
                    mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                    final float velocityY = mVelocityTracker.getYVelocity();
                    if (Math.abs(velocityY) > 600 &&
                            Math.abs(event.getY() - mDownPoint.y) > mTouchSlop) {
                        LogUtil.log("VerticalScrollView", "ACTION_MOVE mSlide");
                        mSlide = true;
                        event.setAction(MotionEvent.ACTION_UP);
                        onTouchEvent(event);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                LogUtil.log("VerticalScrollView", "ACTION_UP");
                if (mVelocityTracker != null ) {
                    releaseVelocityTracker();
                    if (mSlide) {
                        if (event.getY() > mDownPoint.y) {
                            moveToPrevious();
                        } else {
                            moveToNext();
                        }
                        mSlide = false;
                    }
                    mDownPoint = null;
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return true;
    }

    private void acquireVelocityTracker(MotionEvent event) {
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

    protected void moveToPrevious() {
        mScrolling = true;
        mViewPrevious.setVisibility(View.VISIBLE);

        mViewCurrent.startAnimation(mAnimOutToBottom);
        mViewPrevious.startAnimation(mAnimInFromTop);
    }

    protected void moveToNext() {
        mScrolling = true;
        mViewNext.setVisibility(View.VISIBLE);

        mViewCurrent.startAnimation(mAnimOutToTop);
        mViewNext.startAnimation(mAnimInFromBottom);
    }

    protected void animationEnd() {
    }

    private class AnimInFromTopListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            if (mListener != null) {
                mListener.onPageScrollStateChanged(ViewPager.SCROLL_STATE_DRAGGING);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mScrolling = false;

            View view = mViewNext;

            mViewNext = mViewCurrent;
            mViewNext.setVisibility(View.GONE);
            mViewCurrent = mViewPrevious;

            mSelection = getNextSelection(false);

            if (mAdapter.getCount() > 2) {
                mViewPrevious = getConvertView(view, getNextSelection(false));
                mViewPrevious.setVisibility(View.GONE);
            } else {
                mViewPrevious = mViewNext;
            }
            animationEnd();

            if (mListener != null) {
                mListener.onPageScrollStateChanged(ViewPager.SCROLL_STATE_IDLE);
                mListener.onPageSelected(mSelection);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private class AnimInFromBottomListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
            if (mListener != null) {
                mListener.onPageScrollStateChanged(ViewPager.SCROLL_STATE_DRAGGING);
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mScrolling = false;

            View view = mViewPrevious;

            mViewPrevious = mViewCurrent;
            mViewPrevious.setVisibility(View.GONE);
            mViewCurrent = mViewNext;

            mSelection = getNextSelection(true);

            if (mAdapter.getCount() > 2) {
                mViewNext = getConvertView(view, getNextSelection(true));
                mViewNext.setVisibility(View.GONE);
            } else {
                mViewNext = mViewPrevious;
            }
            animationEnd();

            if (mListener != null) {
                mListener.onPageScrollStateChanged(ViewPager.SCROLL_STATE_IDLE);
                mListener.onPageSelected(mSelection);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public void setOnPageChangeListener (ViewPager.OnPageChangeListener listener) {
        this.mListener = listener;
    }

}
