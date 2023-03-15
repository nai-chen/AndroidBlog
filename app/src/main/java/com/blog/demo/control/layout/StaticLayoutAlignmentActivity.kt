package com.blog.demo.control.layout

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.annotation.RequiresApi
import com.blog.demo.R

class StaticLayoutAlignmentActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_layout_static_alignment)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var staticLayoutView: StaticLayoutView = findViewById(R.id.view_static_layout_normal)
            staticLayoutView.setLayout(buildStaticLayout(Layout.Alignment.ALIGN_NORMAL))

            staticLayoutView = findViewById(R.id.view_static_layout_center)
            staticLayoutView.setLayout(buildStaticLayout(Layout.Alignment.ALIGN_CENTER))

            staticLayoutView = findViewById(R.id.view_static_layout_opposite)
            staticLayoutView.setLayout(buildStaticLayout(Layout.Alignment.ALIGN_OPPOSITE))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun buildStaticLayout(alignment: Layout.Alignment): StaticLayout {
        var text = getString(R.string.long_text)
        var textPaint = TextPaint()
        textPaint.textSize = resources.getDimension(R.dimen.font_size_20)
        return StaticLayout.Builder.obtain(text, 0, text.length, textPaint,
            resources.displayMetrics.widthPixels)
            .setAlignment(alignment)
            .build()
    }

}