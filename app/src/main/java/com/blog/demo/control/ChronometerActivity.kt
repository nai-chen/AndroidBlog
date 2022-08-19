package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import com.blog.demo.R

class ChronometerActivity : Activity() {

    private lateinit var mChronometer:Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_chronometer)

        mChronometer = findViewById(R.id.chronometer)
        mChronometer.base = SystemClock.elapsedRealtime()
        mChronometer.format = "计时开始 %s"
        mChronometer.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        mChronometer.stop()
    }

}