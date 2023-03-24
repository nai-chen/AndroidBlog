package com.blog.demo.control.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blog.demo.LogTool

class TouchEventView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        View(context, attrs, defStyleAttr) {

    val LOG_TAG = "TouchEventView"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogTool.logi(LOG_TAG, "before dispatchTouchEvent event = ${event.action}")
        val handle = super.dispatchTouchEvent(event)
        LogTool.logi(LOG_TAG, "after dispatchTouchEvent handle = $handle")
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