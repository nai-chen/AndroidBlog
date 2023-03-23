package com.blog.demo.component

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import com.blog.demo.R

class BatteryManagerActivity : Activity() {

    private lateinit var textView: TextView

    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            textView.text = """
             Status = ${getStatus(intent)}
             Health = ${getHealth(intent)}
             Present = ${intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false)}
             Level = ${intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)}
             Scale = ${intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)}
             Plugged = ${getPlug(intent)}
             Voltage = ${intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)}
             Temperature = ${intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)}
             Technology = ${intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)}
             """.trimIndent()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)
        textView = findViewById(R.id.text_view)

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(mReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    private fun getStatus(intent: Intent): String {
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
        return if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            "CHARGING"
        } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
            "DISCHARGING"
        } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
            "NOT_CHARGING"
        } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
            "FULL"
        } else {
            "UNKNOWN"
        }
    }

    private fun getHealth(intent: Intent): String {
        val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN)
        return if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
            "GOOD"
        } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
            "OVERHEAT"
        } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
            "DEAD"
        } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            "OVER_VOLTAGE"
        } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
            "UNSPECIFIED_FAILURE"
        } else if (health == BatteryManager.BATTERY_HEALTH_COLD) {
            "COLD"
        } else {
            "UNKNOWN"
        }
    }

    private fun getPlug(intent: Intent): String {
        val plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC)
        return if (plug == BatteryManager.BATTERY_PLUGGED_USB) {
            "USB"
        } else if (plug == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
            "WIRELESS"
        } else {
            "AC"
        }
    }
}