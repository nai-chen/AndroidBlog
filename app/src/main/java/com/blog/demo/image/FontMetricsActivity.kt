package com.blog.demo.image

import android.app.Activity
import android.os.Bundle
import com.blog.demo.image.widget.FontMetricsView

class FontMetricsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FontMetricsView(this))
    }

}