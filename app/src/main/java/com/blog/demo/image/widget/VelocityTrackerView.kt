package com.blog.demo.image.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.Scroller
import kotlin.math.abs

class VelocityTrackerView (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    private var mScroller: Scroller
    private var mVelocityTracker: VelocityTracker? = null

    private var mDownX = 0
    private var mSlide = false
    private var mMaxVelocity = 0
    private var mTouchSlop = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mScroller = Scroller(context)

        val configuration = ViewConfiguration.get(context)
        mMaxVelocity = configuration.scaledMaximumFlingVelocity
        mTouchSlop = configuration.scaledTouchSlop
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = ev.x.toInt()
                // 请求一个新的VelocityTracker
                acquireVelocityTracker(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                if (mVelocityTracker != null) {
                    // 添加触摸对象
                    mVelocityTracker?.addMovement(ev)
                    // 计算当前速率
                    mVelocityTracker?.computeCurrentVelocity(1000, mMaxVelocity.toFloat())
                    val velocityX = mVelocityTracker?.xVelocity ?: 0f
                    if (abs(velocityX) > 600 && abs(ev.x - mDownX) > mTouchSlop) {
                        mSlide = true
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (mSlide) {
                    // 回到初始位置
                    backToOrigin(mDownX - ev.x.toInt(), 0)
                    mSlide = false
                }
                if (mVelocityTracker != null) releaseVelocityTracker()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> if (mSlide) {
                scrollTo(mDownX - event.x.toInt(), 0)
            }
        }
        return true
    }

    private fun acquireVelocityTracker(event: MotionEvent) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker?.addMovement(event)
    }

    private fun releaseVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker?.clear()
            mVelocityTracker?.recycle()
            mVelocityTracker = null
        }
    }

    // 移动到原点
    fun backToOrigin(scrollX: Int, scrollY: Int) {
        mScroller.startScroll(scrollX, scrollY, -scrollX, -scrollY, 1000)
        postInvalidate()
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }

}