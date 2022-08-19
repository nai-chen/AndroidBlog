package com.blog.demo.component.receiver

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.blog.demo.LogTool
import com.blog.demo.R

class BroadcastReceiverActivity : Activity(), View.OnClickListener {

    private lateinit var mLocalBroadcastManager: LocalBroadcastManager

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            LogTool.logi("BroadcastReceiverActivity", "onReceive " + intent.getStringExtra("value"))
            //            abortBroadcast();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_receiver)

        findViewById<Button>(R.id.btn_register_receiver).setOnClickListener(this)
        findViewById<Button>(R.id.btn_unregister_receiver).setOnClickListener(this)
        findViewById<Button>(R.id.btn_send_broadcast).setOnClickListener(this)
        findViewById<Button>(R.id.btn_send_order_broadcast).setOnClickListener(this)
        findViewById<Button>(R.id.btn_local_register_receiver).setOnClickListener(this)
        findViewById<Button>(R.id.btn_local_unregister_receiver).setOnClickListener(this)

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_register_receiver) {
            val intentFilter = IntentFilter("com.blog.demo.action.receiver")
            intentFilter.priority = 500
            registerReceiver(mReceiver, intentFilter)
        } else if (v.id == R.id.btn_unregister_receiver) {
            unregisterReceiver(mReceiver)
        } else if (v.id == R.id.btn_send_broadcast) {
            val intent = Intent("com.blog.demo.action.receiver")
            intent.putExtra("value", "broadcast")
            sendBroadcast(intent)
        } else if (v.id == R.id.btn_send_order_broadcast) {
            val intent = Intent("com.blog.demo.action.receiver")
            intent.putExtra("value", "broadcast")
            sendOrderedBroadcast(intent, null)
        } else if (v.id == R.id.btn_local_register_receiver) {
            val intentFilter = IntentFilter("com.blog.demo.action.receiver")
            intentFilter.priority = 500
            mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter)
        } else if (v.id == R.id.btn_local_unregister_receiver) {
            mLocalBroadcastManager.unregisterReceiver(mReceiver)
        }
    }

}