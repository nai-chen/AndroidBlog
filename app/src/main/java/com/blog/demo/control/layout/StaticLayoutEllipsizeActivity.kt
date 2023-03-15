package com.blog.demo.control.layout

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.blog.demo.R

class StaticLayoutEllipsizeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_layout_static_ellipsize)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var staticLayoutView: StaticLayoutView = findViewById(R.id.view_static_layout_start)
            staticLayoutView.setLayout(buildStaticLayout(TextUtils.TruncateAt.START))

            staticLayoutView = findViewById(R.id.view_static_layout_middle)
            staticLayoutView.setLayout(buildStaticLayout(TextUtils.TruncateAt.MIDDLE))

            staticLayoutView = findViewById(R.id.view_static_layout_end)
            staticLayoutView.setLayout(buildStaticLayout(TextUtils.TruncateAt.END))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun buildStaticLayout(ellipsize: TextUtils.TruncateAt): StaticLayout {
        var text = getString(R.string.long_text)
        var textPaint = TextPaint()
        textPaint.textSize = resources.getDimension(R.dimen.font_size_20)
        return StaticLayout.Builder.obtain(text, 0, text.length, textPaint,
            resources.displayMetrics.widthPixels)
            .setEllipsize(ellipsize)
            .setMaxLines(1)
            .build()
    }

}