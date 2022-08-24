package com.blog.demo.feature

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R

class ConnectivityManagerActivity : Activity() {

    private lateinit var textView: TextView
    private lateinit var connectivityManager: ConnectivityManager

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val networkInfo = connectivityManager!!.activeNetworkInfo
                if (networkInfo != null) {
                    textView.text = """
                    Available = ${networkInfo.isAvailable}
                    Connected = ${networkInfo.isConnected}
                    Type = ${getType(networkInfo.type)}
                    Roaming = ${networkInfo.isRoaming}
                    State = ${networkInfo.state}
                    DetailedState = ${networkInfo.detailedState}
                    Failover = ${networkInfo.isFailover}
                    """.trimIndent()
                } else {
                    textView.text = "no active network info"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        textView = findViewById(R.id.text_view)
        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(mReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(mReceiver)
    }

    private fun getType(type: Int): String {
        return if (type == ConnectivityManager.TYPE_MOBILE) {
            "MOBILE"
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            "WIFI"
        } else {
            "unknow"
        }
    }

}