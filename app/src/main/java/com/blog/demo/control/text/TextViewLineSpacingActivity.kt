package com.blog.demo.control.text

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R

class TextViewLineSpacingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_text_view_line_spacing)
        val textView: TextView = findViewById(R.id.text_view)
        textView.setLineSpacing(20f, 1.5f)
    }

}