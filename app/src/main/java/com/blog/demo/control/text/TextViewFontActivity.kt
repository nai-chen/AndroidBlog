package com.blog.demo.control.text

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R
import java.io.File

class TextViewFontActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_text_view_font)
        val textView = findViewById<TextView>(R.id.text_view)
        val font = Typeface.createFromAsset(assets, "fonts" + File.separator + "digital-7.ttf")
        textView.typeface = font
    }

}