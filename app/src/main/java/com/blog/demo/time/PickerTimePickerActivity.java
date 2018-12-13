package com.blog.demo.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cn on 2017/3/28.
 */

public class PickerTimePickerActivity extends Activity {
    private TextView mTvTimePicker;
    private Calendar mCalendar = Calendar.getInstance();
    private DateFormat mDateFormatHour = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picker_time_picker);

        TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
        mTvTimePicker = (TextView) findViewById(R.id.tv_time_picker);

        // 显示为24小时制，而不是12小时制
        timePicker.setIs24HourView(true);
        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);
        timePicker.setCurrentHour(hourOfDay);
        timePicker.setCurrentMinute(minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTimePicker(hourOfDay, minute);
            }
        });
        setTimePicker(hourOfDay, minute);
    }

    private void setTimePicker(int hourOfDay, int minute) {
        LogUtil.log("PickerActivity", "hourOfDay = " + hourOfDay + ", minute = " + minute);

        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);

        mTvTimePicker.setText(mDateFormatHour.format(mCalendar.getTime()));
    }

}
