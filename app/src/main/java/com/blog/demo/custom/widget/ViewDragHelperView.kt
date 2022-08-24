package com.blog.demo.custom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper
import com.blog.demo.LogTool

class ViewDragHelperView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        FrameLayout(context, attrs, defStyleAttr) {

    private val LOG_TAG = "ViewDragHelperView"

    private var mViewDragHelper: ViewDragHelper
    private var mClickableView: View? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mViewDragHelper = ViewDragHelper.create(this, CustomCallback())
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        mClickableView = getChildAt(childCount - 1)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mViewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mViewDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate()
        }
    }

    private inner class CustomCallback : ViewDragHelper.Callback() {
        private var mLeft = 0
        private var mTop = 0
        override fun tryCaptureView(view: View, pointerId: Int): Boolean {
            return true
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            LogTool.logi(LOG_TAG, "clampViewPositionHorizontal left = $left, dx = $dx")
            return left
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            LogTool.logi(LOG_TAG, "clampViewPositionVertical top = $top, dy = $dy")
            return top
        }

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            LogTool.logi(LOG_TAG, "onViewCaptured capturedChild = " + capturedChild.javaClass.name)
            mLeft = capturedChild.left
            mTop = capturedChild.top
            LogTool.logi(LOG_TAG, "onViewCaptured mLeft = $mLeft, mTop = $mTop")
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            mViewDragHelper.settleCapturedViewAt(mLeft, mTop)
            invalidate()
        }

        override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
            LogTool.logi(LOG_TAG, "onEdgeDragStarted")
            if (edgeFlags == ViewDragHelper.EDGE_LEFT) {
                mViewDragHelper.captureChildView(getChildAt(0), pointerId)
            }
        }

        override fun onEdgeTouched(edgeFlags: Int, pointerId: Int) {
            LogTool.logi(LOG_TAG, "onEdgeTouched")
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return if (child === mClickableView) {
                1
            } else {
                super.getViewHorizontalDragRange(child)
            }
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return if (child === mClickableView) {
                1
            } else {
                super.getViewVerticalDragRange(child)
            }
        }
    }

}