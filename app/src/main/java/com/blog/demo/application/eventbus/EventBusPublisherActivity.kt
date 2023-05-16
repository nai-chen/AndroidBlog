package com.blog.demo.application.eventbus

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R
import org.greenrobot.eventbus.EventBus

class EventBusPublisherActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_event_bus_publisher)

        findViewById<Button>(R.id.btn_publisher).setOnClickListener(this)
        findViewById<Button>(R.id.btn_publisher_sticky).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_publisher) {
            EventBus.getDefault().post(MessageEvent("普通事件"))
        } else if (v.id == R.id.btn_publisher_sticky) {
            EventBus.getDefault().postSticky(MessageEvent("粘性事件"))
        }
    }
}