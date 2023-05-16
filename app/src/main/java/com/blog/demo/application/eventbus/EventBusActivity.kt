package com.blog.demo.application.eventbus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.blog.demo.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : Activity(), View.OnClickListener {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_event_bus)

        findViewById<Button>(R.id.btn_register).setOnClickListener(this)
        findViewById<Button>(R.id.btn_goto_publisher).setOnClickListener(this)

        textView = findViewById(R.id.text_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_register) {
            EventBus.getDefault().register(this)
        } else if (v.id == R.id.btn_goto_publisher) {
            startActivity(Intent(this, EventBusPublisherActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onReceiveMessage(event: MessageEvent) {
        textView.text = event.message
        Toast.makeText(this, "收到事件", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


}