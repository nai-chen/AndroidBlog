package com.blog.demo.component.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.blog.demo.R

class LifeComplexActivity : AbstractLifeActivity() {
    private val LOG_TAG = "LifeComplexActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_life_complex)
        findViewById<Button>(R.id.btn_start_new_activity).setOnClickListener {
            val intent = Intent(this@LifeComplexActivity, LifeComplexOtherActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getLogTag(): String {
        return LOG_TAG
    }

}