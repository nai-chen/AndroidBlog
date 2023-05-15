package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import com.blog.demo.LogTool
import com.blog.demo.R

class TouchEventActivity : Activity() {

    private val LOG_TAG = "TouchEventActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_touch_event)
    }

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
    }
}