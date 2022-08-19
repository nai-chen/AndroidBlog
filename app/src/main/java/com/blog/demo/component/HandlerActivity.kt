package com.blog.demo.component

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R
import java.text.SimpleDateFormat
import java.util.*

class HandlerActivity : Activity(), View.OnClickListener {

    private val UPDATE_TIME = 1

    private lateinit var mTextView: TextView
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                UPDATE_TIME -> {
                    mTextView.text = getCurrentTime()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_handler)

        findViewById<Button>(R.id.btn_send_empty_message).setOnClickListener(this)
        findViewById<Button>(R.id.btn_post_runnable).setOnClickListener(this)

        mTextView = findViewById(R.id.text_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_send_empty_message) {
            mHandler.sendEmptyMessage(UPDATE_TIME)
        } else if (v.id == R.id.btn_post_runnable) {
            mHandler.post {
                mTextView.text = getCurrentTime()
            }
        }
    }

    private fun getCurrentTime(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
    }

}