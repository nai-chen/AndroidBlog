package com.blog.demo.component.file

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R

class SharedPreferencesActivity : Activity(), View.OnClickListener {

    private val LOG_TAG = "SharedPreferencesActivity"

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_file_shared_preferences)

        findViewById<Button>(R.id.btn_write_shared_preferences).setOnClickListener(this)
        findViewById<Button>(R.id.btn_read_shared_preferences).setOnClickListener(this)

        textView = findViewById(R.id.text_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_write_shared_preferences) {
            val preference = getSharedPreferences("people", MODE_PRIVATE)
            val editor = preference.edit()
            editor.putInt("iValue", 10)
            editor.putFloat("fValue", 1.45f)
            editor.putBoolean("bValue", true)
            editor.putLong("lValue", 123)
            editor.putString("strValue", "This is a string")
            editor.commit()
        } else if (v.id == R.id.btn_read_shared_preferences) {
            val preference = getSharedPreferences("people", MODE_PRIVATE)
            textView.text = """
            iValue = ${preference.getInt("iValue", 0)}
            fValue = ${preference.getFloat("fValue", 0f)}
            bValue = ${preference.getBoolean("bValue", false)}
            lValue = ${preference.getLong("lValue", 0)}
            strValue = ${preference.getString("strValue", "")}
            """.trimIndent()
        }
    }
}