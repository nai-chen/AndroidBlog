package com.blog.demo.control.layout

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.StaticLayout
import android.text.TextPaint
import androidx.annotation.RequiresApi
import com.blog.demo.R

class StaticLayoutLineSpacingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_layout_static_line_spacing)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var staticLayoutView: StaticLayoutView = findViewById(R.id.view_static_layout_normal)
            staticLayoutView.setLayout(buildStaticLayout(0f, 1.0f))

            staticLayoutView = findViewById(R.id.view_static_layout_include_padding)
            staticLayoutView.setLayout(buildStaticLayout(0f, 1.0f, false))

            staticLayoutView = findViewById(R.id.view_static_layout_space_add_10)
            // 行间距的额外增加值10
            staticLayoutView.setLayout(buildStaticLayout(10f, 1.0f))

            staticLayoutView = findViewById(R.id.view_static_layout_space_add_20)
            // 行间距的额外增加值20
            staticLayoutView.setLayout(buildStaticLayout(20f, 1.0f))

            staticLayoutView = findViewById(R.id.view_static_layout_space_mult_1_5)
            // 1.5倍的行间距
            staticLayoutView.setLayout(buildStaticLayout(0f, 1.5f))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun buildStaticLayout(spaceAdd: Float, spacingMult: Float, includePad: Boolean = true): StaticLayout {
        var text = getString(R.string.long_text)
        var textPaint = TextPaint()
        textPaint.textSize = resources.getDimension(R.dimen.font_size_20)
        return StaticLayout.Builder.obtain(text, 0, text.length, textPaint,
            resources.displayMetrics.widthPixels)
            .setLineSpacing(spaceAdd, spacingMult)
            .setIncludePad(includePad)
            .build()
    }

}