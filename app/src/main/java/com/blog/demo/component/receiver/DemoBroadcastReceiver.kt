package com.blog.demo.component.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.blog.demo.LogTool

class DemoBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        LogTool.logi("DemoBroadcastReceiver", "onReceive " + intent?.getStringExtra("value"))
    }
}