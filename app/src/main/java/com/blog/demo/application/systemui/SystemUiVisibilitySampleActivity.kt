package com.blog.demo.application.systemui

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class SystemUiVisibilitySampleActivity : Activity() {

    private val LOG_TAG = "SystemUiVisibilitySampleActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val value = intent.getIntExtra("value", 0)
        val fitsSystemWindows = intent.getBooleanExtra("fitsSystemWindows", false)
        logi(LOG_TAG, "uiOptions = $value")
        logi(LOG_TAG, "fitsSystemWindows = $fitsSystemWindows")

        window.decorView.systemUiVisibility = value
        if (value and View.SYSTEM_UI_FLAG_FULLSCREEN == View.SYSTEM_UI_FLAG_FULLSCREEN) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_application_system_ui_visibility_sample)
        findViewById<View>(R.id.layout_system_ui_visibility).fitsSystemWindows = fitsSystemWindows
    }

}