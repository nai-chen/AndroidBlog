package com.blog.demo.control.text

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.blog.demo.R

class TextViewRichTextActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        val textView: TextView = findViewById(R.id.text_view)
        val richText = ("<font color=\"red\">红色样式</font><br />"
                + "<big>大号字样式</big><br />"
                + "<small>小号字样式</small><br />"
                + "<i>斜体样式</i><br />"
                + "<b>粗体样式</b><br />"
                + "<tt>等t宽t样式</tt><br />"
                + "<p>段落样式</p><br />"
                + "<a href=\"http://www.baidu.com\">百度一下</a>")
        textView.text = Html.fromHtml(richText)
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

}