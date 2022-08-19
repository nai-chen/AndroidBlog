package com.blog.demo.component.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import com.blog.demo.R

open class IntentFlagActivity : AbstractLifeActivity(), View.OnClickListener {
    private val LOG_TAG = "IntentFlagFirstActivity"

    var mRadioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_intent_flag)
        mRadioGroup = findViewById(R.id.radio_group)
        findViewById<Button>(R.id.btn_standard).setOnClickListener(this)
        findViewById<Button>(R.id.btn_new_task).setOnClickListener(this)
        findViewById<Button>(R.id.btn_single_top).setOnClickListener(this)
        findViewById<Button>(R.id.btn_clear_top).setOnClickListener(this)
        findViewById<Button>(R.id.btn_multi).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        if (mRadioGroup!!.checkedRadioButtonId == R.id.rb_intent_flag) {
            intent = Intent(this, IntentFlagActivity::class.java)
        } else if (mRadioGroup!!.checkedRadioButtonId == R.id.rb_intent_flag_other) {
            intent = Intent(this, IntentFlagOtherActivity::class.java)
        }

        if (intent == null) return

        when (v.id) {
            R.id.btn_new_task -> {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            R.id.btn_single_top -> {
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            R.id.btn_clear_top -> {
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            R.id.btn_multi -> {
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        }
        startActivity(intent)
    }

    override fun getLogTag(): String {
        return LOG_TAG
    }

}