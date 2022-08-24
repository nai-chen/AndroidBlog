package com.blog.demo.feature

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.*
import android.widget.TextView
import com.blog.demo.R

class ParagraphStyleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_paragraph_style)

        lendingMarginStyle()
        bulletSpanStyle()
        quoteSpanStyle()
        drawableMarginSpanStyle()
        iconMarginSpanStyle()
        complexSpanStyle()
    }

    private fun lendingMarginStyle() {
        var tv:TextView = findViewById(R.id.tv_lending_margin1)
        var ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(LeadingMarginSpan.Standard(60), 0, ss.length, 0)
        tv.text = ss

        tv = findViewById(R.id.tv_lending_margin2)
        ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(LeadingMarginSpan.Standard(60, 20), 0, ss.length, 0)
        tv.text = ss
    }

    private fun bulletSpanStyle() {
        var tv: TextView = findViewById(R.id.tv_bullet_span1)
        var ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(BulletSpan(), 0, ss.length, 0)
        tv.text = ss

        tv = findViewById(R.id.tv_bullet_span2)
        ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(BulletSpan(20, Color.RED), 0, ss.length, 0)
        tv.text = ss
    }

    private fun quoteSpanStyle() {
        var tv: TextView = findViewById(R.id.tv_quota_span1)
        var ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(QuoteSpan(), 0, ss.length, 0)
        tv.text = ss

        tv = findViewById(R.id.tv_quota_span2)
        ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(QuoteSpan(Color.RED), 0, ss.length, 0)
        tv.text = ss
    }

    private fun drawableMarginSpanStyle() {
        val drawable = getDrawable(R.drawable.checkbox_s)
        var tv: TextView = findViewById(R.id.tv_drawable_margin_span1)
        var ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(DrawableMarginSpan(drawable!!), 0, ss.length, 0)
        tv.text = ss

        tv = findViewById(R.id.tv_drawable_margin_span2)
        ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(DrawableMarginSpan(drawable, 20), 0, ss.length, 0)
        tv.text = ss
    }

    private fun iconMarginSpanStyle() {
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.checkbox_s)
        var tv: TextView = findViewById(R.id.tv_icon_margin_span1)
        var ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(IconMarginSpan(bmp), 0, ss.length, 0)
        tv.text = ss

        tv = findViewById(R.id.tv_icon_margin_span2)
        ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(IconMarginSpan(bmp, 20), 0, ss.length, 0)
        tv.text = ss
    }

    private fun complexSpanStyle() {
        var tv: TextView = findViewById(R.id.tv_complex_span1)
        val ss = SpannableString(getString(R.string.poetry))
        ss.setSpan(BulletSpan(), 0, ss.length, 0)
        ss.setSpan(LeadingMarginSpan.Standard(40, 0), 0, ss.length, 0)
        tv.text = ss
    }
}