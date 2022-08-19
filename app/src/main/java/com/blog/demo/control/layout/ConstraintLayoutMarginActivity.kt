package com.blog.demo.control.layout

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R

class ConstraintLayoutMarginActivity : Activity() {

    private var visibility = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_layout_constraint_margin)

        val tvLeft: TextView = findViewById(R.id.tv_left)
        findViewById<Button>(R.id.btn).setOnClickListener {
            visibility = !visibility
            tvLeft.visibility = if (visibility) View.VISIBLE else View.GONE
        }
    }

}