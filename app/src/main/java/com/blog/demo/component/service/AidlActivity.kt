package com.blog.demo.component.service

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R

class AidlActivity : Activity() {

    private var aidlInterface: IAidlInterface? = null
    private lateinit var sc: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindAidlService()
        setContentView(R.layout.activity_component_aidl_service)
        val tv = findViewById<TextView>(R.id.tv_aidl)
        findViewById<Button>(R.id.btn_add).setOnClickListener {
            try {
                tv.text = aidlInterface?.add(5, 6)?.toString() ?: ""
            } catch (e: RemoteException) {
            }
        }
    }

    private fun bindAidlService() {
        startService(Intent(this, AidlService::class.java))
        sc = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                aidlInterface = IAidlInterface.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName) {
                aidlInterface = null
            }
        }
        bindService(Intent(this, AidlService::class.java), sc, BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(sc)
    }

}