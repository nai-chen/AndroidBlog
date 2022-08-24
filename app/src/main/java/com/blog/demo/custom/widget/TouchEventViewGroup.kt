package com.blog.demo.custom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.blog.demo.LogTool

class TouchEventViewGroup(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        RelativeLayout(context, attrs, defStyleAttr) {

    private val LOG_TAG = "TouchRelativeLayout"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogTool.logi(LOG_TAG, "before dispatchTouchEvent event = ${event.action}")
        val handle: Boolean = super.dispatchTouchEvent(event)
        LogTool.logi(LOG_TAG, "after dispatchTouchEvent handle = $handle")
        return handle
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogTool.logi(LOG_TAG, "before onInterceptTouchEvent event = ${ev.action}")
        val handle: Boolean = super.onInterceptTouchEvent(ev)
        LogTool.logi(LOG_TAG, "after onInterceptTouchEvent handle = $handle")
        return handle
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogTool.logi(LOG_TAG, "before onTouchEvent event = ${event.action}")
        val handle = super.onTouchEvent(event)
        LogTool.logi(LOG_TAG, "after onTouchEvent handle = $handle")
        return handle
//        return event.action == MotionEvent.ACTION_DOWN
    }
}