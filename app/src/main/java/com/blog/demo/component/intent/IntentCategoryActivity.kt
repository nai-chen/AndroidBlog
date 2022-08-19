package com.blog.demo.component.intent

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R

class IntentCategoryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component_intent_category)
        val tvIntent: TextView = findViewById(R.id.tv_intent)
        tvIntent.text = intent.getStringExtra("value")
    }

}