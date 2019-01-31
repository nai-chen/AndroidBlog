package com.blog.demo.control.picker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TimePicker;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class TimePickerActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_picker_time);

        TimePicker timePicker = findViewById(R.id.time_picker);
        timePicker.setCurrentHour(12);
        timePicker.setCurrentMinute(30);
        timePicker.setIs24HourView(true);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                LogTool.logi("TimePickerActivity", hourOfDay + ":" + minute);
            }
        });

        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                LogTool.logi("TimePickerActivity", hourOfDay + ":" + minute);
            }
        }, 11, 30, true).show();

    }
}
