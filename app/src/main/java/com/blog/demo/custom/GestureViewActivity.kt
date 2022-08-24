package com.blog.demo.custom

import android.app.Activity
import android.os.Bundle
import com.blog.demo.custom.widget.GestureView

class GestureViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(GestureView(this))
    }

}