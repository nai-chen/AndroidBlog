package com.blog.demo.feature

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.blog.demo.R

class AlarmManagerActivity : Activity(), View.OnClickListener {

    private val ACTION_ALARM = "com.blog.demo.feature.ACTION_ALARM"

    private lateinit var alarmManager: AlarmManager

    private lateinit var mRgType: RadioGroup
    private lateinit var editText: EditText

    private var pendingIntent: PendingIntent? = null
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_ALARM) {
                Toast.makeText(this@AlarmManagerActivity, "alarm", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_alarm_manager)

        findViewById<Button>(R.id.btn_set_alarm).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_repeat_alarm).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cancel_alarm).setOnClickListener(this)

        mRgType = findViewById(R.id.rg_type)
        editText = findViewById(R.id.edit_text)
        pendingIntent = PendingIntent.getBroadcast(this, 0, Intent(ACTION_ALARM), 0)

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(broadcastReceiver, IntentFilter(ACTION_ALARM))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onClick(v: View) {
        val time = editText.text.toString().toInt() * 1000
        if (v.id == R.id.btn_set_alarm) {
            alarmManager[getType(), time + SystemClock.elapsedRealtime()] = pendingIntent
        } else if (v.id == R.id.btn_set_repeat_alarm) {
            alarmManager.setRepeating(
                getType(),
                time + SystemClock.elapsedRealtime(),
                10000,
                pendingIntent
            )
        } else if (v.id == R.id.btn_cancel_alarm) {
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun getType(): Int {
        val id = mRgType.checkedRadioButtonId
        if (id == R.id.rb_elapsed_realtime) {
            return AlarmManager.ELAPSED_REALTIME
        } else if (id == R.id.rb_elapsed_realtime_wakeup) {
            return AlarmManager.ELAPSED_REALTIME_WAKEUP
        } else if (id == R.id.rb_rtc) {
            return AlarmManager.RTC
        } else if (id == R.id.rb_rtc_wakeup) {
            return AlarmManager.RTC_WAKEUP
        }
        return -1
    }

}