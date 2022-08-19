package com.blog.demo.control.clock

import android.app.Activity
import android.os.Bundle
import android.widget.DigitalClock
import com.blog.demo.R

class DigitalClockActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_clock_digital)

        val clock: DigitalClock = findViewById(R.id.digital_clock)
        val cls: Class<Any> = clock.javaClass
        try {
            val f = cls.getDeclaredField("mFormat")
            f.isAccessible = true
            f.set(cls, "yyyy-MM-dd hh:mm")
        } catch (e: Exception) {
        }
    }

}