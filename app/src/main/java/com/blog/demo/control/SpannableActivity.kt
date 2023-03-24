package com.blog.demo.control

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.blog.demo.R

class SpannableActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_spannable)

        val editText: EditText = findViewById(R.id.edit_text)
        val ss = SpannableString("0123456789")
        ss.setSpan(ForegroundColorSpan(Color.RED), 3, 5, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        editText.setText(ss)

        makeSystemSpan()
        makeCustomSpan()
    }

    private fun makeSystemSpan() {
        var tv: TextView = findViewById(R.id.tv_foreground_color)
        tv.text = makeSpan(ForegroundColorSpan(Color.RED))

        tv = findViewById(R.id.tv_background_color)
        tv.text = makeSpan(BackgroundColorSpan(Color.BLUE))

        tv = findViewById(R.id.tv_strike_through)
        tv.text = makeSpan(StrikethroughSpan())

        tv = findViewById(R.id.tv_under_line)
        tv.text = makeSpan(UnderlineSpan())

        tv = findViewById(R.id.tv_url)
        tv.text = makeSpan(URLSpan("tel:1234567890"))
        tv.movementMethod = LinkMovementMethod.getInstance()

        tv = findViewById(R.id.tv_style)
        tv.text = makeSpan(StyleSpan(Typeface.BOLD_ITALIC))

        tv = findViewById(R.id.tv_typeface)
        tv.text = makeSpan(TypefaceSpan("monospace"))

        tv = findViewById(R.id.tv_sup)
        tv.text = makeSpan(SuperscriptSpan())

        tv = findViewById(R.id.tv_sub)
        tv.text = makeSpan(SubscriptSpan())

        tv = findViewById(R.id.tv_relative_size)
        tv.text = makeSpan(RelativeSizeSpan(2f))

        tv = findViewById(R.id.tv_absolute_size)
        tv.text = makeSpan(AbsoluteSizeSpan(25, true))

        tv = findViewById(R.id.tv_scale_x)
        tv.text = makeSpan(ScaleXSpan(2f))

        tv = findViewById(R.id.tv_image)
        val drawable = resources.getDrawable(R.drawable.star)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        tv.text = makeSpan(ImageSpan(drawable))

        tv = findViewById(R.id.tv_multi_span)
        val ss = SpannableString("0123456789")
        ss.setSpan(RelativeSizeSpan(2f), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(URLSpan("tel:1234567890"), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv.movementMethod = LinkMovementMethod.getInstance()
        tv.text = ss
    }

    private fun makeSpan(style: CharacterStyle): CharSequence {
        val ss = SpannableString("0123456789")
        ss.setSpan(style, 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss
    }

    private fun makeCustomSpan() {
        val tv: TextView = findViewById(R.id.text_view)
        val ss = SpannableString("点击我")
        ss.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@SpannableActivity, "onClick", Toast.LENGTH_LONG).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ds.linkColor
            }
        }, 0, 3, 0)
        tv.text = ss
        // 点击事件，需要setMovementMethod
        tv.movementMethod = LinkMovementMethod.getInstance()
    }

}