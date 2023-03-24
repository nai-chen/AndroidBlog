package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import com.blog.demo.control.widget.GestureView

class GestureViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(GestureView(this))
    }

}