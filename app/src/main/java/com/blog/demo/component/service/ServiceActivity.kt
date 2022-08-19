package com.blog.demo.component.service

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import com.blog.demo.LogTool
import com.blog.demo.R
import com.blog.demo.component.service.DemoService.DemoBind

class ServiceActivity : Activity(), View.OnClickListener {

    private val LOG_TAG = "ServiceActivity"

    private var sc: ServiceConnection? = null
    private var mService: DemoService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_service)

        findViewById<Button>(R.id.btn_start_service).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop_service).setOnClickListener(this)
        findViewById<Button>(R.id.btn_bind_service).setOnClickListener(this)
        findViewById<Button>(R.id.btn_unbind_service).setOnClickListener(this)

        LogTool.logi(LOG_TAG, "onCreate " + Thread.currentThread().name)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start_service -> {
                startService()
            }
            R.id.btn_stop_service -> {
                stopService()
            }
            R.id.btn_bind_service -> {
                bindService()
            }
            R.id.btn_unbind_service -> {
                unbindService()
            }
        }
    }

    private fun startService() {
        val intent = Intent(this, DemoService::class.java)
        startService(intent)
    }

    private fun stopService() {
        val intent = Intent(this, DemoService::class.java)
        stopService(intent)
    }

    private fun bindService() {
        if (sc == null) {
            sc = object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName, service: IBinder) {
                    mService = (service as DemoBind).getService()
                }

                override fun onServiceDisconnected(name: ComponentName) {
                    mService = null
                }
            }
            val intent = Intent(this, DemoService::class.java)
            bindService(intent, sc!!, BIND_AUTO_CREATE)
            LogTool.logi(LOG_TAG, "bindService " + Thread.currentThread().name)
        }
    }

    private fun unbindService() {
        if (sc != null) {
            unbindService(sc!!)
            sc = null
        }
    }

}