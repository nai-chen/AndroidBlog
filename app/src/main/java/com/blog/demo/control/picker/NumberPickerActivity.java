package com.blog.demo.control.picker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class NumberPickerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_picker_number);

        NumberPicker numberPicker = findViewById(R.id.number_picker);
        numberPicker.setMinValue(10);
        numberPicker.setMaxValue(30);
        numberPicker.setValue(20);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                LogTool.logi("NumberPickerActivity", oldVal + ":" + newVal);
            }
        });
    }
}
