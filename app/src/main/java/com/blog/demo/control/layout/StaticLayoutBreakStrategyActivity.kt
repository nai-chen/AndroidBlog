package com.blog.demo.control.layout

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.annotation.RequiresApi
import com.blog.demo.R

class StaticLayoutBreakStrategyActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_layout_static_break_strategy)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var staticLayoutView: StaticLayoutView = findViewById(R.id.view_static_layout_simple)
            // 简单策略, 文本到达边界处再裁断
            staticLayoutView.setLayout(buildStaticLayout(Layout.BREAK_STRATEGY_SIMPLE))

            staticLayoutView = findViewById(R.id.view_static_layout_high_quality)
            // 高优化策略
            staticLayoutView.setLayout(buildStaticLayout(Layout.BREAK_STRATEGY_HIGH_QUALITY))

            staticLayoutView = findViewById(R.id.view_static_layout_balanced)
            // 平衡策略, 尽量保持每段文本长度平衡
            staticLayoutView.setLayout(buildStaticLayout(Layout.BREAK_STRATEGY_BALANCED))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun buildStaticLayout(breakStrategy: Int): StaticLayout {
        var text = getString(R.string.long_text)
        var textPaint = TextPaint()
        textPaint.textSize = resources.getDimension(R.dimen.font_size_20)
        return StaticLayout.Builder.obtain(text, 0, text.length, textPaint,
            resources.displayMetrics.widthPixels)
            .setBreakStrategy(breakStrategy)
            .build()
    }

}