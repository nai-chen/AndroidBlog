package com.blog.demo.application.systemui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class SystemUiVisibilityActivity : Activity() {

    private val LOG_TAG = "SystemUiVisibilityActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_system_ui_visibility)

        val cbProfile: CheckBox = findViewById(R.id.cb_profile)
        val cbHideNavigation: CheckBox = findViewById(R.id.cb_hide_navigation)
        val cbFullscreen: CheckBox = findViewById(R.id.cb_fullscreen)
        val cbLayoutHideNavigation: CheckBox = findViewById(R.id.cb_layout_hide_navigation)
        val cbLayoutFullscreen: CheckBox = findViewById(R.id.cb_layout_fullscreen)
        val cbLayoutStable: CheckBox = findViewById(R.id.cb_layout_stable)
        val cbImmersive: CheckBox = findViewById(R.id.cb_immersive)
        val cbImmersiveSticky: CheckBox = findViewById(R.id.cb_immersive_sticky)
        val cbLightStatusBar: CheckBox = findViewById(R.id.cb_light_status_bar)
        val cbFitsSystemWindows: CheckBox = findViewById(R.id.cb_fits_system_windows)

        findViewById<View>(R.id.btn_show).setOnClickListener {
            var flag = View.SYSTEM_UI_FLAG_VISIBLE
            if (cbProfile.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_LOW_PROFILE
                logi(LOG_TAG, "PROFILE")
            }
            if (cbHideNavigation.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                logi(LOG_TAG, "HIDE_NAVIGATION")
            }
            if (cbFullscreen.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_FULLSCREEN
                logi(LOG_TAG, "FULLSCREEN")
            }
            if (cbLayoutHideNavigation.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                logi(LOG_TAG, "LAYOUT_HIDE_NAVIGATION")
            }
            if (cbLayoutFullscreen.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                logi(LOG_TAG, "LAYOUT_FULLSCREEN")
            }
            if (cbLayoutStable.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                logi(LOG_TAG, "LAYOUT_STABLE")
            }
            if (cbImmersive.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_IMMERSIVE
                logi(LOG_TAG, "IMMERSIVE")
            }
            if (cbImmersiveSticky.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                logi(LOG_TAG, "IMMERSIVE_STICKY")
            }
            if (cbLightStatusBar.isChecked) {
                flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                logi(LOG_TAG, "LIGHT_STATUS_BAR")
            }

            val intent = Intent(this@SystemUiVisibilityActivity, SystemUiVisibilitySampleActivity::class.java)
            intent.putExtra("value", flag)
            intent.putExtra("fitsSystemWindows", cbFitsSystemWindows.isChecked)
            startActivity(intent)
        }
    }

}