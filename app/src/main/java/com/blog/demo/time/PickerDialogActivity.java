package com.blog.demo.time;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.blog.demo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cn on 2017/3/30.
 */

public class PickerDialogActivity extends Activity implements View.OnClickListener {

    private Button mBtnDatePicker, mBtnTimePicker;
    private Calendar mCalendar = Calendar.getInstance();
    private DateFormat mDateFormatHour = new SimpleDateFormat("HH:mm");
    private DateFormat mDateFormatYear = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picker_dialog);

        mBtnDatePicker = (Button) findViewById(R.id.btn_date_picker_dialog);
        mBtnDatePicker.setOnClickListener(this);
        mBtnTimePicker = (Button) findViewById(R.id.btn_time_picker_dialog);
        mBtnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date_picker_dialog:
                openDatePickerDialog();
                break;
            case R.id.btn_time_picker_dialog:
                openTimePickerDialog();
                break;
        }
    }

    private void openDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(year, monthOfYear, dayOfMonth);

                mBtnDatePicker.setText(mDateFormatYear.format(mCalendar.getTime()));
            }
        }, 2000, 0, 1);
        dialog.show();
    }

    private void openTimePickerDialog() {
        mCalendar.setTime(new Date());
        TimePickerDialog dialog = new TimePickerDialog(this, new OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
        dialog.show();
    }

}
