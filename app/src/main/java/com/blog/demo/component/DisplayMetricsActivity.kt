package com.blog.demo.component

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import com.blog.demo.R

class DisplayMetricsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val textView: TextView = findViewById(R.id.text_view)
        textView.text = """
                widthPixels = ${dm.widthPixels}
                heightPixels = ${dm.heightPixels}
                densityDpi = ${dm.densityDpi}
                density = ${dm.density}
                """.trimIndent()
    }

}