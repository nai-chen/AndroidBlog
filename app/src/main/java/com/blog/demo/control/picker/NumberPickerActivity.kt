package com.blog.demo.control.picker

import android.app.Activity
import android.os.Bundle
import android.widget.NumberPicker
import com.blog.demo.LogTool
import com.blog.demo.R

class NumberPickerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_picker_number)

        val numberPicker: NumberPicker = findViewById(R.id.number_picker)
        numberPicker.minValue = 10
        numberPicker.maxValue = 30
        numberPicker.value = 20
        numberPicker.setOnValueChangedListener { _, oldVal, newVal ->
            LogTool.logi("NumberPickerActivity", "$oldVal:$newVal")}
    }

}