package com.blog.demo.feature

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R

class BuildActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        val textView: TextView = findViewById(R.id.text_view)
        textView.text = """
                BOARD = ${Build.BOARD}
                BRAND = ${Build.BRAND}
                CPU_ABI = ${Build.CPU_ABI}
                DEVICE = ${Build.DEVICE}
                DISPLAY = ${Build.DISPLAY}
                FINGERPRINT = ${Build.FINGERPRINT}
                ID = ${Build.ID}
                MANUFACTURER = ${Build.MANUFACTURER}
                MODEL = ${Build.MODEL}
                PRODUCT = ${Build.PRODUCT}
                SERIAL = ${Build.SERIAL}
                TAGS = ${Build.TAGS}
                TYPE = ${Build.TYPE}
                """.trimIndent()
    }

}