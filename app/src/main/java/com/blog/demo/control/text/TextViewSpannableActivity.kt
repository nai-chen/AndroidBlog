package com.blog.demo.control.text

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.blog.demo.R

class TextViewSpannableActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_text_view_spannable)

        val tvSpannableString: TextView = findViewById(R.id.tv_spannable_string)
        val ss = SpannableString("普通文本红色字体蓝色背景")
        ss.setSpan(ForegroundColorSpan(Color.RED), 4, 8, 0)
        ss.setSpan(BackgroundColorSpan(Color.BLUE), 8, 12, 0)
        tvSpannableString.text = ss

        val tvClickSpan: TextView = findViewById(R.id.tv_click_span)
        val ssClick = SpannableString("点击我")
        ssClick.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@TextViewSpannableActivity, "onClick", Toast.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ds.linkColor
            }
        }, 0, 3, 0)
        tvClickSpan.text = ssClick
        // 点击事件，需要setMovementMethod
        tvClickSpan.movementMethod = LinkMovementMethod.getInstance()
//        tvClickSpan.setHighlightColor(Color.TRANSPARENT);
    }

}