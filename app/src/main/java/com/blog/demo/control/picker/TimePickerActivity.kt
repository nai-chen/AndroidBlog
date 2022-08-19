package com.blog.demo.control.picker

import android.app.Activity
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import com.blog.demo.LogTool
import com.blog.demo.R

class TimePickerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_picker_time)

        val timePicker: TimePicker = findViewById(R.id.time_picker)
        timePicker.currentHour = 12
        timePicker.currentMinute = 30
        timePicker.setIs24HourView(true)

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            LogTool.logi("TimePickerActivity", "$hourOfDay:$minute")
        }
        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        TimePickerDialog(this, { _, hourOfDay, minute -> {
                    LogTool.logi("TimePickerActivity", "$hourOfDay:$minute")
                }
            }, 11, 30, true).show()
    }

}