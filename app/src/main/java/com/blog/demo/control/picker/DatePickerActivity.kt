package com.blog.demo.control.picker

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import com.blog.demo.LogTool
import com.blog.demo.R

class DatePickerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_picker_date)
        val datePicker: DatePicker = findViewById(R.id.date_picker)
        datePicker.init(2000, 1, 1) { _, year, monthOfYear, dayOfMonth ->
            LogTool.logi("DatePickerActivity", "$year:$monthOfYear:$dayOfMonth")
        }
        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
                LogTool.logi("DatePickerActivity", "$year:$month:$dayOfMonth")
            }, 2000, 1, 1
        ).show()
    }

}