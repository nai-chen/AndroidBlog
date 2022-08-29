package com.blog.demo.application

import android.app.Activity
import android.os.Bundle
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.blog.demo.R

class ScreenOnActivity : Activity(), View.OnClickListener {

    private lateinit var mContentView: View
    private var wakeLock: WakeLock? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_screen_on)
        mContentView = findViewById(R.id.view_content)

        findViewById<Button>(R.id.btn_add_flags).setOnClickListener(this)
        findViewById<Button>(R.id.btn_clear_flags).setOnClickListener(this)
        findViewById<Button>(R.id.btn_keep_screen_on).setOnClickListener(this)
        findViewById<Button>(R.id.btn_keep_screen_off).setOnClickListener(this)
        findViewById<Button>(R.id.btn_new_wake_lock).setOnClickListener(this)
        findViewById<Button>(R.id.btn_release).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_add_flags) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else if (v.id == R.id.btn_clear_flags) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else if (v.id == R.id.btn_keep_screen_on) {
            mContentView.keepScreenOn = true
        } else if (v.id == R.id.btn_keep_screen_off) {
            mContentView.keepScreenOn = false
        } else if (v.id == R.id.btn_new_wake_lock) {
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "demo:tag")
            wakeLock?.acquire()
        } else if (v.id == R.id.btn_release) {
            wakeLock?.release()
        }
    }
}