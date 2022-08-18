package com.blog.demo.control.picker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class DatePickerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_picker_date);

        DatePicker datePicker = findViewById(R.id.date_picker);
        datePicker.init(2000, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                LogTool.logi("DatePickerActivity", year + ":" + monthOfYear + ":" + dayOfMonth);
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
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                LogTool.logi("DatePickerActivity", year + ":" + month + ":" + dayOfMonth);
            }
        }, 2000, 1, 1).show();

    }
}
