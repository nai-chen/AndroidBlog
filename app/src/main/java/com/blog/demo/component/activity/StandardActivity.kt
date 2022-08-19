package com.blog.demo.component.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R

open class StandardActivity : AbstractLifeActivity(), View.OnClickListener {
    private val LOG_TAG = "StandardActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_launch_mode)
        findViewById<Button>(R.id.btn_standard).setOnClickListener(this)
        findViewById<Button>(R.id.btn_single_top).setOnClickListener(this)
        findViewById<Button>(R.id.btn_single_task).setOnClickListener(this)
        findViewById<Button>(R.id.btn_single_instance).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_standard -> {
                startActivity(Intent(this, StandardActivity::class.java))
            }
            R.id.btn_single_top -> {
                startActivity(Intent(this, SingleTopActivity::class.java))
            }
            R.id.btn_single_task -> {
                startActivity(Intent(this, SingleTaskActivity::class.java))
            }
            R.id.btn_single_instance -> {
                startActivity(Intent(this, SingleInstanceActivity::class.java))
            }
        }
    }

    override fun getLogTag(): String {
        return LOG_TAG
    }
}